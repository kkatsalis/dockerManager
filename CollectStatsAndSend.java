import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.io.*;

class CollectStatsAndSend{

  public static void main(String argv[])throws InterruptedException,IOException,Exception {
   
      
    String QUEUE_NAME = "dockerContainerStats";
    String targetpath = "/sys/fs/cgroup/cpuacct/docker/";
    String cpuUsage = null;
    int numCpus = Runtime.getRuntime().availableProcessors();
    
    // check and report how many containers exist
    
    FileFilter dirfilter = new FileFilter() {
      public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            return false;
        }
      }
    };
    
    
    File dir = new File(targetpath);
    File files[] = dir.listFiles(dirfilter);
    if (files.length == 0) {
	System.out.println("There are no container running");
    }
    else {
      for (File aFile : files) {
	  System.out.println(aFile.getName());
      }
    }
    
    
    RandomAccessFile  raf;

    // open file for reading cpu stats
    try{
      raf = new RandomAccessFile(targetpath+files[0].getName()+"/cpuacct.usage","r");
    }
    catch(FileNotFoundException ex){
      System.out.println("Could not find file to open");
      return;
    }
    // create connection to rabbit server and open queue
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  
    do{
      try{
	cpuUsage = raf.readLine();
	raf.seek(0);
	channel.basicPublish("", QUEUE_NAME, null, cpuUsage.getBytes());
	System.out.println(" [x] Sent '" + cpuUsage + "'");
	Thread.sleep(1000);
	}
     catch(IOException ex){
	System.out.println("System error could not read more stats ");
	channel.close();
	connection.close();
	return;
	}
    }while(cpuUsage!=null);
  
  //System.out.println("Numbers of cpus :"+ numCpus);
  raf.close();

  }

}
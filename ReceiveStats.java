import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.io.*;

class ReceiveStats{

  public static void main(String argv[]) throws Exception{
     
    
      
      
      
      
     //name of the queue is the id of the container
     String QUEUE_NAME = "dockerContainerStats";
     
     //create the connection and start the queue
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    
    Consumer consumer = new DefaultConsumer(channel) {
     int  prevCpuUsage = 0;
     int  curCpuUsage;
     int numCpus = Runtime.getRuntime().availableProcessors();
    @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
      String cpuUsage = new String(body, "UTF-8");
      System.out.print(" [x] Received '" + cpuUsage + "'");
      curCpuUsage = Integer.parseInt(cpuUsage);
      
	if(prevCpuUsage == 0){
	  prevCpuUsage = curCpuUsage;
	}
	System.out.print(" calculating Cpu percentage :");
	System.out.printf("%.2f",(float)(curCpuUsage - prevCpuUsage)/10000000/numCpus);
	System.out.println("%");
	prevCpuUsage = curCpuUsage;
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);

  }
}





	
	
	
	
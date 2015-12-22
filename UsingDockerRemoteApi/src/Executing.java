import java.io.IOException;

public class Executing {
	public static void main(String argv[]){
		
		ContainerCharacteristics obContainer = new ContainerCharacteristics(); 
		try{
			String contID = ContainerRemoteUtilities.createContainer(obContainer);
			if(contID.contains("Could not create container")){
				System.out.println(contID+"check message right above for info" );
				System.exit(0);
			}
			
			System.out.println("A container has been created with Id :"+contID);
			
			if(!ContainerRemoteUtilities.runContainer(contID)){
				System.out.println("Container could not start, check error message right above ");
				System.exit(0);
			}
		 
			System.out.println("Container is now running");
			
			//ContainerRemoteUtilities.getContainerStats(contID);
			
			
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
		
	} 
	
}

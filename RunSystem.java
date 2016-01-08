import java.util.*;

public class RunSystem{
	
	public static void main(String [] args){
		Controller ctrl = new Controller(3,15);
		ctrl.createRiderQueue("riders.txt");
		while(ctrl.getRidersinQueue() > 0){
			ctrl.assignRiderstoElevators();
			ctrl.update();
			int clearFlag = 0;
			int verboseFlag = 1;
			ctrl.printElevators(clearFlag, verboseFlag); 

			// Apply delay of X sec
			double sec = 0.5;
			long t2 = 0;
			long t1 = System.currentTimeMillis();
			do{ 
				 t2=System.currentTimeMillis(); 
			} while (t2-t1 < (sec*1000) ); //waits a second

		}
	}

	public void waitSec(double sec){
		
	}

}
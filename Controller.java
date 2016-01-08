import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

class Controller{

	private int numFloors;
	private int numElevators;
	private Elevator[] elevators;
	private LinkedList <Rider> riderQueue = new LinkedList <Rider>();
	private LinkedList <Rider> waitingList = new LinkedList <Rider>();
	private LinkedList <Rider> boardedList = new LinkedList <Rider>();
	private int time;

	public Controller(int num_elevators, int num_floors){
		// Constructor

		elevators = new Elevator[num_elevators];
		for(int i = 0; i < num_elevators; i++){
			elevators[i] = new Elevator(i);
		}
		numFloors = num_floors;
		numElevators = num_elevators;
		time = 0;
	}

	public int getRidersinQueue(){
		return riderQueue.size() + waitingList.size();
	}

	public void update(){
		// updates all elevators and increments time step
		for(int i = 0; i < numElevators; i++){
			
			int this_floor_before = elevators[i].getCurrentFloor();
			int this_dir_before = elevators[i].getDir();

			elevators[i].move();
			int this_floor = elevators[i].getCurrentFloor();
			int this_dir = elevators[i].getDir();

			System.out.println(i+": Before("+this_floor_before+", "+this_dir_before+
								 ") After("+this_floor+", "+this_dir+")");

			// check over list of waiting riders
			for(int r = 0; r < waitingList.size(); r++){
				Rider this_rider = waitingList.get(r);
				if(this_rider.getEntryFloor() == this_floor && (this_rider.getDir() == this_dir || this_dir == 0)){
					// conditions are met for this rider to enter this elevator
					System.out.println("Rider number " +this_rider.getNumber() + " entering elevator " + i);
					this_rider.setBoardedElevator(i);
					boardedList.add(this_rider);
					waitingList.remove(r);
					elevators[i].requestFloor(this_rider.getDesiredFloor());

				}
			}

			// check over list of boarded riders TODO

		}
		time ++;
	}


	public void assignRiderstoElevators(){
		// Check the queue for any new riders and assign them to the best elevator

		if(riderQueue.size() > 0){
			Rider peek_rider = riderQueue.peek();
			if(peek_rider.getTime() == time){
				Rider new_rider = riderQueue.poll();
				System.out.println("new rider number " + new_rider.getNumber());
				int best_index = 0;
				int best_pickup_time = 99;

				for(int i = 0; i < numElevators; i++){
					int pickup_time = elevators[i].calcPickupTime(new_rider);
					System.out.println("Pickup time for Elevator "+i+ " is "+ pickup_time);
					if(pickup_time < best_pickup_time){
						best_pickup_time = pickup_time;
						best_index = i;
					}
				}
				System.out.println("best elevator is " + best_index);
				// assign rider to 'lobby'
				waitingList.add(new_rider);
				System.out.println("rider added to waiting list");

				// send elevator to entry floor
				elevators[best_index].requestFloor(new_rider.getEntryFloor());
				System.out.println("Elevator " + best_index + " sent to get rider");
			}
		}
	}

	public void createRiderQueue(String Filename){
		// Read input file and create rider queue

		File file = new File("riders.txt");
		int count = 0;
		try{
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()){
				count++;
				int num = sc.nextInt();
				int t = sc.nextInt();
				int entry = sc.nextInt();
				int des = sc.nextInt();
				riderQueue.add(new Rider(num,t,entry,des));
			}
			sc.close();
			System.out.println("Import successful. Rider queue contains " + count + " entries.");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}
	public void printElevators(int clearFlag, int verbose){
		// Print graphical depiction of system state in terminal

		if(clearFlag > 0){
			clearScreen();
		}

		if(verbose > 0){
			for(int i = 0; i < numElevators; i++){
				elevators[i].print();
			}
		}

		System.out.println("Timestep = " +time);
		System.out.print("Floor  \t Lobby \t\t");

		int[][] printMat = new int[numElevators][numFloors];
		for(int i = 0; i < numElevators; i++){
			int this_floor = elevators[i].getCurrentFloor();
			printMat[i][this_floor] = 1;
			System.out.print("Elevator " + i + "\t");
		}
		System.out.println("");

		String[] printLobby = new String[numFloors];
		for(int r = 0; r < waitingList.size(); r++){
			Rider this_rider = waitingList.get(r);
			int fl = this_rider.getEntryFloor();
			printLobby[fl]=(String.valueOf(this_rider.getNumber()));
		}

		for(int j = numFloors-1; j >= 0; j--){
			System.out.print(j + "\t");
			// Lobby
			System.out.print("\t");
			System.out.print(printLobby[j]); //TODO print waiting Riders
			System.out.print("\t");

			for(int i = 0; i < numElevators; i++){
				System.out.print("|\t");
				if(printMat[i][j] != 0){
					System.out.print("X");
				}
				System.out.print("\t");
			}
			System.out.println("");
		}
	}

	public static void clearScreen(){
		System.out.print("\033[H\033[2J");  
    	System.out.flush();
	}

}
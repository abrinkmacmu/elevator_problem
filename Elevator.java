import java.util.LinkedList;

class Elevator
{
	private int id;
	private int currentFloor;
	private int dir;
	private int topFloor;
	private int botFloor;

	
	public Elevator(int i) //default constructor
	{
		id = i;
		currentFloor = 0; // initialize to ground floor
		dir = 0;
		topFloor = -999;
		botFloor = 999;
	}

	public int calcPickupTime(Rider rider){
		// Given a rider, return the number of time step before they can be picked up
		int rider_dir = rider.getDir();
		int rider_entry = rider.getEntryFloor();

		if(dir == rider_dir || dir == 0){
			return Math.abs(rider_entry - currentFloor);
		}else{
			if(dir > 0){
				return (topFloor - currentFloor + topFloor - rider_entry);
			}else{
				return (currentFloor - botFloor + rider_entry - botFloor);
			}
		}
	}

	public void requestFloor(int request){
		if(request > topFloor && request > currentFloor){
			topFloor = request;
		}else if(request < botFloor && request < currentFloor){
			botFloor = request;
		}
	}

	public void move(){
		
		if(dir == 0){
			if(topFloor != -999 && topFloor > currentFloor){
				dir = 1;
			}else if (botFloor != 999 && botFloor < currentFloor){
				dir = -1;
			}
		}else if(dir > 0){
			if(currentFloor >= topFloor){
				topFloor = -999;
				if(botFloor != 999 && botFloor < currentFloor){
					dir = -1;
				}else{
					dir = 0;			
				}
			}
		}else if(dir < 0){
			if(currentFloor <= botFloor){
				botFloor = 999;
				if(topFloor != -999 && topFloor > currentFloor){
					dir = 1;
				}else{
					dir = 0;
				}
			}
		}

		currentFloor = currentFloor + dir;
		
	}

	public int getCurrentFloor(){
		return currentFloor;
	}

	public int getDir(){
		return dir;
	}

	public void print(){
		System.out.println("Elevator info: " +
			"id: " + id + ", " +
			"current: " + currentFloor + ", " +
			"dir: " + dir + ", " +
			"top: " + topFloor + ", " +
			"bot: " + botFloor );
	}
}
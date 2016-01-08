class Rider{
	private int number;
	private int time;
	private int entryFloor;
	private int desiredFloor;
	private int dir;
	private int boardedElevator;

	public Rider(int num, int t, int entry, int des){
		number = num;
		time = t;
		entryFloor = entry;
		desiredFloor = des;
		if(des > entry){
			dir = 1;
		}else{
			dir = -1;
		}
		boardedElevator = -1;
	}

	public int getNumber(){
		return number;
	}

	public int getTime(){
		return time;
	}

	public int getEntryFloor(){
		return entryFloor;
	}

	public int getDesiredFloor(){
		return desiredFloor;
	}

	public int getDir(){
		return dir;
	}

	public int getBoardereElevator(){
		return boardedElevator;
	}

	public void setBoardedElevator(int ele){
		boardedElevator = ele;
	}

	public void print(){
		System.out.println("Rider info: " +
			"#: " + number + ", " +
			"entry: " + entryFloor + ", " +
			"desired: " + desiredFloor + ", " +
			"dir: " + dir + ", " +
			"board: " + boardedElevator);
	}
}
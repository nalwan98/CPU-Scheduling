package assignment2;


public class Process {
	private String processName;
	public int processId;
	public int arrivalTime;
	public int priority;
	public int numOfProcesses;
	public int[] burstCycle = new int[numOfProcesses];
	public int turnaroundTime;
	public int  waitingTime;
	public int burstTime;
	public int completionTime;
	public State processState;

	public enum State {
		New,
		Running,
		Ready,
		Waiting,
		Terminated;
	}

	public Process(int processId, int burstTime, int arrivalTime)
	{
		this.processId = processId;
		this.burstTime = burstTime;
		this.arrivalTime = arrivalTime;
		this.processState = State.New;
	}
	public Process (String name,  int ArrivalTime, int priority, int[] SeqCPUIO) {
		this.processState = State.New;
		this.priority = priority;
		this.arrivalTime = ArrivalTime;
		this.processName = name;
		this.burstCycle = new int[SeqCPUIO.length]; // sequence of CPU & I/O bursts
		for(int i=0; i< SeqCPUIO.length; i++) {
			this.burstCycle[i] = SeqCPUIO[i];
			}
		for(int i=0; i< this.burstCycle.length; i++) {
			
			if(i % 2 == 0) {
			burstTime += SeqCPUIO[i];
			}
			}
		
	}

	public String getName() {
		return processName;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public void SetBurstTime(int burstTime) { this.burstTime = burstTime; }

	public int getWaitingTime() {
		return this.waitingTime;
	}

	public void setstate(State state) {
		this.processState = state;
	}

	public int getarrival() {
		return arrivalTime;
	}

	public void setArriveTime(int arrive) {
		this.arrivalTime = arrive;
	}

	public State getstate() {
		return processState;
	}

	public int getTurnaroundTime() {
		return this.turnaroundTime;
	}

	public int getBurstTime() { 
		return burstTime; 
		}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime; 
		}
	
	public void setCompletionTime(int completionT) { 
		this.completionTime = completionT;
		}
	
	public int getCompletionTime() {
		return completionTime;
		}
}

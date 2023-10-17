package assignment2;

public class PrePriSched extends CpuSched {

	public PrePriSched(int size) {
	super(size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scheduler() {
	insertionSort(getProcesses()); //sort according to arrival time
	SinglyLinkedList <Process>process1 = new SinglyLinkedList<Process>();//a copy of all the processes in the CPU, so we don't edit them
	int numOfProCompleted = 0;
	int SystemTime = getProcesses()[0].getarrival(); //SystemTime set on the least arrival time;
	int numOfPro= getProcesses().length; //number of processes in the CPU
	Process [] Proc = new Process [numOfPro]; //ready queue
	ArrayQueue <Process> Proc1 = new ArrayQueue<Process>(numOfPro); //running queue
	String sequence = "";
	for(int i=0; i<numOfPro; i++) {
	      Process temp = new Process(getProcesses()[i].getName(),getProcesses()[i].getarrival(),getProcesses()[i].getPriority(),getProcesses()[i].burstCycle);
	      process1.addLast(temp); //to add a copy so we don't edit the original processes
	}
	SystemTime = getProcesses()[0].getarrival(); //SystemTime set on the least arrival time;
	while(true) {//start the loop to schedule
	if(numOfProCompleted== numOfPro) { //stop looping of all processes are completed
	break;
	}
	boolean flag = true;


	for(int i=0; i<numOfPro && flag && process1.first()!=null; i++) {
	flag = false;
	if(process1.first().getarrival() <= SystemTime) {//check if a process has arrived
	int s =  Integer.parseInt(process1.first().getName().substring(1));
	flag = true;
	getProcesses()[s].setstate(Process.State.Ready);//set the state to ready after arrival
	      process1.removeFirst();// remove it from the copy
	      Proc[i] = getProcesses()[s];
	     
	      if(Proc1.isEmpty()) {
	      Proc1.enqueue(Proc[i]);
	      getProcesses()[s].setstate(Process.State.Running);
	      }

	     
	      else if((Proc1.first().getarrival() + Proc1.first().getBurstTime()) <= Proc[i].getarrival()) {
	     
	      Proc1.enqueue(Proc[i]);
	      getProcesses()[s].setstate(Process.State.Running);
	     
	      }
	      else if (Proc[i].getPriority() > Proc1.first().getPriority()) {
	     
	     
	      Proc1.first().setstate(Process.State.Waiting);
	      Proc1.enqueue(Proc[i]);
	      getProcesses()[s].setstate(Process.State.Running);
	           
	      }
	     
	      else {
	     
	      insertionSort2(Proc);
	     
	      }
	     
	 
	}}

	insertionSort2(Proc);// to sort the processes according to the priority

	for(int m=0; m < Proc.length && Proc1.size()!=numOfPro && Proc[m]!=null; m++) { // to add the ready processes to the running queue

	if(Proc[m].getstate() == Process.State.Ready ||  Proc[m].getstate() == Process.State.Waiting) {
	Proc1.enqueue(Proc[m]);
	}
	}
	int s =  Integer.parseInt(Proc1.first().getName().substring(1));//get the processes number to use in indexing
	SystemTime += Proc1.first().getBurstTime();  //update system time
	 numOfProCompleted ++; //increase number of processes completed
	 sequence += "-> " + Proc1.first().getName(); // to print the ready queue
	 Proc1.dequeue();  // remove the finished processes from the queue
	 getProcesses()[s].setstate(Process.State.Terminated);//set its state to terminated
	 boolean flag3 = true;
	 for(int h=0; h< getProcesses().length && flag3; h++) //loop to assign the wait, turn around, completion time to the correspondent process
	 {
	    if( Integer.parseInt(getProcesses()[h].getName().substring(1))==(s))//to get the index
	    {
	    flag3 = false;
	    getProcesses()[h].setCompletionTime(SystemTime);//set completion time
	    getProcesses()[h].setWaitingTime(SystemTime - getProcesses()[h].getBurstTime() - getProcesses()[h].getarrival());//set waiting Time
	    getProcesses()[h].setTurnaroundTime(SystemTime - getProcesses()[h].getarrival());//set turn around time
	    }
	 }

	}
	System.out.println("READY QUEUE: " +sequence);  //for testing
	      System.out.println("# OF PROCESSES COMPLETED: "+ numOfProCompleted);//for testing
	      System.out.println();//for appearance
	      System.out.println("NAME " + "\t" + "ARRIVAL" + "\t" + "COMPLETE " + "WAIT" +"\t"+ "TURNAROUND" + "\t" + "STATE");//for testing
	for(int n=0; n<getProcesses().length; n++) {//for testing
	  System.out.print(getProcesses()[n].getName()+ "\t");//for testing
	  System.out.print(getProcesses()[n].getarrival()+ "\t");//for testing
	  System.out.print(getProcesses()[n].getCompletionTime()+ "\t");//for testing
	  System.out.print(getProcesses()[n].getWaitingTime()+ "\t");//for testing
	  System.out.print(getProcesses()[n].getTurnaroundTime()+ "\t\t");//for testing
	  System.out.println(getProcesses()[n].getstate()+ "\t");//for testing
	  System.out.println();//for appearance
	}
	}
	}


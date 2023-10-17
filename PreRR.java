package assignment2;

public class PreRR extends CpuSched {
	

	public PreRR(int size) {
		super(size);
	}
	@Override
	public void scheduler() {
		System.out.println("********before looping********");//for appearance and testing
		 System.out.println();//for appearance and testing
		 System.out.println("NAME " + "\t" +"STATE");//for appearance and testing
		for(int n=0; n<getProcesses().length; n++) {
			 System.out.print(getProcesses()[n].getName()+ "\t");//for testing
			 System.out.println(getProcesses()[n].getstate()+ "\t");//for testing
			 System.out.println();//for appearance and testing
		}
		insertionSort(getProcesses());	//to sort depends on arrival time
		 int numOfProCompleted = 0; 	//number of processes completed, p=2
	 CircularlyLinkedList<Process> Proc = new CircularlyLinkedList<>();  // ready queue
	 	int SystemTime = getProcesses()[0].getarrival();	//SystemTime set on the least arrival time; 
		int numOfPro= getProcesses().length;	//number of processes in the CPU
		int timeSlice = 2;						// time slice of round robin
		String sequence = "";					// sequence of ready queue
		boolean flag = true;					// its used to break the loop when the number of processes completed = number of processes in the CPU
		boolean flag1 = false;					//if its true, the ready queue rotates, but if the process on the first of the queue is terminated then flag1 is set to false and queue doesn't rotate 
		int s=0;
  	for(int i = 0; i < numOfPro || flag; ) 
  	{
	    		  
	    for(int j=i; j < numOfPro;  ) //using this loop to add the processes to the queue when they arrive
	   {
	      if(getProcesses()[j].getarrival() <= SystemTime) //a process arrives when its equal to the system time or less
	      {
	    	  System.out.println("********after checking arrival time********");//for appearance and testing
	 		 System.out.println();//for appearance and testing
	       getProcesses()[i].setstate(Process.State.Ready);//set the state of the process to ready because it's arrived
	       System.out.print(getProcesses()[i].getName()+ "\t");//for testing
		   System.out.println(getProcesses()[i].getstate()+ "\t");//for testing
		   System.out.println();//for appearance and testing
	       Process temp = new Process(getProcesses()[j].getName(),getProcesses()[j].getarrival(),getProcesses()[j].getPriority(),getProcesses()[j].burstCycle);//make a copy
		    Proc.addLast(temp); //add it to the queue
		     j++;	
		     i = j;
	      }
	      else  { j++; }			   
	  }
	    if(flag1) {   Proc.rotate(); }//to rotate
	    flag1 =true;
	    sequence += "-> " + Proc.first().getName(); // to print the ready queue
	    s =  Integer.parseInt(Proc.first().getName().substring(1)); //get the processes number to use in indexing
	  if(Proc.first().getBurstTime() > timeSlice) // if burst time is less than the time slice, the processes is going to run but not going to be completed
	    {
		  System.out.println("********in the running queue********");//for appearance and testing
			 System.out.println();//for appearance and testing
		  getProcesses()[s].setstate(Process.State.Running);//the process is running, set state
		  System.out.print(getProcesses()[s].getName()+ "\t");//for testing
		   System.out.println(getProcesses()[s].getstate()+ "\t");
		   System.out.println();//for appearance and testing
		  SystemTime = SystemTime + timeSlice ; //update system time
		  Proc.first().SetBurstTime(Proc.first().getBurstTime() - timeSlice) ;//update the burst time of the process in ready queue
	    }
	 else 
	 {
		  SystemTime +=  Proc.first().getBurstTime();	//if burst time is more than time slice, then time system is updated by adding burst time to it
		  numOfProCompleted ++;		//increase number of processes completed
		  getProcesses()[s].setstate(Process.State.Terminated);	//set the state of the process to terminated
		  boolean flag3 = true;
		  for(int h=0; h< getProcesses().length && flag3; h++) //loop to assign the wait, turn around, completion time to the correspondent process
		  {
		     if( Integer.parseInt(getProcesses()[h].getName().substring(1))==(s)) //get the processes number to use in indexing
		     {
		     flag3 = false;
		     getProcesses()[h].setCompletionTime(SystemTime);//set completion time
		     getProcesses()[h].setWaitingTime(SystemTime - getProcesses()[h].getBurstTime() - getProcesses()[h].getarrival());//calculate the wait time of the process and set it to the process
		     getProcesses()[h].setTurnaroundTime(SystemTime -  getProcesses()[h].getarrival());//calculate the turn around time of the process and set it to the process
		     }
		  }
		  Proc.removeFirst();// remove it from the queue
		  flag1 = false;
		  if( numOfProCompleted == numOfPro) // check when the number of processes completed = number of processes in the CPU to break the loop
		  {
		  flag = false;
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
	
	  
	
		  




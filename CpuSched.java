package assignment2;

public abstract class CpuSched {
		
		private int size;
	
	  private final Process[] processes ;
	  
	  public CpuSched(int size) {
	       this.size =size;
	       processes = new Process[size];
	  }
	  
	 public int getAverageTurnAroundTime()
	    {
		 
		 int n = getProcesses().length;
	        int avg = 0;
	        for (int i =0; i< getProcesses().length ; i++)
	        {
	         avg += getProcesses()[i].getTurnaroundTime();
	     	
	        }
	       return avg / n;
	    }
	 
	  public int getAverageWaitingTime()
	  {
		
			 int n = getProcesses().length;
		       int avg = 0;
		        for (int i = 0; i<getProcesses().length ; i++)
		        {
		         avg += getProcesses()[i].getWaitingTime();
		       
		        }
		        
		        return avg / n;
		    }
	  
	  public void add(Process p, int i)
	    {
	       processes[i]=p;
	       
	    }
	  public abstract void scheduler();

	public Process[] getProcesses() {
		return processes;
	}
	
	protected static void insertionSort( Process[] p) {
		int n = p.length;
		for(int i = 1; i<n; i++) {
			Process current = p[i];
			int cur = p[i].getarrival();
			int j = i;
			while(j>0 && p[j-1].getarrival()>cur) {
				p[j]= p[j-1];
				p[j] = p[j-1];
				j--;
			}
			p[j] = current;
			
		}
		}
	protected static void insertionSort1( Process[] p) {
		int n = p.length;
		for(int i = 1; i<n && p[i]!=null ; i++) {
			
			Process current = p[i];
			int cur = p[i].getBurstTime();
			int j = i;
			while(j>0 && p[j-1].getBurstTime()>cur) {
				p[j]= p[j-1];
				p[j] = p[j-1];
				j--;
			}
			p[j] = current;
			
		}
		}
	protected static void insertionSort2( Process[] p) {
		int n = p.length;
		for(int i = 1; i<n && p[i]!=null ; i++) {
			
			Process current = p[i];
			int cur = p[i].getPriority();
			int j = i;
			while(j>0 && p[j-1].getPriority()>cur) {
				p[j]= p[j-1];
				p[j] = p[j-1];
				j--;
			}
			p[j] = current;
			
		}
		}
	
	  
}

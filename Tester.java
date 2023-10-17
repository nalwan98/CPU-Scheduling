package assignment2;

public class Tester {

public static void main(String[] args) {
	CpuSched nn = new Non_SJF(4);
	int [] oneB = {0, 7, 0, 5, 1}; //1
	int [] twoB = {0, 1, 2, 1, 0}; //2
	int [] threeB = {2, 6, 1, 1, 0}; //3
	int [] fourB = {1, 7, 1, 5, 2}; //4
	int [] fiveB = {1, 7, 1, 5, 3}; //5
	int [] sixB = {1, 1, 4, 1, 1}; //6
	int [] sevenB = {1, 1, 4, 1, 2}; //7
	int [] eightB = {2, 1, 4, 1, 2}; //8
	int [] tenB = {5,1,3,2, 2};
	
	Process p0 = new Process("p0",0, 1, sixB); //String name,  int ArrivalTime, int priority, cpu/io
	Process p1 = new Process("p1",0, 2,eightB);
	Process p2 = new Process("p2",0, 3, sevenB);
	Process p3 = new Process("p3",0, 4, threeB); 
	nn.add(p0, 0);
	nn.add(p1, 1);
	nn.add(p2, 2);
	nn.add(p3, 3);
	System.out.println("\t\t\t Tester1 same arrival for NON SJF");
	System.out.println();
	nn.scheduler();
	System.out.println("Average Waiting Time:" + nn.getAverageWaitingTime());
	System.out.println("Average TurnAround Time:" + nn.getAverageTurnAroundTime());
	System.out.println("--------------------------------------------------------------------");
	CpuSched xx = new Non_SJF(4);
	Process m0 = new Process("p0",0, 2, sevenB); //String name,  int ArrivalTime, int priority, cpu/io
	Process m1 = new Process("p1",2, 3,fourB);
	Process m2 = new Process("p2",4, 1, oneB);
	Process m3 = new Process("p3",5, 2, fourB); 
	xx.add(m0, 0);
	xx.add(m1, 1);
	xx.add(m2, 2);
	xx.add(m3, 3);
	System.out.println("\t\t\t Tester2 differnet arrival for NON SJF");
	System.out.println();
	xx.scheduler();
	System.out.println("Average Waiting Time:" + xx.getAverageWaitingTime());
	System.out.println("Average TurnAround Time:" + xx.getAverageTurnAroundTime());
	System.out.println("--------------------------------------------------------------------");
	
	CpuSched ww = new PreRR(6);
	Process o0 = new Process("p0",0, 2, fourB); //String name,  int ArrivalTime, int priority, cpu/io
	Process o1 = new Process("p1",1, 3,fiveB);
	Process o2 = new Process("p2",2, 1, twoB);
	Process o3 = new Process("p3",3, 2, oneB); 
	Process o4 = new Process("p4",4, 6, sixB);
	Process o5 = new Process("p5",6, 1, threeB);
	ww.add(o0, 0);
	ww.add(o1, 1);
	ww.add(o2, 2);
	ww.add(o3, 3);
	ww.add(o4, 4);
	ww.add(o5, 5);
	System.out.println("\t\t\t Tester for ROUND ROBIN");
	System.out.println();
	ww.scheduler();
	System.out.println("Average Waiting Time:" + ww.getAverageWaitingTime());
	System.out.println("Average TurnAround Time:" + ww.getAverageTurnAroundTime());
System.out.println("--------------------------------------------------------------------");
	
	CpuSched ii = new NonPriSched(4);
	Process y0 = new Process("p0",0, 3, fourB); //String name,  int ArrivalTime, int priority, cpu/io
	Process y1 = new Process("p1",1, 2,twoB);
	Process y2 = new Process("p2",2, 4, threeB);
	Process y3 = new Process("p3",4, 1, twoB); 
	///Process y4 = new Process("p4",4, 8, oneB);
//	Process y5 = new Process("p5",5, 12, fourB);
	//Process y6= new Process("p6", 6, 9, sixB);
	//Process y5 = new Process("p5",6, 1, threeB);
	ii.add(y0, 0);
	ii.add(y1, 1);
	ii.add(y2, 2);
	ii.add(y3, 3);
//	ii.add(y4, 4);
	//ii.add(y5, 5);
	//ii.add(y6, 6);
	
	System.out.println("\t\t\t Tester for NON Priority");
	System.out.println();
	ii.scheduler();
	System.out.println("Average Waiting Time:" + ii.getAverageWaitingTime());
	System.out.println("Average TurnAround Time:" + ii.getAverageTurnAroundTime());

	// Tester for PreemptiveSJF
	System.out.println("Preemptive SJF");
	Process proc[] = { new Process(1, 6, 1),
			new Process(2, 8, 1),
			new Process(3, 7, 2),
			new Process(4, 3, 3)};

	PreSJF.findAverageTimes(proc, proc.length);

	// Tester for FCFS
	System.out.println("FCFS");
	Process[] processes = { new Process(1, 5, 0),
			new Process(2, 9, 3),
			new Process(3, 6, 6)};
	int n = processes.length;
	NonFCFS.findAverageTimes(processes, n);
}
}

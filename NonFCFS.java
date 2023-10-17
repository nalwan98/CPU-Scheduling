package assignment2;
//import process.Process;

public class NonFCFS {


    /**
     * Find waiting time for individual processes in proccesList
     * @param processList - list of processes
     * @param numOfProcesses - number of processes in processList
     * @param waitingTime - array to store the waiting time of each process in processList
     */
    private static void findWaitingTime(Process[] processList, int numOfProcesses, int[] waitingTime) {
        int[] service_time = new int[numOfProcesses];
        service_time[0] = 0;
        waitingTime[0] = 0;

        // Go through processList and calculate waiting time for each process
        for (int i = 1; i < numOfProcesses ; i++)
        {
            /* Service time: amount of time before process at processList[i] can execute
             * (sum of service time & burst time of previous process in processList)
             */
            service_time[i] = service_time[i-1] + processList[i-1].burstTime;

            // Calculate waiting time for process at processList[i]
            waitingTime[i] = service_time[i] - processList[i].arrivalTime;

            // A negative waiting time indicates process is already in ready queue before CPU is idle
            if (waitingTime[i] < 0)
                waitingTime[i] = 0;
                processList[i].setstate(Process.State.Ready);
        }
    }

    /**
     * Find turnaround time for individual processes in proccesList
     * @param processList - list of processes
     * @param numOfProcesses - number of processes in processList
     * @param waitingTime - array to store the waiting time of each process in processList
     * @param turnaroundTime - array to store the turnaround time of each process in processList
     */
    private static void findTurnAroundTime(Process[] processList, int numOfProcesses, int[] waitingTime, int[] turnaroundTime) {
        // The turnaround for each process in processList is the burst time of that process plus its waiting time
        for (int i = 0; i < numOfProcesses ; i++)
            turnaroundTime[i] = processList[i].burstTime + waitingTime[i];
    }

    /**
     * Finds the average waiting time and average turnaround time for the given list of processes in processList.
     * The average times are printed to the console along with tabular representation of process list inputs
     * @param processList - list of processes
     * @param numOfProcesses - number of processes in processList
     */
    public static void findAverageTimes(Process[] processList, int numOfProcesses) {
        int[] waitingTime = new int[numOfProcesses];
        int[] turnaroundTime = new int[numOfProcesses];

        // get waiting times
        findWaitingTime(processList, numOfProcesses, waitingTime);

        // get turnaround times
        findTurnAroundTime(processList, numOfProcesses, waitingTime, turnaroundTime);

        // print process details to console
        System.out.println("FCFS");
        System.out.print("Processes " + " Burst Time " + " Arrival Time "
                + " Waiting Time " + " Turn-Around Time "
                + " Completion Time \n");
        int totalWaitingTime = 0, totalTurnaroundTime = 0;
        for (int i = 0 ; i < numOfProcesses ; i++)
        {
            totalWaitingTime = totalWaitingTime + waitingTime[i];
            totalTurnaroundTime = totalTurnaroundTime + turnaroundTime[i];
            int completionTime = turnaroundTime[i] + processList[i].arrivalTime;
            System.out.println(i+1 + "\t\t" + processList[i].burstTime + "\t\t"
                    + processList[i].arrivalTime + "\t\t" + waitingTime[i] + "\t\t "
                    + turnaroundTime[i] + "\t\t " + completionTime);
        }

        System.out.print("Average waiting time = "
                + (float)totalWaitingTime / (float)numOfProcesses);
        System.out.print("\nAverage turn around time = "
                + (float)totalTurnaroundTime / (float)numOfProcesses);
    }
	}

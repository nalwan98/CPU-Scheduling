package assignment2;
//import process.Process;
public class PreSJF {


	 /**
     * Find waiting time for individual processes in proccesList
     * @param processList - list of processes
     * @param numOfProcesses - number of processes in processList
     * @param waitingTime - array to store the waiting time of each process in processList
     */
    private static void findWaitingTime(Process[] processList, int numOfProcesses, int[] waitingTime) {
        int[] remainingTime = new int[numOfProcesses];

        // remaining times of processes start from the initial burst time
        for (int i = 0; i < numOfProcesses; i++)
            remainingTime[i] = processList[i].burstTime;

        int complete = 0, t = 0, minimumRemainingTime = Integer.MAX_VALUE;
        int shortest = 0, processFinishTime;
        boolean check = false;

        // keeps running time all processes have finished executing
        while (complete != numOfProcesses) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < numOfProcesses; j++)
            {
                if ((processList[j].arrivalTime <= t) &&
                        (remainingTime[j] < minimumRemainingTime) && remainingTime[j] > 0) {
                    minimumRemainingTime = remainingTime[j];
                    shortest = j;
                    check = true;
                }
            }

            if (!check) {
                t++;
                continue;
            }

            remainingTime[shortest]--;

            // updates the minimum remaining time
            minimumRemainingTime = remainingTime[shortest];
            if (minimumRemainingTime == 0)
                minimumRemainingTime = Integer.MAX_VALUE;

            // process finished
            if (remainingTime[shortest] == 0) {

                complete++;
                check = false;

                // calculate finish time of process
                processFinishTime = t + 1;

                // calculate waiting time
                waitingTime[shortest] = processFinishTime -
                        processList[shortest].burstTime -
                        processList[shortest].arrivalTime;

                if (waitingTime[shortest] < 0)
                    waitingTime[shortest] = 0;
            }
            t++;
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
        for (int i = 0; i < numOfProcesses; i++)
            turnaroundTime[i] = processList[i].burstTime + waitingTime[i];
    }

    /**
     * Finds the average waiting time and average turnaround time for the given list of processes in processList.
     * The average times are printed to the console along with tabular representation of process list inputs
     * @param processList - list of processes
     * @param numOfProcesses - number of processes in processList
     */
    public static void findAverageTimes(Process[] processList, int numOfProcesses)
    {
        int[] waitingTime = new int[numOfProcesses];
        int[] turnaroundTime = new int[numOfProcesses];
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        // get waiting times
        findWaitingTime(processList, numOfProcesses, waitingTime);

        // get turnaround times
        findTurnAroundTime(processList, numOfProcesses, waitingTime, turnaroundTime);

        // print process details to console
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time");
        for (int i = 0; i < numOfProcesses; i++) {
            totalWaitingTime = totalWaitingTime + waitingTime[i];
            totalTurnaroundTime = totalTurnaroundTime + turnaroundTime[i];
            System.out.println(" " + processList[i].processId + "\t\t"
                    + processList[i].burstTime + "\t\t " + waitingTime[i]
                    + "\t\t" + turnaroundTime[i]);
        }

        System.out.println("Average waiting time = " +
                (float)totalWaitingTime / (float)numOfProcesses);
        System.out.println("Average turn around time = " +
                (float)totalTurnaroundTime / (float)numOfProcesses);
    }

}

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;




public class Simulation {
	
	/* this class runs a simulation of the printing program. It takes input from 
	 * the user, generates print requests and records the wait times of each print
	 *  request. the fields required for this class are the input variables, the
	 *  number of queues to be used, a character denoting which queue system will
	 *  be used, and the number of dequeue operations before promoting a print request,
	 *  and the probability that a print request is created in one second.
	 *  the other fields required are the time, the average wait, and the maximum wait.
	 *  I also initialize an arraylist to store all of the print requests that 
	 *  were generated (for testing purposes) as well as a new queue to store 
	 *  linked lists (containing print requests) in the case of algorithm a or b 
	 *  being selected.
	 */
	
	public static int nq;
	public static int k;
	public static int time = 0;
	public static char alg;
	public static double prob;
	public static int maxWait;
	public static int aveWait;
	ArrayList<PrintRequest> allPr = new ArrayList<PrintRequest>();
	Queue myQueue = new Queue();
	
	
	/* 
	 * the simulation object is used to store user-entered data. it stores the number
	 * of queues, the number of dequeue operations before promoting a print request,
	 * the printing algorithm that the user wants to use, and the probability of 
	 * a print request being generated per second.
	 */
	
	public Simulation(int newnq, int newk, char newalg, double newprob){
		nq = newnq;
		k = newk;
		alg= newalg;
		prob=newprob;
	} 
	
	
	/*
	 * the getnq method returns the number of queues that the user wants to use for
	 * either algorithm a or b, and this method is used to initialize the array
	 * "queues" in the Queue class.
	 */
	
	public static int getnq(){
		return nq;
	}
	
	
	/*
	 * the ranGen method utilizes the random package's randDec function, which 
	 * returns a random decimal between 1 and 0, this number is multiplied by 100 
	 * and then augmented by one (to prevent print requests of 0 pages). the new 
	 * double is cast into an int and then returned. this is used to determine the 
	 * number of pages of each printrequest and also used to determine whether or
	 * not a print request is generated in a time unit.
	 */
	
	public int ranGen(){
		double randDec;
		int randInt=0;
		randDec = Math.random();
		randInt = (int) ((randDec*100)+1);
		return randInt;
	}


	/*
	 * the simulation method generates print requests, queues them via the methods
	 * in the queue class and then dequeues them, printing them depending on which 
	 * algorithm was chosen by the user. Because i am only generating print requests
	 * for 1000 seconds, I have a for loop in which print requests are generated 
	 * depending on the probability of a print request being generated in one
	 * second then queued. within the loop, where each increment of i is a second, 
	 * the printer begins processing requests, printing one page per second of the highest
	 * priority print request. Because the printing speed is one page per second, the 
	 * printing takes more than 1000 seconds. For this reason, after the for loop 
	 * I wrote some if statements to make sure all of the linked lists containing 
	 * print requests are emptied and all the print requests are dequeued and printed.
	 * At the ned of this method, i call the averwait and maxwait methods of the 
	 * printer class to determine the maximum and average wait time for the print
	 * print requests. 
	 */
	
	public void simulation(){
		Printer printer = new Printer();
		for (int i=0; i<=1000; i++){
				if ((prob*100>=ranGen())){
					PrintRequest pr = new PrintRequest(ranGen(), i);
					pr.toString();
					switch(alg){
					case 'a':
						myQueue.queuer(pr);
						allPr.add(pr);
						break;
					case 'b':
						myQueue.queuer(pr);
						allPr.add(pr);
						break;
					case 'c':
						myQueue.queuer3(pr);
						allPr.add(pr);
						break;
				}	
				} else {
					
				}
			switch(alg){
			case 'a':
				printer.printerIdle();
				if (printer.printerIdle() == true){
					printer.printFile(myQueue.dequeuer(k));
				} else{
				}
				break;
			case 'b':
				printer.printerIdle();
				if (printer.printerIdle() == true){
					printer.printFile(myQueue.dequeuer2());
				} else{
				}
				break;
			case 'c':
				printer.printerIdle();
				if (printer.printerIdle() == true){
					printer.printFile(myQueue.dequeuer3());
				} else{
				}
				break;
			}
		}
		
		switch(alg){
		case 'a':
			for (int w =0; w<nq; w++){
				if (myQueue.queues[w] != null){
					while (printer.printFile(myQueue.dequeuer(k))==true){
						printer.printerIdle();
						if (printer.printerIdle() == true){
							printer.printFile(myQueue.dequeuer(k));
						} else{
						}
					}
				} else {
				}
			}
			break;
		case 'b':
			for (int w =0; w<nq; w++){
				if (myQueue.queues[w] != null){
					while (printer.printFile(myQueue.dequeuer2())==true){
						printer.printerIdle();
						if (printer.printerIdle() == true){
							printer.printFile(myQueue.dequeuer2());
						} else{
						}
					}
				} else {
				}
			}
			break;
		case 'c':
			if (myQueue.queue3 != null){
				while (printer.printFile(myQueue.dequeuer3())==true){
					printer.printerIdle();
					if (printer.printerIdle() == true){
						printer.printFile(myQueue.dequeuer3());
					} else{
					}
				}
			} else {
			}
			break;
		}
		
		
		
	
		aveWait = printer.aveWait();
		maxWait = printer.maxWait();
	}
	
	
	/*
	 * the main method of this class takes input from the user to define the 
	 * fields, runs the simulation method, and then displays the output, which is 
	 * the average wait and the maximum wait. it also 
	 * contains many print statements for testing purposes, which will be 
	 * slashed out
	 * 
	 */

	
	public static void main(String[]args){
		System.out.println("which queue algorithm would you like to use? (enter lowercase a, b, or c)");
		Scanner scanalg = new Scanner(System.in);
		alg = scanalg.next().charAt(0);
		switch(alg){
		case 'a':
			System.out.println("how many queues should there be?");
			Scanner scannq = new Scanner(System.in);
			nq = scannq.nextInt();
			System.out.println("how many dequeue operations before promoting a print job to a higher priority queue?");
			Scanner scank = new Scanner(System.in);
			k = scank.nextInt();
			break;
		case 'b':
			System.out.println("how many queues should there be?");
			Scanner scannqb = new Scanner(System.in);
			nq = scannqb.nextInt();
			break;
		case 'c':
			nq = 1;
			break;
		}
		System.out.println("what is the probability that a print request is created in one second? (between o and 1)");
		Scanner scanprob = new Scanner(System.in);
		prob = scanprob.nextDouble();
		Simulation simulation = new Simulation(nq, k, alg, prob);
		simulation.simulation();
		//System.out.println(simulation.myQueue.queues[0]);//just a test
		///System.out.println(simulation.myQueue.queues[1]);
		//System.out.println(simulation.myQueue.queues[2]);
		//System.out.println(simulation.myQueue.queues[3]);
		//System.out.println(Printer.waitTimes.size());
		//System.out.println(simulation.allPr.size());
		//System.out.println(Queue.dequeued.size());
		//System.out.println(simulation.allPr);
		//System.out.println(Queue.dequeued);
		//System.out.println(Printer.waitTimes);
		System.out.println("The longest wait time for any print request was " + maxWait);
		System.out.println("The average waiting time for any print request was " + aveWait);
	}	
}


/*
 * Code written by
 * David Munkvold
 * June 23rd, 2015
 */



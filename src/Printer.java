import java.util.ArrayList;
import java.util.Collections;

public class Printer {
	
	/*
	 * the printer class simulates a real printer object. the printer object itself
	 * has no characteristics, but it can check to see if there are printrequests to
	 * be printed, print one page per unit of time, keep track of how much time 
	 * has elapsed since the first print job, and keep track of the wait time for 
	 * each print request. The fields I use are the printTime, 
	 * which is the time (in seconds) that have elapsed since the first print job,
	 * p and l, which i use as placeholders for time elapsed during a single print job 
	 * and the number of pages of that print job, a printrequest object, and an arraylist
	 * of integers that stores all of the wait times.
	 */
	
	public int printTime = 0;
	public int p=0;
	public int l=1;
	public PrintRequest pr = null;
	static ArrayList<Integer> waitTimes = new ArrayList<Integer>();
	
	public Printer(){
	}
	
	
	/*
	 * the printeridle method checks to see if the printer is currently processing
	 * a request or if it is idle. It does this by incrementing l each second until l is 
	 * equal to the number of pages (p). It also increments the print time in order to keep
	 * track of the seconds that have elapsed. If the printer is working on a request
	 * the method returns false and if it is not than it returns true.
	 */
	
	public boolean printerIdle(){
		if (l<p){
			l++;
			printTime = printTime+1;
			return false;
		} else {
			return true;
		}
	}
	
	
	/*
	 * the printFile method takes a print request and if the print request is not
	 * null, then it clocks the time it took for the print request to get to the 
	 * printer and resets l to 0 and p to the number of pages of that request, telling 
	 * the printer that it has a prequest to process. When the print request entered is
	 * null, the method returns false, and when a non null print request is used as the 
	 * input, it returns true.
	 */
	
	public boolean printFile(PrintRequest printrequest){
		pr = printrequest;
		if (pr == null){
			return false;
		} else {
			timeClock(pr);
			p=pr.getPages();
			l=0;
			return true;
		}
	}
	
	
	/*
	 * the timeclock method records the number of seconds that elapsed between the 
	 * print request that is used as its input's generation time and the time it was
	 * actually processed by the printer. It called the print request's gettime method
	 * to get the time of generation, subtracts that from the time that it was processed
	 * at, and then stores this wait time in an arraylist 
	 * */
	
	public void timeClock(PrintRequest printrequest){
		if ((printTime - pr.getTime())<=0){
			waitTimes.add(0);
		} else{
			waitTimes.add(printTime - pr.getTime());
		}
	}
	
	
	/*
	 * the aveWait method evaluates the average wait of all the print requests by adding
	 * all of the elements of the waittimes list, and then dividing by its size,
	 * returning an integer.
	 */
	
	public int aveWait(){
		int total = 0;
		int average;
		for (int i = 0; i < waitTimes.size(); i++){
			total = waitTimes.get(i) + total;
		}
			average = total/waitTimes.size();
		return average;
	}
	
	
	/*
	 * the maxWait method evaluates the maximum wait time of ay print request by 
	 * finding the max integer in the wait times list, returning it as an integer.
	 */
	
	public int maxWait(){
		int maximum;
		maximum = Collections.max(waitTimes);
		return maximum;
	}
}


/*
 * Code written by
 * David Munkvold
 * June 23rd, 2015
 */



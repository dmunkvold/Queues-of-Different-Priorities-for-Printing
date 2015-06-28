
import java.util.Arrays;
import java.util.LinkedList;


public class Queue {
	/*for this queue class, I am using a linkedlist to store printrequest objects,
	 * and an array to store the different linkedlists when this is necessary. 
	 * the fields required for this class are the number of the number of queues
	 * that will be utilized, which is defined by the getnq method of the simulation class,  
	 * a linked list for queue option 3 and an array of empty linked lists
	 * based on the number of queues. I also initialize an arrayList of Integers
	 * that I use to store the page numbers of print requests for testing purposes.
	 */
	
	public static int numQueues = Simulation.getnq();
	LinkedList<PrintRequest> queue3 = new LinkedList<PrintRequest>();
	LinkedList<PrintRequest>[] queues = new LinkedList[numQueues];
	//static LinkedList<Integer> dequeued = new LinkedList<Integer>();
	
	
	/*
	 * the queue class does not require that the object store its fields, so the 
	 * constructor is empty.
	 */
	
	public Queue(){
	}
	
	
	/* this method is called when queue option a or b is chosen. it takes a printrequest
	 *  and assigns it to a linkedlist. if the array is null at the given index, 
	 *  a linkedlist is created for that index. if the linkedlist does not return
	 *  null at the given index, it simply adds the printrequest to the linkedlist 
	 *  at the given index in the array.  
	 */
	public void queuer(PrintRequest pr){
		for (int i = 0;i<numQueues; i++){
			int n = ((pr.getPages()+10)/10)-1;
			if (queues[i] == null && n==i){
				queues[i] =  new LinkedList<PrintRequest>(Arrays.asList(pr));
				break;
			} else if (queues[i] != null && n==i){
				queues[i].add(pr);
				break;
			} else if (n>=numQueues && queues[queues.length-1]==null){
				queues[queues.length-1] = new LinkedList<PrintRequest>(Arrays.asList(pr));
				break;
			} else if (n>=numQueues && queues[queues.length-1]!=null){
				queues[queues.length-1].add(pr);
				break;
			} else {
				continue;
			}
		}
			
	}
	
	
	/* this method is called if queue option c is chosen. it merely adds a printrequest
	 * to a LinkedList
	 */
	
	public void queuer3(PrintRequest pr){
		queue3.add(pr);
	}
	
	
	/*this dequeueing method is called when queue option a is chosen.
	 * it takes the input of the number of dequeue operations before transferring
	 * print requests to a high priority queue. It removes the highest priority 
	 * print request and returns it. It also moves the printrequest to a higher
	 * priority queue if the number of dequeues, k, have been performed. when the 
	 * Linkedlists are empty, it returns null.
	 */
	
	public PrintRequest dequeuer(int k){
		int dqs=0;
		PrintRequest pr = null;
		for (int i = 0; i < numQueues; i++ ){
			if (queues[i] == null || queues[i].isEmpty()){
				continue;
			} else {
				pr = queues[i].pollFirst();
				dqs=dqs+1;
				if (k==dqs){
					for (int p = 1; p<(queues.length- (i)); p++){
						if (queues[i+p].isEmpty()){
							continue;
						} else {
							queues[i].add(queues[i+p].pollFirst());
							dqs=0;
							break;
						}
					}
				} else {
					break;
				}
			}
		}
		if (pr == null){
		} else {
		//dequeued.add(pr.getPages());
		}
		return pr;		
	}
	
	
	/* this method is called when queue option b is chosen. it removes the highest 
	 * priority print request and returns it, or else it returns null.
	 */
	
	public PrintRequest dequeuer2(){
		PrintRequest pr = null;
		for (int b =0; b < numQueues; b++ ){
			if (queues[b] == null || queues[b].isEmpty() == true){
				continue;
			} else {
				pr = queues[b].pollFirst();
				break;
			}
		}
		if (pr == null){
		}else{
		//dequeued.add(pr.getPages());
		}
		return pr;
	}
	
	
	/* this method is called when queue option c is chosen. it simply removes the 
	 * first item in the list and returns it. when the linkedlist is empty, it returns null.
	 */
	
	public PrintRequest dequeuer3(){
		PrintRequest pr = queue3.pollFirst();
		return pr;
		
	}
}


/*
 * Code written by
 * David Munkvold
 * June 23rd, 2015
 */


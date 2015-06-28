
public class PrintRequest {
	
	/*
	 * The printrequest class is used to store information regarding print requests.
	 * the fields that a print request object needs to have are the number of pages 
	 * of the print request and the time that the print request was generated. These
	 * will be crucial to calculating a certain print requests wait time.
	 */
	
	public int pages;
	public int clockTime;

	public PrintRequest(int numpages, int atime){
		pages = numpages;
		clockTime = atime;
	}
		
	
	/*
	 * the getPages method returns the number of pages of the print request. If the 
	 * printerquest object is null, there is an error when this method is called.
	 */
	
	public int getPages(){
		return pages;
	}
	
	
	/*
	 * the getTime method returns the time that the print request object was generated.
	 * if the object is null, this method returns an error.
	 */
	
	public int getTime(){
		return clockTime;
	}
	
	
	/*
	 * this toString method I used for testing purposes, it takes a print request 
	 * object and prints its pages and generation time.
	 */
	
	public String toString(){
		
		return "Pages: "+ pages +
				"\nTime: "+ clockTime;
	}
}


/*
 * Code written by
 * David Munkvold
 * June 23rd, 2015
 */


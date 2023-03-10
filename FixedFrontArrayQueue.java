
public class FixedFrontArrayQueue<T> implements QueueInterface<T> {
	private static int CAPACITY = 100;
	private T[] elements;
	private int rearIndex;
 
	// Default constructor to initialize array capacity with CAPACITY
	public FixedFrontArrayQueue() {
		elements = (T[]) new Object[CAPACITY];
		rearIndex = 0 ;            //either 0 or -1
	}

	// Constructor to initialize array capacity with 'size'
	public FixedFrontArrayQueue(int size) {
      elements = (T[]) new Object[size];
      
	}

	// Dequeue info from array. Throw QueueUnderflowException if array is empty
	public T dequeue() {
		
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T data = elements[0];
		for (int i = 0 ; i<size()-1;i++) { //change to rearindex or size (with actual elements)
			elements[i] = elements[i+1];
		}
		rearIndex--;
	   return  data;
	}

	// Enqueue info into array. Throw QueueOverflowException if array is full
	public void enqueue(T info) {
		
		
        if (isFull()) {
        	throw new QueueOverflowException();
        }
        	
        elements[rearIndex] = info;
        rearIndex++;	
           
	}

	// Return true if the array is empty; otherwise return false
	public boolean isEmpty() {
             
             return size() == 0;

	}

	// Return true if the array is full; otherwise return false
	public boolean isFull() {
		
         return size() == elements.length;
	}

	// number of elements == size
	public int size() {
         return rearIndex;  //determine by how many times enqueue is added in with "info".
	}

	// Adjust elements array capacity with 'size'
	public void resize(int size) {
		T[] new_elements = (T[]) new Object[elements.length+size];
		for (int i = 0 ; i<size(); i++) {
			    new_elements[i] = elements[i];
		}
	
		elements = new_elements;
	}

	// Creates and returns a string that correctly represents the current stack.
	public String toString() {
		String result = "Size: " + size() + "\n";
		for (int i=0; i<size(); i++) { //not length but size
			       
			result = result + elements[i] + "\n";
			
		}
		
         return result;
	
	}

	// Return array capacity
	public int getQueueCapacity() { //true
         
     
        return elements.length;
	}
}


import java.util.ArrayList;

public class ArrayListQueue<T> implements QueueInterface<T> {
	private ArrayList<T> elements;

	// Default constructor to initialize array capacity with CAPACITY
	public ArrayListQueue() {
         elements = new ArrayList<T>();                                  
	}

	// Dequeue info from array. Throw QueueUnderflowException if array is empty
	public T dequeue() {
		
		if (elements.isEmpty()) //empty
		{
			throw new QueueUnderflowException();			
		}
		
		else
			
		return elements.remove(0); 
		
	}

	// Enqueue info into array. Throw QueueOverflowException if array is full
	public void enqueue(T info) {
		
	if( isFull() ) {
        throw new QueueOverflowException();
        
	}
	
	else {
		
		elements.add(info);
		
		
	}
	
	}
	

	// Return true if the array is empty; otherwise return false
	public boolean isEmpty() {
        if (elements.isEmpty())  
        {
        	return true;
        }
        
        return false;
	}

	// Return true if the array is full; otherwise return false
	public boolean isFull() {
		
		if (elements.size() > 3)
           {
			return true;
           }
		
		return false;
	}

	// Return number of elements
	public int size() {
         return elements.size();
	}

	// Creates and returns a string that correctly represents the current stack.
	public String toString() {
		String result = "";
		//result is the a long string of all elements in the queue
		System.out.println("Size: " + size());
		for (int i=0; i<elements.size(); i++) {			
			System.out.println(elements.get(i));			
		}		
         return result;
	}
	
}

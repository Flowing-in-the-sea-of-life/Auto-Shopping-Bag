 
import java.io.*;
import java.util.*;

public class FixedFrontArrayQueueVersion {
	public static void main(String[] args) throws FileNotFoundException {
		String actualOutputFileName = "./src/Program2/ActualOutput.txt";
		String sampleOutputFileName = "./src/Program2/SampleOutput2b.txt";

		File actualOutputFile = new File(actualOutputFileName);
		File sampleOutputFile = new File(sampleOutputFileName);

		int score = 0;

		score += checkProjectSettings1();
		score += checkFiles(actualOutputFile, sampleOutputFile);

		if (score < 2) {
			System.out.println("Check project and package settings.");
			System.exit(0);
		}

		score += testFixedFrontArrayQueue();
		score += testArrayListQueue();

		System.setOut(new PrintStream(actualOutputFile));
		testFixedFrontArrayQueue();
		testArrayListQueue();
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		
		if (compareOutput(sampleOutputFileName, actualOutputFileName))
			score += 10;

		System.out.printf("Your score is %d/100.\n", score);
	}

	private static int testFixedFrontArrayQueue() {
		int score = 0;

		QueueInterface<Character> c = new FixedFrontArrayQueue<Character>();
		if (((FixedFrontArrayQueue<Character>) c).getQueueCapacity() == 100)
			score += 5;
		else
			System.out.println("Check default constructor.");

		QueueInterface<String> q = new FixedFrontArrayQueue<String>(2);
		if (((FixedFrontArrayQueue<String>) q).getQueueCapacity() == 2)
			score += 5;
		else
			System.out.println("Check overloaded constructor.");

		try {
			q.dequeue(); // Note: Underflow exception should occur
		} catch (Exception e) {
			score += 5;
		}

		if (q.isEmpty() && !q.isFull() && q.size() == 0)
			score += 5;
		else
			System.out.println("Check isEmpty(), isFull() and size() methods.");

		System.out.println("Enqueue Orange");
		q.enqueue("Orange");
		System.out.println(q); // Calling the .toString()

		System.out.println("Enqueue Mango");
		q.enqueue("Mango");
		System.out.println(q);

		System.out.println("Enqueue Guava into full queue");
		try {
			q.enqueue("Guava"); // Note: with Queue size 2, this won't get into the queue.
		} catch (Exception e) {
			score += 5;
		}
		System.out.println(q);

		if (q.isFull())
			score += 5;
		else
			System.out.println("Check enqueue() and isFull() method.");

		System.out.println("Dequeue");
		if (q.dequeue().equals("Orange") && q.size() == 1 && !q.isEmpty())
			score += 5;
		else
			System.out.println("Check dequeue(), size() and isEmpty() methods");
		System.out.println(q);

		System.out.println("Dequeue");

		if (q.dequeue().equals("Mango") && q.size() == 0 && q.isEmpty())
			score += 5;
		else
			System.out.println("Check dequeue(), size() and isEmpty() methods");
		System.out.println(q);

		System.out.println("Enqueue Strawberry");
		q.enqueue("Strawberry");
		System.out.println(q);

		System.out.println("Enqueue Lemon");
		q.enqueue("Lemon");
		System.out.println(q);

		System.out.println("Resize and Enqueue Banana");
		((FixedFrontArrayQueue<String>) q).resize(2); // Increase the array size by 2
		q.enqueue("Banana");
		if (((FixedFrontArrayQueue<String>) q).getQueueCapacity() == 4 && q.size() == 3 && !q.isEmpty() && !q.isFull())
			score += 5;
		else
			System.out.println("Check enqueue(), resize(), getQueueCapacity(), size(), isEmpty and isFull()");
		System.out.println(q);

		System.out.println("Resize");
		((FixedFrontArrayQueue<String>) q).resize(-1); // Decrease the array size by 1.
		if (((FixedFrontArrayQueue<String>) q).getQueueCapacity() == 3 && q.size() == 3 && !q.isEmpty() && q.isFull())
			score += 5;
		else
			System.out.println("Check resize(), getQueueCapacity(), size(), isEmpty and isFull()");
		System.out.println(q);

		return score;
	}

	private static int testArrayListQueue() {
		int score = 0;

		ArrayListQueue<String> q = new ArrayListQueue<String>();
		
		if (q.size() == 0)
			score += 5;
		else
			System.out.println("Check default constructor.");

		try {
			q.dequeue(); // Note: Underflow exception should occur
		} catch (Exception e) {
			score += 5;
		}

		if (q.isEmpty() && !q.isFull() && q.size() == 0)
			score += 5;
		else
			System.out.println("Check isEmpty(), isFull() and size() methods.");

		System.out.println("Enqueue Orange");
		q.enqueue("Orange");
		System.out.println(q); // Calling the .toString()

		System.out.println("Enqueue Mango");
		q.enqueue("Mango");
		System.out.println(q);

		System.out.println("Enqueue Guava");
		q.enqueue("Guava");
		System.out.println(q);

		if (!q.isFull())
			score += 5;
		else
			System.out.println("Check isFull() method.");

		System.out.println("Dequeue");
		if (q.dequeue().equals("Orange") && q.size() == 2 && !q.isEmpty())
			score += 5;
		else
			System.out.println("Check dequeue(), size() and isEmpty() methods");
		System.out.println(q);

		System.out.println("Dequeue");
		if (q.dequeue().equals("Mango") && q.size() == 1 && !q.isEmpty())
			score += 5;
		else
			System.out.println("Check dequeue(), size() and isEmpty() methods");
		System.out.println(q);

		System.out.println("Enqueue Strawberry");
		q.enqueue("Strawberry");
		System.out.println(q);

		System.out.println("Enqueue Lemon");
		q.enqueue("Lemon");
		System.out.println(q);

		return score;
	}

	private static int checkProjectSettings1() {
		final String PACKAGE_NAME = "Program2";
		final String INTERFACE_NAME = "QueueInterface";

		// Check if Package was correct
		FixedFrontArrayQueue<String> test = new FixedFrontArrayQueue<String>();

		String packageName = test.getClass().getPackageName();
		if (!packageName.equalsIgnoreCase(PACKAGE_NAME)) {
			System.out.println("Set package to " + PACKAGE_NAME);
			System.exit(0);
		}

		// Check if Interface was included
		String interfaceName = "";
		try {
			interfaceName = test.getClass().getInterfaces()[0].getName();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Interface not found");
			System.exit(0);
		}

		if (!interfaceName.substring(interfaceName.lastIndexOf('.') + 1).equalsIgnoreCase(INTERFACE_NAME)) {
			System.out.println("Set to implement " + INTERFACE_NAME + " interface");
			System.exit(0);
		}

		return 5;
	}

	private static int checkFiles(File actual, File sample) {
		if (!sample.exists()) {
			System.out.println(sample.getName() + " missing");
			System.exit(0);
		}

		actual.delete();

		return 5;
	}

	private static boolean compareOutput(String file1, String file2) {
		Scanner inputStream1 = null;
		Scanner inputStream2 = null;

		try {
			inputStream1 = new Scanner(new File(file1));
			inputStream2 = new Scanner(new File(file2));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		int lineNumber = 1;

		boolean same = true;
		while (inputStream1.hasNextLine() && inputStream2.hasNextLine()) {
			if (!inputStream1.nextLine().trim().equals(inputStream2.nextLine().trim())) {
				same = false;
				break;
			}
			lineNumber++;
		}

		if (!same || inputStream1.hasNextLine() || inputStream2.hasNextLine())
			System.out.println("Check line # " + lineNumber + " of ActualOutput.txt");

		inputStream1.close();
		inputStream2.close();

		return same;
	}
}

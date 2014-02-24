/*
 * Extending Thread Demo
 */
public class ExtendThread {
  public static void main(String[] args) {
    new NewThread(); // Create a new thread

    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Main Thread: " + i);
        Thread.sleep(100);
      }
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted.");
    }
    System.out.println("Main Thread Exiting!");
  }
}

// Create a second thread by extending Thread
class NewThread extends Thread {
  NewThread() {
    // Create the thread
    super("Demo Thread");
    System.out.println("Child Thread: " + this);
    start(); // Start the Thread
  }

  // Entry Point
  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Child Thread: " + i);
        Thread.sleep(50);
      }
    } catch (InterruptedException e) {
    	System.out.println("Child thread interrupted!");
    }
    System.out.println("Child thread exiting!");
  }
}

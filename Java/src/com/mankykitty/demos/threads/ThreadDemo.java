/**
 * Created by manky on 24/02/2014 inspired from the following tutorial on using
 * Threads and Runnable with Java:
 * http://www.tutorialspoint.com/java/java_multithreading.htm
 *
 * Forgive the inclusion of the runnable class in this file.. It's for the sake
 * of the tutorial. Often this would be created in its own file for reasons of
 * best practice and adherence to standards.
 */
public class ThreadDemo {
  public static void main(String[] args) {
    new NewThread(); // Create the new thread

    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Main thread: " + i);
        Thread.sleep(100);
      }
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted!");
    }
    System.out.println("Main thread exiting.");
  }
}

// Create a new thread
class NewThread implements Runnable {
  Thread t;
  NewThread() {
    // Create a new, second thread
    t = new Thread(this, "Demo Thread");
    System.out.println("Child Thread: " + t);
    t.start(); // lets goooooooo
  }

  // This is the entry point for the second thread
  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Child Thread: " + i);
        // Let the thread sleep for a while
        Thread.sleep(50);
      }
    } catch (InterruptedException e) {
      System.out.println("Child InterruptedException!!");
    }
    System.out.println("Exiting child thread.");
  }
}

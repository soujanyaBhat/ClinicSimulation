import java.util.Random;
import java.util.concurrent.Semaphore;

public class Nurse implements Runnable {
    
    private int n;
    Nurse(int n) {
        this.n = n;
    }
    
    /*Working of nurse thread and providing mutual exclusion using semaphores*/

    @Override
    public void run() {
      while(true){
        wait(Office.patientReady);
        wait(Office.doc_ready);
        wait(Office.mutex);
        dequeue();
        signal(Office.mutex);
        signal(Office.nurse_coming);
        
       }
        
     }
    void dequeue() {
        int patientId = Office.queue.remove();
        System.out.println("Nurse " + n + " takes Patient " + patientId + " to Doctor's office");
      }
   
    /*Semaphore wait*/

    void wait(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {

        }
    }
    /*Signal semaphore*/

    void signal(Semaphore s) {
        s.release();
    }
}
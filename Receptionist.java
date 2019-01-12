import java.util.Random;
import java.util.concurrent.Semaphore;

public class Receptionist implements Runnable {
    
    /*Receptionist constructor*/
    private int r;
    Receptionist(int r) {
        this.r = r;
    }
    
    /*Working of receptionist thread and providing mutual exclusion using semaphores*/

    @Override
    public void run() {
      while(true){
        wait(Office.patient_entered_clinic);
        wait(Office.mutex);
        register();
        signal(Office.receptionist);
        signal(Office.mutex);
       }
        
     }
     
    /*Register the patient and Add the Patient ID to the queue*/ 
    void register() {
    int patientId;
    
      patientId= Office.queue_recep.remove();
      System.out.println("Receptionist registers Patient " + patientId);
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
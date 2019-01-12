import java.util.Random;
import java.util.concurrent.Semaphore;

public class Patient implements Runnable {

    public int patNum;
    public int worker_assigned;
    
    /*Patient constructor*/

    Patient(int patNum) {
        this.patNum = patNum;
    }
    /*Working of patient thread and providing mutual exclusion using semaphores*/

    @Override
    public void run() {
        
        wait(Office.maxCapacity);
        patient_enterClinic();
        
        wait(Office.mutex);
        enqueue(patNum);
        signal(Office.mutex);
        
        signal(Office.patient_entered_clinic);
        wait(Office.receptionist);
        get_registered();
        
        
        signal(Office.patientReady);
        signal(Office.coord2);
        
        wait(Office.finished[patNum]);
        finish_work();
        leave();
        signal(Office.leave_DocCabin);
        signal(Office.maxCapacity);
        Office.count++;
    }
    
    /*Print Patient leaving the office*/

    void leave() {
        System.out.println("Patient " + patNum + " leaves clinic");
    }
    /*Print Patient finishes the work*/

    void finish_work() {
         
        System.out.println("Patient " + patNum + " receives advice from doctor "+ Office.objPatient[patNum].worker_assigned);
    }
    
    void get_registered(){
      System.out.println("Patient "+patNum +" leaves receptionist and sits in waiting room");
    }
    
    /*Print 'Patient created'*/

    void cust_created() {
        System.out.println("Patient " + patNum + " created");
    }
    void enqueue(int custId) {
        Office.queue.add(custId);
        Office.queue_recep.add(custId);
        Office.queue_doc.add(custId);
        
    }


    /*Semaphore wait*/

    void wait(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {

        }
    }
    /*Print patient enters clinic*/

    void patient_enterClinic() {
        System.out.println("Patient " + patNum + " enters clinic, waits for Receptionist");
    }
    /*Signal semaphore*/

    void signal(Semaphore s) {
        s.release();
    }
}
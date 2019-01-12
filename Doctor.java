import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Doctor implements Runnable {
    /*Structure of Doctor object*/

    public int workerNum;
    public static Queue<Integer> workerQueue = new LinkedList<>();
    private int next_cust_number;
   
    /*Doctor constructor*/

    Doctor(int workerNum) {
        this.workerNum = workerNum;
    }
    /*Working of worker thread and providing mutual exclusion using semaphores*/

    @Override
    public void run() {
        //work_created();
        while (true) {
            signal(Office.doc_ready);
            wait(Office.nurse_coming);
            
            wait(Office.mutex);
            listen();
            signal(Office.mutex);
            
            wait(Office.coord2);
            signal(Office.finished[next_cust_number]);
            wait(Office.leave_DocCabin);
            signal(Office.docCabin);
        }
    }
    /*Dequeue the Patient number*/

    void listen() {
        next_cust_number = Office.queue_doc.remove();
        System.out.println("Doctor " + workerNum + " listens to Patient " + next_cust_number + "'s symptoms");
        Office.objPatient[next_cust_number].worker_assigned = workerNum;
    }
    /*Print Doctor created*/

    void work_created() {
        System.out.println("Doctor " + workerNum + " created");
    }
    /*Signal semaphore*/

    void signal(Semaphore s) {

        s.release();

    }
         
    /*Semaphore wait*/

    void wait(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {

        }
    }
}
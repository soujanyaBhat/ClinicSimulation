import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.io.*;

public class Office {
    /*List of semaphores and other global variables*/

    public static Semaphore maxCapacity = new Semaphore(30, true);
    public static Semaphore patientReady = new Semaphore(0, true);
    public static Semaphore docCabin = new Semaphore(3, true);
    public static Semaphore mutex = new Semaphore(1, true);
    public static Semaphore coord = new Semaphore(0, true);
    public static Semaphore leave_DocCabin = new Semaphore(0, true);
    public static Semaphore coord2 = new Semaphore(0, true);
    public static Semaphore receptionist = new Semaphore(0, true);
    public static Semaphore patient_entered_clinic = new Semaphore(0, true);
    public static Semaphore nurse_coming = new Semaphore(0, true);
    public static Semaphore doc_ready = new Semaphore(0, true);

    public static int count;
    public static Queue<Integer> queue = new LinkedList<>();
    public static Queue<Integer> queue_recep = new LinkedList<>();
    public static Queue<Integer> queue_doc= new LinkedList<>();
    
    public static final int patNum = Main.patnum;
    public static final int docNum = Main.docnum;
    public static final int recepNum = 1;
    public static Semaphore[] finished = new Semaphore[patNum];

    

    static {
        for (int i = 0; i < patNum; i++) {
            finished[i] = new Semaphore(0, true);
        }
    }
    public static Patient[] objPatient = new Patient[patNum];
    public static Doctor[] objDoc = new Doctor[docNum];
    public static Receptionist[] recep = new Receptionist[recepNum];
    public static Nurse[] nur = new Nurse[docNum];
    public static Thread[] t1 = new Thread[patNum];
    public static Thread[] t2 = new Thread[docNum];
    public static Thread[] t3 = new Thread[recepNum];
    public static Thread[] t4 = new Thread[docNum];
   }
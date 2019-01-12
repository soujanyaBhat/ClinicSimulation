public class Main{
    
    public static int patnum;
    public static int docnum;
    
    public static void main(String args[]) {

        patnum = Integer.parseInt(args[0]);
        docnum = Integer.parseInt(args[1]);



        /*Patient Thread Creation*/
        
        for (int i = 0; i < patnum; i++) {
            Office.objPatient[i] = new Patient(i);
            Office.t1[i] = new Thread(Office.objPatient[i]);
            Office.t1[i].start();

        }
        /*Doctor and Nurse Thread Creation*/
        for (int i = 0; i < docnum; i++) {
            Office.objDoc[i] = new Doctor(i);
            Office.nur[i] = new Nurse(i);
            Office.t2[i] = new Thread(Office.objDoc[i]);
            Office.t4[i] = new Thread(Office.nur[i]);
            Office.t2[i].start();
            Office.t4[i].start();
        }
        /*Receptionist Thread Creation*/
        
        for (int i = 0; i < Office.recepNum; i++) {
            Office.recep[i] = new Receptionist(i);
            Office.t3[i] = new Thread(Office.recep[i]);
            Office.t3[i].start();
        }
        
        /*Patients joined*/
        for (int i = 0; i < patnum; i++) {
            try {
               Office.t1[i].join();
            } catch (InterruptedException e) {

            }
        }

        System.exit(0);
    }
}
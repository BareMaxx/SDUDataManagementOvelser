package dk.sdu.mmmi.dm.healthos.presentation;

import dk.sdu.mmmi.dm.healthos.domain.*;
import dk.sdu.mmmi.dm.healthos.persistance.PersistanceHandler;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(
            "------------------------------------------\n"
            + "WELCOME TO HealthOS\n"
            + "Please input your command or type \"help\"\n"
            + "------------------------------------------\n"
        );
        boolean running = true;
        IPersistanceHandler persistanceHandler = PersistanceHandler.getInstance();
        try(Scanner s = new Scanner(System.in)){
            while(running) {
                switch(s.nextLine().toLowerCase()) {
                    case "addemployee":
                        System.out.println("What is the employees ID?");
                        int EID = s.nextInt();
                        System.out.println("What is the employees name?");
                        String ename = s.next();
                        System.out.println("What is the employees phone number?");
                        int ephone = s.nextInt();
                        System.out.println("What is the employees position ID?");
                        int position = s.nextInt();
                        System.out.println("What is the employees department ID?");
                        int department = s.nextInt();
                        System.out.println("What is the employees room ID?");
                        int room = s.nextInt();
                        if(persistanceHandler.createEmployee(new Employee(EID, ename, ephone, position, department, room))) {
                            System.out.println("Employee successfully created");
                        } else {
                            System.out.println("Employee could not be created");
                        }
                        break;
                    case "addpatient":
                        System.out.println("What is the patient ID?");
                        int PID = s.nextInt();
                        System.out.println("What is the patient name?");
                        String pname = s.next();
                        System.out.println("What is the patient phone number?");
                        String pphone = s.next();
                        System.out.println("What is the patient's CPR?");
                        String cpr = s.next();
                        if(persistanceHandler.createPatient(new Patient(PID, pname, pphone, cpr))) {
                            System.out.println("Patient successfully created");
                        } else {
                            System.out.println("Patient could not be created");
                        }
                        break;
                    case "addbed":
                        System.out.println("What is the bed ID?");
                        int BID = s.nextInt();
                        System.out.println("What is the bed number?");
                        String bNumber = s.next();
                        if(persistanceHandler.createBed(new Bed(BID, bNumber))) {
                            System.out.println("Bed successfully created");
                        } else {
                            System.out.println("Bed could not be created");
                        }
                        break;
                    case "addadmission":
                        System.out.println("What is the admission ID?");
                        int AID = s.nextInt();
                        System.out.println("What is the patient ID?");
                        int PATIENTID = s.nextInt();
                        System.out.println("What is the room ID?");
                        int RID = s.nextInt();
                        System.out.println("What is the bed ID?");
                        int BEDID = s.nextInt();
                        System.out.println("What is the employees ID?");
                        int EMPID = s.nextInt();
                        if(persistanceHandler.createAdmission(new Admission(AID, PATIENTID, RID, BEDID, EMPID))) {
                            System.out.println("Admission successfully created");
                        } else {
                            System.out.println("Admission could not be created");
                        }
                        break;
                    case "getemployees":
                        System.out.println(persistanceHandler.getEmployees());
                        break;
                    case "getemployee":
                        System.out.println("What is the employee ID?");
                        System.out.println(persistanceHandler.getEmployee(Integer.parseInt(s.nextLine())));
                        break;
                    case "getpatients":
                        System.out.println(persistanceHandler.getPatients());
                        break;
                    case "getpatient":
                        System.out.println("What is the patient ID?");
                        System.out.println(persistanceHandler.getPatient(Integer.parseInt(s.nextLine())));
                        break;
                    case "getbeds":
                        System.out.println(persistanceHandler.getBeds());
                        break;
                    case "getbed":
                        System.out.println("What is the bed ID?");
                        System.out.println(persistanceHandler.getBed(Integer.parseInt(s.nextLine())));
                        break;
                    case "getadmissions":
                        System.out.println(persistanceHandler.getAdmissions());
                        break;
                    case "getadmission":
                        System.out.println("What is the admission ID?");
                        System.out.println(persistanceHandler.getAdmission(Integer.parseInt(s.nextLine())));
                        break;
                    case "removeadmission":
                        System.out.println("What is the admission ID?");
                        int id = s.nextInt();
                        if(persistanceHandler.deleteAdmission(id)) {
                            System.out.println("Admission with id: " + id + " is now deleted");
                        } else {
                            System.out.println("Admission with id: " + id + " could not get deleted");
                        }
                        break;
                    case "exit":
                        running = false;
                        break;
                    case "help":
                    default:
                        System.out.println(generateHelpString());
                        break;
                }
            }
        }
    }

    private static String generateHelpString() {
        return "Please write one of the following commands:\n"
                + "- getEmployees\n"
                + "- getEmployee\n"
                + "- getPatients\n"
                + "- getPatient\n"
                + "- getBeds\n"
                + "- getBed\n"
                + "- getAdmissions\n"
                + "- getAdmission\n"
                + "- exit\n";
    }
}

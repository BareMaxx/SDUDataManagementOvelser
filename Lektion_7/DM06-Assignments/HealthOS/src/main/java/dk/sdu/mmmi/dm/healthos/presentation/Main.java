package dk.sdu.mmmi.dm.healthos.presentation;

import dk.sdu.mmmi.dm.healthos.domain.Employee;
import dk.sdu.mmmi.dm.healthos.domain.IPersistanceHandler;
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
                        int ID = s.nextInt();
                        System.out.println("What is the employees name?");
                        String name = s.next();
                        System.out.println("What is the employees phone number?");
                        int phone = s.nextInt();
                        System.out.println("What is the employees position ID?");
                        int position = s.nextInt();
                        System.out.println("What is the employees department ID?");
                        int department = s.nextInt();
                        System.out.println("What is the employees room ID?");
                        int room = s.nextInt();
                        if(persistanceHandler.createEmployee(new Employee(ID, name, phone, position, department, room))) {
                            System.out.println("Employee successfully created");
                        } else {
                            System.out.println("Employee could not be created");
                        }
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

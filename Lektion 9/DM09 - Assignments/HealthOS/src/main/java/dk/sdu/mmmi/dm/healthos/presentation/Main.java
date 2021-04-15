package dk.sdu.mmmi.dm.healthos.presentation;

import dk.sdu.mmmi.dm.healthos.domain.Employee;
import dk.sdu.mmmi.dm.healthos.domain.IPersistanceHandler;
import dk.sdu.mmmi.dm.healthos.domain.Patient;
import dk.sdu.mmmi.dm.healthos.persistance.PersistanceHandler;
import org.bson.types.ObjectId;

import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Oliver Nordestgaard | olnor18
 */

public class Main {
    public static void main(String[] args) {
        Logger.getLogger("").setLevel(Level.WARNING);
        System.out.println(
                "------------------------------------------\n"
                        + "WELCOME TO HealthOS\n"
                        + "Please input your command or type \"help\"\n"
                        + "------------------------------------------\n"
        );
        boolean running = true;
        IPersistanceHandler persistanceHandler = PersistanceHandler.getInstance();
        try (Scanner s = new Scanner(System.in)) {
            while (running) {
                switch (s.nextLine().toLowerCase()) {
                    case "getemployees":
                        for (Employee employee : persistanceHandler.getEmployees()) {
                            System.out.println(employee);
                        }
                        break;
                    case "getemployee":
                        System.out.println("What is the employee ID?");
                        System.out.println(persistanceHandler.getEmployee(Integer.parseInt(s.nextLine())));
                        break;
                    case "createemployee":
                        String emp = Employee.class.getSimpleName().toLowerCase();
                        Employee employee = new Employee();
                        generateMessage("id", emp);
                        employee.setId(Integer.parseInt(s.nextLine()));
                        generateMessage("name", emp);
                        employee.setName(s.nextLine());
                        generateMessage("phone", emp);
                        employee.setPhone(Integer.parseInt(s.nextLine()));
                        generateMessage("position_id", emp);
                        employee.setPosition_id(Integer.parseInt(s.nextLine()));
                        generateMessage("department_id", emp);
                        employee.setDepartment_id(Integer.parseInt(s.nextLine()));
                        generateMessage("room_id", emp);
                        employee.setRoom_id(Integer.parseInt(s.nextLine()));
                        persistanceHandler.createEmployee(employee);
                        break;
                    case "getpatients":
                        for (Patient patient : persistanceHandler.getPatients()) {
                            System.out.println(patient);
                        }
                        break;
                    case "getpatient":
                        System.out.println("What is the patient ID?");
                        System.out.println(persistanceHandler.getPatient(Integer.parseInt(s.nextLine())));
                        break;
                    case "createpatient":
                        String pat = Patient.class.getSimpleName().toLowerCase();
                        Patient patient = new Patient();
                        generateMessage("id", pat);
                        patient.setId(Integer.parseInt(s.nextLine()));
                        generateMessage("name", pat);
                        patient.setName(s.nextLine());
                        generateMessage("phone", pat);
                        patient.setPhone(Integer.parseInt(s.nextLine()));
                        generateMessage("CPR number", pat);
                        patient.setCpr_number(s.nextLine());
                        if(persistanceHandler.createPatient(patient)) {
                            System.out.println("Patient has been inserted in the database");
                        } else {
                            System.out.println("Could not insert patient, try again and check that the id is available");
                        }
                        break;
                    case "getbeds":
                        System.out.println(persistanceHandler.getBeds());
                        break;
                    case "getbed":
                        System.out.println("What is the bed ID?");
                        break;
                    case "createbed":
                        break;
                    case "getadmissions":
                        System.out.println(persistanceHandler.getAdmissions());
                        break;
                    case "getadmission":
                        System.out.println("What is the admission ID?");
                        break;
                    case "createadmission":
                        break;
                    case "deleteadmission":
                        System.out.println("What is the admission ID?");
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
                + "- createEmployee\n"
                + "- getPatients\n"
                + "- getPatient\n"
                + "- createPatient\n"
                + "- getBeds\n"
                + "- getBed\n"
                + "- createBed\n"
                + "- getAdmissions\n"
                + "- getAdmission\n"
                + "- createAdmission\n"
                + "- deleteAdmission\n"
                + "- exit\n";
    }

    private static void generateMessage(String parameter, String type) {
        System.out.println(String.format("Please enter the %s of the %s...", parameter, type));
    }
}

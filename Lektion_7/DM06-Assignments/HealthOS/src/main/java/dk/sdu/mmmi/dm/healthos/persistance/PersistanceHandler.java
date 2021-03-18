package dk.sdu.mmmi.dm.healthos.persistance;

import dk.sdu.mmmi.dm.healthos.domain.Employee;
import dk.sdu.mmmi.dm.healthos.domain.Patient;
import dk.sdu.mmmi.dm.healthos.domain.Admission;
import dk.sdu.mmmi.dm.healthos.domain.Bed;
import dk.sdu.mmmi.dm.healthos.domain.IPersistanceHandler;

import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class PersistanceHandler implements IPersistanceHandler{
    private static PersistanceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "DM07";
    private String username = "postgres";
    private String password = getPassword();
    private Connection connection = null;
    
    private PersistanceHandler(){
        initializePostgresqlDatabase();
    }

    public static PersistanceHandler getInstance(){
        if (instance == null) {
            instance = new PersistanceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }

    @Override
    public List<Employee> getEmployees() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees");
            ResultSet sqlReturnValues = stmt.executeQuery();
            int rowcount = 0;
            List<Employee> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()){
                returnValue.add(new Employee(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3), sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getInt(6)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployee(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()){
               return null;
            }
            return new Employee(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3), sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getInt(6));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }    
    }

    /*
    Implement all of the following. Beware that the model classes are as of yet not properly implemented
    */

    @Override
    public boolean createEmployee(Employee employee) {
        //make HealthOS support this action in the presentation layer too.
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO employees (id, name, phone, position_id, department_id, room_id) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setInt(3, employee.getPhone());
            stmt.setInt(4, employee.getPosition_id());
            stmt.setInt(5, employee.getDepartment_id());
            stmt.setInt(6, employee.getRoom_id());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Patient> getPatients() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM patients");
            ResultSet result = stmt.executeQuery();
            List<Patient> patients = new ArrayList<>();
            while (result.next()) {
                patients.add(new Patient(result.getInt("id"), result.getString("name"), result.getString("phone"), result.getString("cpr_number")));
            }
            return patients;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient getPatient(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM patients WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (!result.next()){
                return null;
            }
            return new Patient(result.getInt("id"), result.getString("name"), result.getString("phone"), result.getString("cpr_number"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createPatient(Patient patient) {
        //make HealthOS support this action in the presentation layer too.
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO patients (id, name, phone, cpr) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, patient.getId());
            stmt.setString(2, patient.getName());
            stmt.setString(3, patient.getPhone());
            stmt.setString(4, patient.getCPR());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Bed> getBeds() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM beds");
            ResultSet result = stmt.executeQuery();
            List<Bed> beds = new ArrayList<>();
            while (result.next()) {
                beds.add(new Bed(result.getInt("id"), result.getString("bed_number")));
            }
            return beds;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bed getBed(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM beds");
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new Bed(result.getInt("id"), result.getString("bed_number"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createBed(Bed bed) {
        //make HealthOS support this action in the presentation layer too.
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO beds (id, bed_number) VALUES (?, ?)");
            stmt.setInt(1, bed.getID());
            stmt.setString(2, bed.getBedNumber());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Admission> getAdmissions() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM admissions");
            ResultSet result = stmt.executeQuery();
            List<Admission> admissions = new ArrayList<>();
            while (result.next()) {
                admissions.add(new Admission(result.getInt("id"), result.getInt("patient_id"), result.getInt("room_id"), result.getInt("bed_id"), result.getInt("assigned_employee_id")));
            }
            return admissions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admission getAdmission(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM admissions WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new Admission(result.getInt("id"), result.getInt("patient_id"), result.getInt("room_id"), result.getInt("bed_id"), result.getInt("assigned_employee_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createAdmission(Admission admission) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO admissions (id, patient_id, room_id, bed_id, assigned_employee_id) VALUES (?, ?, ?, ?, ?)" );
            stmt.setInt(1, admission.getId());
            stmt.setInt(2, admission.getPatientId());
            stmt.setInt(3, admission.getRoomId());
            stmt.setInt(4, admission.getBedId());
            stmt.setInt(5, admission.getAssignedEmployeeId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        //make HealthOS support this action in the presentation layer too.
    }

    @Override
    public boolean deleteAdmission(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM admissions WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        //make HealthOS support this action in the presentation layer too.
    }

    private String getPassword() {
        File file = new File("C:\\Users\\maxx0\\OneDrive - Syddansk Universitet\\2.Semester\\DM\\Ovelser\\SDUDataManagementOvelser\\Lektion_7\\src\\com\\company\\secureInfo");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            password = scanner.next();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, could not get password");
            e.printStackTrace();
        }
        return password;
    }
}

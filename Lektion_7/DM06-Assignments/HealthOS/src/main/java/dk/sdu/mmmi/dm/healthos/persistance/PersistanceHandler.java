package dk.sdu.mmmi.dm.healthos.persistance;

import dk.sdu.mmmi.dm.healthos.domain.Employee;
import dk.sdu.mmmi.dm.healthos.domain.Patient;
import dk.sdu.mmmi.dm.healthos.domain.Admission;
import dk.sdu.mmmi.dm.healthos.domain.Bed;
import dk.sdu.mmmi.dm.healthos.domain.IPersistanceHandler;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Patient getPatient(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean createPatient(Patient patient) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Bed> getBeds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Bed getBed(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean createBed(Bed bed) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Admission> getAdmissions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Admission getAdmission(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean createAdmission(Admission admission) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAdmission(int id) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("Not supported yet.");
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

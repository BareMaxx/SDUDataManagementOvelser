package dk.sdu.mmmi.dm.healthos.domain;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Oliver Nordestgaard | olnor18
 */
public interface IPersistanceHandler {

    public List<Employee> getEmployees();

    public Employee getEmployee(ObjectId id);

    public boolean createEmployee(Employee employee);

    public List<Patient> getPatients();

    public Patient getPatient(ObjectId id);

    public boolean createPatient(Patient patient);

    public List<Bed> getBeds();

    public Bed getBed(ObjectId id);

    public boolean createBed(Bed bed);

    public List<Admission> getAdmissions();

    public Admission getAdmission(ObjectId id);

    public boolean createAdmission(Admission admission);

    public boolean deleteAdmission(ObjectId id);
}

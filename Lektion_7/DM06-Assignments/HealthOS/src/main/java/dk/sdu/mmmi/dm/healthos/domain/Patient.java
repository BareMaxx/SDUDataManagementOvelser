package dk.sdu.mmmi.dm.healthos.domain;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class Patient {
    private int id;
    private String name;
    private String phone;
    private String cpr;

    public Patient(int id, String name, String phone, String cpr) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cpr = cpr;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCPR() {return cpr;}

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", cpr=" + cpr + '}';
    }
}

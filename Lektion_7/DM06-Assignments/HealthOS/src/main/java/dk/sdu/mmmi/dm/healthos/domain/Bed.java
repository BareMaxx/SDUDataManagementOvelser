package dk.sdu.mmmi.dm.healthos.domain;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class Bed {
    private int id;
    private String bed_number;

    public Bed(int id, String bed_number) {
        this.id = id;
        this.bed_number = bed_number;
    }

    public int getID() {
        return id;
    }

    public String getBedNumber() {
        return bed_number;
    }

    @Override
    public String toString() {
        return "Bed {" + "id=" + id + " bed_number=" + bed_number +'}';
    }
}

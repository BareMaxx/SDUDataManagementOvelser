package dk.sdu.mmmi.dm.healthos.domain;

import com.google.gson.Gson;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Oliver Nordestgaard | olnor18
 */

public class Patient {

    @BsonProperty("_id")
    private int id;
    private String name;
    private int phone;
    private String cpr_number;

    public Patient(){
    }
    public Patient(int id, String name, int phone, String cpr_number) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cpr_number = cpr_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCpr_number() {
        return cpr_number;
    }

    public void setCpr_number(String cpr_number) {
        this.cpr_number = cpr_number;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

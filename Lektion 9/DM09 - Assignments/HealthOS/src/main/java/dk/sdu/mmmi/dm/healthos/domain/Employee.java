package dk.sdu.mmmi.dm.healthos.domain;

import com.google.gson.Gson;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * @author Oliver Nordestgaard | olnor18
 */

public class Employee {
    @BsonProperty("_id")
    private int id;
    private String name;
    private int phone;
    private int position_id;
    private int department_id;
    private int room_id;

    public Employee(int id, String name, int phone, int position_id, int department_id, int room_id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.position_id = position_id;
        this.department_id = department_id;
        this.room_id = room_id;
    }

    public Employee() {
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

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

package dk.sdu.dm;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class User {
    @BsonProperty("_id") //Laver "_id" om til id, eftersom der er forskellige norms fra MongoDB og Java
    private int id;
    private String name;
    private String cpr;

    public User() {

    }

    public User(int id, String name, String cpr) {
        this.id = id;
        this.name = name;
        this.cpr = cpr;
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

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }
}

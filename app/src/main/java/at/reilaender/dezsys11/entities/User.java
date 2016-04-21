package at.reilaender.dezsys11.entities;

import org.json.JSONException;
import org.json.JSONObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Represents a user saved in the DB
 *
 * @author Paul Kalauner 5BHIT
 * @version 20160212.1
 */
@DatabaseTable(tableName = "User")
public class User {
    @DatabaseField(id = true)
    private String email;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String password;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        JSONObject json = null;
        try {
            json = new JSONObject();
            json.put("email", this.email);
            json.put("name", this.name);
            json.put("password", this.password);
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}

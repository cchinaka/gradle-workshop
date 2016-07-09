package ng.com.workshop.model.user;

import java.io.Serializable;


public class UserRole implements Serializable {

    private static final long serialVersionUID = -2515709243082466732L;

    private String name;

    private String code;

    private String description;


    public UserRole() {
    }


    public UserRole(String code) {
        setCode(code);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}

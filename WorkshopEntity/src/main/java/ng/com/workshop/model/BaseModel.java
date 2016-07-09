package ng.com.workshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 5946617285336449048L;

    private Long id;

    private String code;

    private String description;

    private Date creationDate = new Date();

    private boolean active = true;


    public BaseModel() {
    }


    public BaseModel(Long id, String code, String description) {
        setId(id);
        setCode(code);
        setDescription(description);
        setCreationDate(creationDate);
    }


    public BaseModel(Long id) {
        setId(id);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    @Column(unique = true)
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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass().isInstance(obj)) {
            if (getId() == null) {
                return this == obj;
            } else {
                return getId() == ((BaseModel) obj).getId();
            }
        } else {
            return false;
        }
    }


    @Override
    public int hashCode() {
        if (getId() == null) {
            return 0;
        }
        int result = 17;
        int calculation = (int) (getId() ^ (getId() >>> 32));
        return result = (37 * result) + calculation;
    }


    public static void main(String[] args) {
        BaseModel model1 = new BaseModel();
        BaseModel model2 = new BaseModel();
        System.out.println(model1);
        System.out.println(model2);
        System.out.println(model1.equals(model2));
    }


    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public String toString() {
        return String.format("id: %s; code: %s; description: %s; creationDate: %s", id, code, description, creationDate);
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }
}

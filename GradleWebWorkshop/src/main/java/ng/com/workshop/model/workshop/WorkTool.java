package ng.com.workshop.model.workshop;

import javax.persistence.Entity;

import ng.com.workshop.model.BaseModel;


@Entity
public class WorkTool extends BaseModel {

    public static final String FETCH_WORKTOOLS_SQL = "select id,code,active,description,creationDate,longitude,latitude from WorkTool";

    public static final String INSERT_WORKTOOL_SQL = "insert into WorkTool (code,description,creationDate,longitude,latitude,active) VALUES (?,?,?,?,?,?)";

    private static final long serialVersionUID = 9038611459856510825L;

    private Double longitude;

    private Double latitude;


    public WorkTool() {
    }


    public WorkTool(Long id, String code, String description) {
        super(id, code, description);
    }


    public WorkTool(String code, String description) {
        this(null, code, description);
    }


    public WorkTool(Long id, String code, String description, Double longitude, Double latitude) {
        this(id, code, description);
        setLongitude(longitude);
        setLatitude(latitude);
    }


    public WorkTool(String code, String description, Double longitude, Double latitude) {
        this(code, description);
        setLongitude(longitude);
        setLatitude(latitude);
    }


    public Double getLongitude() {
        return longitude;
    }


    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public Double getLatitude() {
        return latitude;
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

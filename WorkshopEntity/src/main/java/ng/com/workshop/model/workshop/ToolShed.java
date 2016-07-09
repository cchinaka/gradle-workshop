package ng.com.workshop.model.workshop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import ng.com.workshop.model.BaseModel;


@Entity
public class ToolShed extends BaseModel {

    private static final long serialVersionUID = -8324829168894701554L;

    private Set<String> nicknames = new HashSet<>();

    private Map<String, WorkTool> worktools = new HashMap<>();

    private Map<String, String> keyValue = new HashMap<>();


    @ElementCollection(fetch = FetchType.EAGER)
    public Set<String> getNicknames() {
        return nicknames;
    }


    public void setNicknames(Set<String> nicknames) {
        this.nicknames = nicknames;
    }


    @OneToMany
    @JoinColumn(name = "toolshed_id")
    @MapKey(name = "code")
    public Map<String, WorkTool> getWorktools() {
        return worktools;
    }


    public void setWorktools(Map<String, WorkTool> worktools) {
        this.worktools = worktools;
    }


    @ElementCollection
    public Map<String, String> getKeyValue() {
        return keyValue;
    }


    public void setKeyValue(Map<String, String> keyValue) {
        this.keyValue = keyValue;
    }
}

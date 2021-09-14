package at.spg.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Extension extends Element{

    public Extension(String url, List<String> value) {
        this.url = url;
        this.value = value;
    }
    public Extension() {
    }

    @Column(name="url")
    private String url;
    
    @ElementCollection
    public List<String> value;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}

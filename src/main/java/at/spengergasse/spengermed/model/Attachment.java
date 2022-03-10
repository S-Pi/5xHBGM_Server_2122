package at.spengergasse.spengermed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Attachment extends Element{

    public Attachment() {
    }

    public Attachment(String contentType, String language, String url, int size, String title, LocalDate creation) {
        this.contentType = contentType;
        this.language = language;
        this.url = url;
        this.size = size;
        this.title = title;
        this.creation = creation;
    }

    @Column(name="at_contentType")
    private String contentType;
    
    @Column(name="at_language")
    private String language;

    //private Base64 date;??
    @Column(name="at_url")
    private String url;
    
    @Column(name="at_size")
    private int size;

    //private Base64 hash;??
    @Column(name="at_title")
    private String title;
    
    @Column(name="at_creation")
    private LocalDate creation;

    
}

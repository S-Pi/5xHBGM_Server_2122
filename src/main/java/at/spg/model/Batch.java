package at.spg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Batch extends BackboneElement{

    public Batch() {
    }

    public Batch(String lotNumber, LocalDateTime expirationDate) {
        this.lotNumber = lotNumber;
        this.expirationDate = expirationDate;
    }

    @Column(name="lotNumber")
    private String lotNumber;
    
    @Column(name="expirationDate")
    private LocalDateTime expirationDate;

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}

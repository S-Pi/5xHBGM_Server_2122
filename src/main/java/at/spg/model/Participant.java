package at.spg.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Participant extends BackboneElement{

    public Participant(List<CodeableConcept> type, Period period, Reference individual) {
        this.type = type;
        this.period = period;
        this.individual = individual;
    }

    public Participant() {
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = CodeableConcept.class)
    @JoinColumn(name = "cc_participant_fk", referencedColumnName = "id")
    private List<CodeableConcept> type;
    
    @Embedded
    private Period period;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="part_reference_fk", referencedColumnName="id")
    private Reference individual;


    public List<CodeableConcept> getType() {
        return type;
    }

    public void setType(List<CodeableConcept> type) {
        this.type = type;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}

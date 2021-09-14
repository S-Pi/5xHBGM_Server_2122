package at.spg.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name="d_diagnosis")
public class Diagnosis extends BackboneElement{
    public Diagnosis(List<Reference> condition, CodeableConcept use, int rank) {
        this.condition = condition;
        this.use = use;
        this.rank = rank;
    }

    public Diagnosis() {
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Reference.class)
    @JoinColumn(name = "diag_reference_fk", referencedColumnName = "id")    
    private List<Reference> condition;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="diag_codeableconcept_fk", referencedColumnName="id")
    private CodeableConcept use;
    
    @Column(name="d_rank")
    private int rank;

    public List<Reference> getCondition() {
        return condition;
    }

    public void setCondition(List<Reference> condition) {
        this.condition = condition;
    }

    public CodeableConcept getUse() {
        return use;
    }

    public void setUse(CodeableConcept use) {
        this.use = use;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

package at.spg.model;

import javax.persistence.*;

@Entity
public class Ingredient extends BackboneElement{

    public Ingredient() {
    }

    public Ingredient(CodeableConcept itemCodeableConcept, Boolean isActive, Ratio strength) {
        this.itemCodeableConcept = itemCodeableConcept;
        this.isActive = isActive;
        this.strength = strength;
    }

    public Ingredient(Reference itemReference, Boolean isActive, Ratio strength) {
        this.itemReference = itemReference;
        this.isActive = isActive;
        this.strength = strength;
    }

    @OneToOne(cascade = CascadeType.ALL,targetEntity = CodeableConcept.class)
    @JoinColumn(name = "ind_item_cc_fk", referencedColumnName = "id")
    private CodeableConcept itemCodeableConcept;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = Reference.class)
    @JoinColumn(name = "med_manufacturer_reference_fk", referencedColumnName = "id")
    private Reference itemReference;

    @Column(name="isActive")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = Ratio.class)
    @JoinColumn(name = "ind_ratio_fk", referencedColumnName = "id")
    private Ratio strength;

    public CodeableConcept getItemCodeableConcept() {
        return itemCodeableConcept;
    }

    public void setItemCodeableConcept(CodeableConcept itemCodeableConcept) {
        if(itemReference != null && itemCodeableConcept == null) {
            throw new IllegalArgumentException("Itemreference and ItemCodeableConcept cannot be set simultaneously.");

        }
        else{
            this.itemCodeableConcept = itemCodeableConcept;
        }
    }

    public Reference getItemReference() {
        return itemReference;
    }

    public void setItemReference(Reference itemreference) {
        if(itemCodeableConcept != null && itemreference == null) {
            throw new IllegalArgumentException("Itemreference and ItemCodeableConcept cannot be set simultaneously.");
        }
        else{
            this.itemReference = itemreference;
        }
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Ratio getStrength() {
        return strength;
    }

    public void setStrength(Ratio strength) {
        this.strength = strength;
    }
}

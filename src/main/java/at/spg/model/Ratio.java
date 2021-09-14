package at.spg.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jdk.jfr.Name;

@Entity
@Table(name="r_ratio")
public class Ratio extends Element{

    public Ratio() {
    }

    public Ratio(Quantity numerator, Quantity denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rat_num_fk", referencedColumnName="id")
    private Quantity numerator;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rat_denom_fk", referencedColumnName="id")
    private Quantity denominator;

    public Quantity getNumerator() {
        return numerator;
    }

    public void setNumerator(Quantity numerator) {
        this.numerator = numerator;
    }

    public Quantity getDenominator() {
        return denominator;
    }

    public void setDenominator(Quantity denominator) {
        this.denominator = denominator;
    }
}

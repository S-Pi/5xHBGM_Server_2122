package at.spg.model;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="a_address")
@Builder
public class Address extends Element {

    public enum UseCode{
        home, work, temp, old, billing
    }

    public enum TypeCode{
        postal, physical, both
    }

    @Enumerated(EnumType.STRING)
    @Column(name="a_use")
    private UseCode use;

    @Enumerated(EnumType.STRING)
    @Column(name="a_type")
    private TypeCode type;

    @Column(name="a_text")
    private String text;

    @Column(name="a_line")
    private String line;

    @Column(name="a_city")
    private String city;

    @Column(name="a_district")
    private String district;

    @Column(name="a_state")
    private String state;

    @Column(name="a_postalcode")
    private String postalcode;

    @Column(name="a_country")
    private String country;

    @Embedded
    private Period period;

}

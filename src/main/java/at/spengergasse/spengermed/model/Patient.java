package at.spengergasse.spengermed.model;

import at.spengergasse.spengermed.validators.PatientValid;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Entity bewirkt, dass die Klasse Patient von JPA beachtet wird
// und dafür eine Tabelle in der DB erzeugt wird
@Entity
@Table(name="p_patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PatientValid
public class Patient extends DomainResource{

    public enum GenderCode{
        male, female, other, unknown
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_p_id", referencedColumnName = "id")
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @Column(name = "p_active")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="hn_p_id",nullable = true, referencedColumnName = "id")
    private List<HumanName> name = new ArrayList<HumanName>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cp_p_id", referencedColumnName = "id")
    private List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    @Enumerated(EnumType.STRING)
    @Column(name = "p_gender")
    private GenderCode gender;

    @PastOrPresent(message = "Das Geburtsdatum muss in der Vergangenheit liegen")
    @Column(name = "p_birthdate")
    private LocalDate birthDate;

    @Column(name= "p_deceasedboolean")
    private Boolean deceasedBoolean;

    @Column(name="p_deceaseddatetime")
    private LocalDateTime deceasedDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "a_p_id", referencedColumnName = "id")
    private List<Address> address = new ArrayList<Address>();
}

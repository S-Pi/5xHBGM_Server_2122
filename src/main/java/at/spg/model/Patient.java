package at.spg.model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Patient extends DomainResource{

    //Aufzählung. Nur diese Werte können gespeichert werden.
    public enum GenderCode{
        male, female, other, unknown
    }

    //1:n oder 0..* zu einem Identifier Element
    //CascadeType.ALL - wenn der Patient gelöscht wird, werden auch alle Identifier dazu gelöscht.
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    //Der ForeignKey ist i_patient_fk in der Tabelle Identifier. Immer auf der "n" Seite.
    //Dieser ForeignKey referenziert auf den PrimaryKey von Patient, der heißt "id".
    @JoinColumn(name = "i_patient_fk", referencedColumnName = "id")
    private List<Identifier> identifier;

    @Column(name="p_active")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hn_patient_fk", referencedColumnName = "id")
    private List<HumanName> name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_patient_fk", referencedColumnName = "id")
    private List<ContactPoint> telecom;

    //EnumType.STRING bewirkt, dass die Textwerte von Enums in der DB gespeichert werden.
    //Ansonsten würde die Position/Index gespeichert werden.
    @Enumerated(EnumType.STRING)
    @Column(name="p_gender")
    private GenderCode gender;

    //Bei Datum und Zeit immer Local verwenden
    @Column(name="p_birthdate")
    private LocalDate birthDate;

    //Alternative ? Felder- es wird in der DB beides abgebildet,
    //Im Controller nur 1 erlaubt.
    @Column(name="p_deceasedBoolean")
    private boolean deceasedBoolean;

    @Column(name="p_deceasedDateTime")
    private LocalDate deceaseDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_patient_fk", referencedColumnName = "id")
    private List<Address> addresses;



}

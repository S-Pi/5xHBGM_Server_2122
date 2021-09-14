package at.spg.model;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Identifier extends Element {

    public enum UseCode{
        usual , official , temp , secondary , old;
    }
    @Enumerated(EnumType.STRING)
    @Column(name="i_code")
    private UseCode code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="i_codeableconcept_fk", referencedColumnName="id")
    private CodeableConcept type;

    @Column(name="i_system")
    private String system;

    @Column(name="i_value")
    private String value;

    @Embedded
    private Period period;
}

package at.spg.model;



import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeableConcept extends Element{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_codeableconcept_fk", referencedColumnName = "id")
    private List<Coding> coding;

    @Column(name="cc_text")
    private String text;
}

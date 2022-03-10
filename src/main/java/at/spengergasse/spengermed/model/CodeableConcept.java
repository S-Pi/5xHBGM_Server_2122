package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="cc_codeableconcept")
@Builder
public class CodeableConcept extends Element{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="co_cc_id")
    private List<Coding> coding = new ArrayList<Coding>();

    @Column(name="cc_text")
    private String text;


}

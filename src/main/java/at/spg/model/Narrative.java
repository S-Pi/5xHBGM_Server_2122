package at.spg.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="N_NARRATIVE")
@Builder
public class Narrative extends Element{
    public enum NarrativeCode{
        generated , extensions , additional , empty
    }
    @Column(name="n_status")
    private NarrativeCode status;

    @Column(name="n_div")
    private String div;
}

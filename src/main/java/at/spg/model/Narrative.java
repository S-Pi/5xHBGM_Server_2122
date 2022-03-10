package at.spg.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @Column(name="n_status", nullable = false)
    private NarrativeCode status;

    @NotNull
    @Lob
    @Column(name="n_div", nullable = false)
    private String div;
}

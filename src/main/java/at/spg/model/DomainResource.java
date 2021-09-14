package at.spg.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class DomainResource extends Resource{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dr_narrative_fk", referencedColumnName = "id")
    private Narrative text;
}

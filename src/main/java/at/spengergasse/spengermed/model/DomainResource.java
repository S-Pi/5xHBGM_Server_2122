package at.spengergasse.spengermed.model;

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

public class DomainResource extends Resource{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="dr_n_id", referencedColumnName = "id")
    private Narrative text;
}

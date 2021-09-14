package at.spg.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="r_reference")
public class Reference extends Element{

    @Column(name="r_reference")
    private String reference;

    @Column(name="r_type")
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "r_identifier_fk", referencedColumnName = "id")
    private Identifier identifier;

    @Column(name="r_display")
    private String display;
}

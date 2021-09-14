package at.spg.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HumanName extends Element{

    public enum UseCode{
        usual , official , temp , nickname , anonymous , old , maiden
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "hn_use")
    private UseCode use;

    @Column(name = "hn_text")
    private String text;

    @Column(name = "hn_family")
    private String family;

    @Column(name = "hn_given")
    private String given;

    @ElementCollection
    @CollectionTable(name = "humanname_prefix", joinColumns = @JoinColumn(name = "id"))
    private List<String> prefix = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "humanname_surfix", joinColumns = @JoinColumn(name = "id"))
    private List<String> surfix = new ArrayList<>();

    @Embedded
    private Period period;
}

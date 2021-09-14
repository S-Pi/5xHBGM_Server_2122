package at.spg.model;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
public class BackboneElement extends Element{
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Extension.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ex_backboneelement_fk", referencedColumnName = "id")
    List<Extension> modifierExtension;

    public List<Extension> getModifierExtension() {
        return modifierExtension;
    }

    public void setModifierExtension(List<Extension> modifierExtension) {
        this.modifierExtension = modifierExtension;
    }
}

package at.spg.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coding extends Element{
    
    @Column(name="c_system")
    private String system;
    @Column(name="c_version")
    private String version;
    @Column(name="c_code")
    private String code;
    @Column(name="c_display")
    private String display;
    @Column(name="c_userSelected")
    private boolean userSelected;
}

package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="co_coding")
@Builder
public class Coding extends Element{

    @Column(name= "co_system")
    private String system;

    @Column(name= "co_version")
    private String version;

    @Column(name= "co_code")
    private String code;

    @Column(name= "co_display")
    private String display;

    @Column(name= "co_userSelected")
    private Boolean userSelected;
}

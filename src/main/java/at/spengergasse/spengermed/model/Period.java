package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pe_period")
@Builder
public class Period extends Element{
    @Column(name = "pe_start")
    private LocalDateTime start;
    @Column(name = "pe_end")
    private LocalDateTime end;
}

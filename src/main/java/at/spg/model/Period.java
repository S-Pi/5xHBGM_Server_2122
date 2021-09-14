package at.spg.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Period {

    @Column(name="pp_start")
    private LocalDateTime start;

    @Column(name="pp_end")
    private LocalDateTime end;

}

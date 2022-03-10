package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusHistory extends BackboneElement{


    public enum EncounterStatus {
        planned("planned"),
        arrived("arrived"),
        triaged("triaged"),
        inprogress("in-progress"),
        onleave("onleave"),
        finished("finished"),
        cancelled("cancelled +");

        private String value;
        private EncounterStatus(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return this.value;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private EncounterStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sh_period_id", referencedColumnName="id")
    private Period period;


}

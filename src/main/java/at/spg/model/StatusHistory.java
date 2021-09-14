package at.spg.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class StatusHistory extends BackboneElement{

    public StatusHistory(EncounterStatus status, Period period) {
        this.status = status;
        this.period = period;
    }

    public StatusHistory() {
    }

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
    
    @Embedded
    private Period period;

    public EncounterStatus getStatus() {
        return status;
    }

    public void setStatus(EncounterStatus status) {
        this.status = status;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}

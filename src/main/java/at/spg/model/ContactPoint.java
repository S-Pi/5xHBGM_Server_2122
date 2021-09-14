package at.spg.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPoint extends Element {

    public enum SystemCode {
        phone("phone"),
        fax("fax"),
        email("email"),
        pager("pager"),
        url("url"),
        sms("sms"),
        other("other");

        private String value;

        private SystemCode(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public enum UseCode {
        home, work, temp, old, mobile
    }

    @Enumerated(EnumType.STRING)
    private SystemCode systemEnum;

    @Column(name = "cp_value")
    private String value;

    @Enumerated(EnumType.STRING)
    private UseCode useEnum;

    @Column(name = "cp_rank")
    private int rank;

    @Embedded
    private Period period;
}

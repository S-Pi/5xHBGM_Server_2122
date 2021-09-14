package at.spg.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Encounter extends DomainResource {

	public Encounter() {
	}

	public Encounter(List<Identifier> identifier, StatusCode status, List<StatusHistory> statusHistory,
			List<CodeableConcept> type, Reference subject, List<Reference> episodeOfCare, List<Participant> participant,
			List<Reference> appointment, Period period, List<Reference> reasonReference, List<Diagnosis> diagnosis,
			Reference partOf) {
		this.identifier = identifier;
		this.status = status;
		this.statusHistory = statusHistory;
		this.type = type;
		this.subject = subject;
		this.episodeOfCare = episodeOfCare;
		this.participant = participant;
		this.appointment = appointment;
		this.period = period;
		this.reasonReference = reasonReference;
		this.diagnosis = diagnosis;
		this.partOf = partOf;
	}

	

	public enum StatusCode {
        planned("planned"),
        arrived("arrived"),
        triaged("triaged"),
        inprogress("in-progress"), // - wäre in Java nicht erlaubt, so funktioniert es
        onleave("onleave"),
        finished("finished"),
        cancelled("cancelled");

         private String value;
         private StatusCode(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return this.value;
        }
    }
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "i_encounter_fk", referencedColumnName = "id")
	private List<Identifier> identifier = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private StatusCode status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sh_encounter_fk", referencedColumnName = "id")
	private List<StatusHistory> statusHistory = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cc_encounter_fk", referencedColumnName = "id")
	private List<CodeableConcept> type = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "enc_subject_reference_fk", referencedColumnName = "id")
	private Reference subject;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enc_epOfCa_reference_fk", referencedColumnName = "id")
	private List<Reference> episodeOfCare = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "part_encounter_fk", referencedColumnName = "id")
	private List<Participant> participant = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ref_encounter_appointment_fk", referencedColumnName = "id")
	private List<Reference> appointment = new ArrayList<>();
	
	@Embedded
	private Period period;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ref_encounter_reason_fk", referencedColumnName = "id")
	private List<Reference> reasonReference;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dia_encounter_fk", referencedColumnName = "id")	
	private List<Diagnosis> diagnosis;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "enc_partOf_reference_fk", referencedColumnName = "id")
	private Reference partOf;

	public List<Identifier> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(List<Identifier> identifier) {
		this.identifier = identifier;
	}

	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	public List<StatusHistory> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(List<StatusHistory> statusHistory) {
		this.statusHistory = statusHistory;
	}

	public List<CodeableConcept> getType() {
		return type;
	}

	public void setType(List<CodeableConcept> type) {
		this.type = type;
	}

	public Reference getSubject() {
		return subject;
	}

	public void setSubject(Reference subject) {
		this.subject = subject;
	}

	public List<Reference> getEpisodeOfCare() {
		return episodeOfCare;
	}

	public void setEpisodeOfCare(List<Reference> episodeOfCare) {
		this.episodeOfCare = episodeOfCare;
	}

	public List<Participant> getParticipant() {
		return participant;
	}

	public void setParticipant(List<Participant> participant) {
		this.participant = participant;
	}

	public List<Reference> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Reference> appointment) {
		this.appointment = appointment;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public List<Reference> getReasonReference() {
		return reasonReference;
	}

	public void setReasonReference(List<Reference> reasonReference) {
		this.reasonReference = reasonReference;
	}

	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Reference getPartOf() {
		return partOf;
	}

	public void setPartOf(Reference partOf) {
		this.partOf = partOf;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Encounter encounter = (Encounter) o;
		return Objects.equals(identifier, encounter.identifier) && status == encounter.status
				&& Objects.equals(statusHistory, encounter.statusHistory) && Objects.equals(type, encounter.type)
				&& Objects.equals(subject, encounter.subject) && Objects.equals(episodeOfCare, encounter.episodeOfCare)
				&& Objects.equals(participant, encounter.participant)
				&& Objects.equals(appointment, encounter.appointment) && Objects.equals(period, encounter.period)
				&& Objects.equals(reasonReference, encounter.reasonReference)
				&& Objects.equals(diagnosis, encounter.diagnosis) && Objects.equals(partOf, encounter.partOf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), identifier, status, statusHistory, type, subject, episodeOfCare,
				participant, appointment, period, reasonReference, diagnosis, partOf);
	}
}

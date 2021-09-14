package at.spg.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;
@Entity
public class Practitioner extends DomainResource {

    public Practitioner(List<Identifier> identifier, boolean active, List<HumanName> name, List<ContactPoint> telecom, List<Address> address, GenderCode gender, LocalDate birthDate, List<Attachment> photo, List<CodeableConcept> communication) {
        this.identifier = identifier;
        this.active = active;
        this.name = name;
        this.telecom = telecom;
        this.address = address;
        this.gender = gender;
        this.birthDate = birthDate;
        this.photo = photo;
        this.communication = communication;
    }

    public Practitioner() {
    }

    public enum GenderCode{
        male, female, other, unknown
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Identifier.class)
    @JoinColumn(name = "i_practitioner_fk", referencedColumnName = "id")
    private List<Identifier> identifier;
    
    @Column(name="active")
    private boolean active;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = HumanName.class)
    @JoinColumn(name = "hn_practitioner_fk", referencedColumnName = "id")
    private List<HumanName> name;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = ContactPoint.class)
    @JoinColumn(name = "cp_practitioner_fk", referencedColumnName = "id")
    private List<ContactPoint> telecom;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "a_practitioner_fk", referencedColumnName = "id")
    private List<Address> address;
    
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private GenderCode gender;
    
    @Column(name="birthdate")
    private LocalDate birthDate;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Attachment.class)
    @JoinColumn(name = "at_practitioner_fk", referencedColumnName = "id")
    private List<Attachment> photo;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = CodeableConcept.class)
    @JoinColumn(name = "cc_practitioner_fk", referencedColumnName = "id")
    private List<CodeableConcept> communication;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Qualification.class)
    @JoinColumn(name = "q_practitioner_fk", referencedColumnName = "id")
    private List<Qualification> qualifications;


    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<HumanName> getName() {
        return name;
    }

    public void setName(List<HumanName> name) {
        this.name = name;
    }

    public List<ContactPoint> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<ContactPoint> telecom) {
        this.telecom = telecom;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public GenderCode getGender() {
        return gender;
    }

    public void setGender(GenderCode gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Attachment> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Attachment> photo) {
        this.photo = photo;
    }

    public List<CodeableConcept> getCommunication() {
        return communication;
    }

    public void setCommunication(List<CodeableConcept> communication) {
        this.communication = communication;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Practitioner that = (Practitioner) o;
        return active == that.active &&
                Objects.equals(identifier, that.identifier) &&
                Objects.equals(name, that.name) &&
                Objects.equals(telecom, that.telecom) &&
                Objects.equals(address, that.address) &&
                gender == that.gender &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(communication, that.communication) &&
                Objects.equals(qualifications, that.qualifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, active, name, telecom, address, gender, birthDate, photo, communication, qualifications);
    }
}

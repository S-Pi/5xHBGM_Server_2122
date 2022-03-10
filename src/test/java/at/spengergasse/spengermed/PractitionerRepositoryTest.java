package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.model.Qualification;
import at.spengergasse.spengermed.repository.PractitionerRepository;
import at.spengergasse.spengermed.model.Attachment;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PractitionerRepositoryTest {
    @Autowired
    PractitionerRepository practitionerRepository;
    @Test
    @Transactional
    public void testSaveOnePractitioner(){
        Practitioner p1 = returnOnePractitioner();
        Practitioner savedP1 = practitionerRepository.save(p1);
        Practitioner loadedPractitioner = practitionerRepository.findById(savedP1.getId()).get();

        assertEquals(p1, savedP1);
        assertTrue(CollectionUtils.isEqualCollection(p1.getIdentifier(), loadedPractitioner.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getName(), loadedPractitioner.getName()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getTelecom(), loadedPractitioner.getTelecom()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getAddress(), loadedPractitioner.getAddress()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getPhoto(), loadedPractitioner.getPhoto()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getCommunication(), loadedPractitioner.getCommunication()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getQualifications(), loadedPractitioner.getQualifications()));
    }


    @Test
    public void testDeleteOnePractitioner(){
        //1. Erstellen einer mit Daten befüllten Practitionereninstanz
        Practitioner p = returnOnePractitioner();
        //2. Instanz in die DB speichern
        Practitioner savedP = practitionerRepository.save(p);

        //3. Practitioneren aus der DB löschen
        practitionerRepository.deleteById(savedP.getId());
        //4. Practitioneren suchen, darf es nicht mehr geben.
        assertFalse(practitionerRepository.findById(savedP.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOnePractitioner(){
        //1. Erstellen einer mit Daten befüllten Practitionereninstanz
        Practitioner p = returnOnePractitioner();
        //2. Instanz in die DB speichern
        Practitioner savedP = practitionerRepository.save(p);
        //3. Gespeicherte Daten aus der DB lesen
        Practitioner loadedPractitioner = practitionerRepository.findById(savedP.getId()).get();
        //4. Practitioner verändern => Alle Attribute
        loadedPractitioner.setActive(!loadedPractitioner.isActive());
        loadedPractitioner.setGender(Practitioner.GenderCode.female);

        Practitioner changedPractitioner = practitionerRepository.save(loadedPractitioner);
        //5. Vergleich des gespeicherten Objekts mit dem geladenen


        //Alle einfachen Datentypen können mit Equals verglichen werden.
        //Assert prüft, ob die beiden gleich sind. Schlägt ein Assert fehl, ist der Test fehlgeschlagen
        //Asserts sind die eigentlichen "Tests"
        assertEquals(loadedPractitioner.getBirthDate(),changedPractitioner.getBirthDate());
        assertEquals(loadedPractitioner.getBirthDate(),changedPractitioner.getBirthDate());
        assertEquals(loadedPractitioner.getText(),changedPractitioner.getText());
        assertEquals(loadedPractitioner.getGender(),changedPractitioner.getGender());
        //Es sollen alle Attribute verglichen werden, auch die geerbten.

        //Collections werden mit CollectionUtils auf gleichheit getestet.
        // Dabei werden die einzelnen Elemente verglichen,nicht ob die Collectionobjekte gleich sind.
        assertTrue(CollectionUtils.isEqualCollection(p.getIdentifier(), loadedPractitioner.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(p.getName(), loadedPractitioner.getName()));
        assertTrue(CollectionUtils.isEqualCollection(p.getTelecom(), loadedPractitioner.getTelecom()));
        assertTrue(CollectionUtils.isEqualCollection(p.getAddress(), loadedPractitioner.getAddress()));
        //Es sollen alle Collections getestet werden.
    }

    public static Practitioner returnOnePractitioner(){
        List<String> givens = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<HumanName> names = new ArrayList<>();
        List<Address> address = new ArrayList<>();
        List<Attachment> photos = new ArrayList<>();
        List<CodeableConcept> cc = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<ContactPoint> contactPoints = new ArrayList<>();
        List<Qualification> qualifications = new ArrayList<>();
        Period period = new Period(LocalDateTime.of(2000, 01,01,1,1), LocalDateTime.of(2001,01,01,1,1));
        Period period2 = new Period(LocalDateTime.of(2000, 02,02,2,2), LocalDateTime.of(2002,02,02,2,2));
        codings.add(new Coding("System", "0.1.1", "Code", "<div>...<div>", false));
        CodeableConcept ccType = new CodeableConcept(codings, "Text");
        contactPoints.add(new ContactPoint(ContactPoint.SystemCode.phone, "123454321", ContactPoint.UseCode.home, 1, period2));
        givens.add("Simon");
        HumanName h1 = HumanName.builder().family("Perkerson").given(givens).build();
        names.add(h1);
        cc.add(new CodeableConcept(codings, "CodCoc"));
        qualifications.add(new Qualification(identifiers, new CodeableConcept(codings, "CodCoc"), period2));

        Practitioner p1 = new Practitioner(identifiers, true, names, contactPoints, address, Practitioner.GenderCode.female, LocalDate.of(1987, 3, 7), photos, cc);
        p1.setQualifications(qualifications);
        return p1;

    }

}

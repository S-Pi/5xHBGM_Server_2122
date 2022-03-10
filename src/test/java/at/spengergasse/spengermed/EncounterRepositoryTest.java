package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.model.Diagnosis;
import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.Participant;
import at.spengergasse.spengermed.model.StatusHistory;
import at.spengergasse.spengermed.repository.EncounterRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EncounterRepositoryTest {
    @Autowired
    EncounterRepository encounterRepository;
    @Test
    @Transactional
    public void TestSaveOneEncounter(){
        Encounter enc2 =  returnOneEncounter();
        Encounter savedEnc = encounterRepository.save(enc2);
        Encounter loadedEnc = encounterRepository.findById(enc2.getId()).get();


        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getIdentifier(), loadedEnc.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getStatusHistory(), loadedEnc.getStatusHistory()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getEpisodeOfCare(), loadedEnc.getEpisodeOfCare()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getParticipant(), loadedEnc.getParticipant()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getAppointment(), loadedEnc.getAppointment()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getReasonReference(), loadedEnc.getReasonReference()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getDiagnosis(), loadedEnc.getDiagnosis()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getType(), loadedEnc.getType()));
        assertEquals(savedEnc.getPeriod(), loadedEnc.getPeriod());
        assertEquals(enc2.getPartOf(), loadedEnc.getPartOf());
        assertEquals(savedEnc, loadedEnc);

    }


    @Test
    public void testDeleteOneEncounter(){
        //1. Erstellen einer mit Daten befüllten Encountereninstanz
        Encounter p = returnOneEncounter();
        //2. Instanz in die DB speichern
        Encounter savedP = encounterRepository.save(p);

        //3. Encounteren aus der DB löschen
        encounterRepository.deleteById(savedP.getId());
        //4. Encounteren suchen, darf es nicht mehr geben.
        assertFalse(encounterRepository.findById(savedP.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneEncounter(){
        //1. Erstellen einer mit Daten befüllten Encountereninstanz
        Encounter p = returnOneEncounter();
        //2. Instanz in die DB speichern
        Encounter savedP = encounterRepository.save(p);
        //3. Gespeicherte Daten aus der DB lesen
        Encounter loadedEncounter = encounterRepository.findById(savedP.getId()).get();
        //4. Encounter verändern => Alle Attribute
        loadedEncounter.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
        loadedEncounter.setStatus(Encounter.StatusCode.onleave);


        Encounter changedEncounter = encounterRepository.save(loadedEncounter);
        //5. Vergleich des gespeicherten Objekts mit dem geladenen


        //Alle einfachen Datentypen können mit Equals verglichen werden.
        //Assert prüft, ob die beiden gleich sind. Schlägt ein Assert fehl, ist der Test fehlgeschlagen
        //Asserts sind die eigentlichen "Tests"
        assertEquals(loadedEncounter.getPeriod(),changedEncounter.getPeriod());
        assertEquals(loadedEncounter.getStatus(),changedEncounter.getStatus());

        //Es sollen alle Collections getestet werden.
    }

    public static Encounter returnOneEncounter(){
        List<Identifier> identifiers = new ArrayList<>();

        List<StatusHistory> statusHistory = new ArrayList<>();
        List<Reference> episodeOfCare = new ArrayList<>();
        List<Participant> participants = new ArrayList<>();
        List<Reference> appointment = new ArrayList<>();
        List<Reference> reasonReference = new ArrayList<>();
        List<Reference> conditions = new ArrayList<>();
        List<Diagnosis> diagnosis = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<CodeableConcept> cc = new ArrayList<>();

        Identifier identifier1 =new Identifier();
        identifier1.setPeriod(Period.builder().start(LocalDateTime.now()).end(LocalDateTime.now()).build());
        identifier1.setUse(Identifier.UseCode.old);
        identifier1.setValue("Identifiervalue");
        Identifier identifier2 =new Identifier();
        identifier2.setPeriod(Period.builder().start(LocalDateTime.now()).end(LocalDateTime.now()).build());
        identifier2.setUse(Identifier.UseCode.old);
        identifier2.setValue("Identifiervalue");
        Identifier identifier3 =new Identifier();
        identifier3.setPeriod(Period.builder().start(LocalDateTime.now()).end(LocalDateTime.now()).build());
        identifier3.setUse(Identifier.UseCode.old);
        identifier3.setValue("Identifiervalue");
        Identifier identifier4 =new Identifier();
        identifier4.setPeriod(Period.builder().start(LocalDateTime.now()).end(LocalDateTime.now()).build());
        identifier4.setUse(Identifier.UseCode.old);
        identifier4.setValue("Identifiervalue");

        Period period = new Period(LocalDateTime.of(2000, 01,01,1,1), LocalDateTime.of(2001,01,01,1,1));
        Reference r1 = new Reference("/api/practitioner/p1", "Encounter", identifier1, "behandelnder Arzt");
        Reference subjectReference = new Reference("/api/patient/p1", "Patient", identifier2, "Betroffener Patient");
        Reference partOfReference = new Reference("/api/encounter/enc1", "Encounter", identifier3, "Mitglieder");
        conditions.add(new Reference("/api/conditions/cond1", "Condition", identifier4, "Zustand"));
        CodeableConcept ccType = new CodeableConcept(codings, "Text");
        codings.add(new Coding("System", "0.1.1", "Code", "<div>...<div>", false));
        cc.add(new CodeableConcept(codings, "CodCoc"));
        diagnosis.add(new Diagnosis(conditions, ccType, 5));

        identifiers.add(Identifier.builder()
                .value("0123012345")
                .type(CodeableConcept.builder()
                        .coding(codings)
                        .build())
                .use(Identifier.UseCode.official)
                .build());



        statusHistory.add(new StatusHistory(StatusHistory.EncounterStatus.finished, period));
        episodeOfCare.add(new Reference("/api/episode", "EpisodeOfCare", identifier1, "alternative"));
        participants.add(new Participant(cc, period, r1));
        appointment.add(new Reference("/api/references/ref1", "Appointment", identifier2, "Liste der bisherigen Termine"));
        reasonReference.add(new Reference("/api/procedures/proc1", "Procedure", identifier3, "Grund"));

        identifiers.add(identifier4);
        Encounter enc2 = new Encounter(identifiers, Encounter.StatusCode.finished, statusHistory, cc, subjectReference, episodeOfCare, participants, appointment, period, reasonReference, diagnosis, partOfReference);
        return enc2;
    }
}

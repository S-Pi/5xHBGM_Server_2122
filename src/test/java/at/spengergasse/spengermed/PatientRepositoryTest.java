package at.spengergasse.spengermed;


import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.PatientRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//Durch diese Annotation wird aus der Klasse eine Testklasse
@SpringBootTest
public class PatientRepositoryTest {
    @Autowired
    PatientRepository patientRepository;
    @Test
    @Transactional
    public void testSaveAndLoadOnePatient(){
        //1. Erstellen einer mit Daten befüllten Patienteninstanz
        Patient p = returnOnePatient();
        //2. Instanz in die DB speichern
        Patient savedP = patientRepository.save(p);
        //3. Gespeicherte Daten aus der DB lesen
        Patient loadedPatient = patientRepository.findById(savedP.getId()).get();
        System.out.println(loadedPatient);
        //4. Vergleich des gespeicherten Objekts mit dem geladenen


        //Alle einfachen Datentypen können mit Equals verglichen werden.
        //Assert prüft, ob die beiden gleich sind. Schlägt ein Assert fehl, ist der Test fehlgeschlagen
        //Asserts sind die eigentlichen "Tests"
        assertEquals(p.getBirthDate(),savedP.getBirthDate());
        assertEquals(p.getDeceasedDateTime(),savedP.getDeceasedDateTime());
        assertEquals(p.getBirthDate(),savedP.getBirthDate());
        assertEquals(p.getText(),savedP.getText());
        assertEquals(p.getGender(),savedP.getGender());
        //Es sollen alle Attribute verglichen werden, auch die geerbten.

        //Collections werden mit CollectionUtils auf gleichheit getestet.
        // Dabei werden die einzelnen Elemente verglichen,nicht ob die Collectionobjekte gleich sind.
        assertTrue(CollectionUtils.isEqualCollection(p.getIdentifier(), loadedPatient.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(p.getName(), loadedPatient.getName()));
        assertTrue(CollectionUtils.isEqualCollection(p.getTelecom(), loadedPatient.getTelecom()));
        assertTrue(CollectionUtils.isEqualCollection(p.getAddress(), loadedPatient.getAddress()));
        //Es sollen alle Collections getestet werden.
    }

    //Ein Patientenobjekt. Alle Attribute sollen Werte bekommen.
    //Auch die Collections sollen zumindest 1 Wert haben.
    public static Patient returnOnePatient() {


        List<ContactPoint> telecoms = new ArrayList<>();

        List<HumanName> names = new ArrayList<>();
        names.add(HumanName.builder()
                .family("Pirker")
                .given(List.of("Simon"))
                .prefix(List.of("Mag.", "DI"))
                .suffix(List.of("Bakk.techn."))
                .period(new Period(LocalDateTime.of(2010,01,01,0,0,0),
                        LocalDateTime.of(2050,01,01,0,0,0)))
                .text("<div> Mag. DI Simon Pirker, Bakk.techn.</div>")
                .use(HumanName.UseCode.official)
                .build());

        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> ccCodings = new ArrayList<>();
        ccCodings.add(Coding.builder()
                .display("Sozialversicherungsnummer")
                .code("SS")
                .build());
        identifiers.add(Identifier.builder()
                .value("0123012345")
                .type(CodeableConcept.builder()
                        .coding(ccCodings)
                        .build())
                .use(Identifier.UseCode.official)
                .build());

        List<Coding> ccCodings1 = new ArrayList<>();
        ccCodings1.add(Coding.builder()
                .display("Führerscheinnummer")
                .code("DL")
                .build());
        identifiers.add(Identifier.builder()
                .value("01234567")
                .type(CodeableConcept.builder()
                        .coding(ccCodings1)
                        .build())
                .use(Identifier.UseCode.official)
                .build());

        List<Address> addresses = new ArrayList<>();

        List<String> addressLines = new ArrayList<>();
        addressLines.add("Simon Pirker");
        addressLines.add("Spengergasse 20");
        addressLines.add("1050 Vienna");
        addressLines.add("Austria");

        addresses.add(Address.builder()
                .use(Address.UseCode.work)
                .type(Address.TypeCode.postal)
                .district("Margareten")
                .country("Austria")
                .city("Vienna")
                .postalcode("1050")
                .state("Vienna")
                .period(new Period(LocalDateTime.of(2010,01,01,0,0,0),
                        LocalDateTime.of(2050,01,01,0,0,0)))
                .line(addressLines)
                .text("<div>Address...</div>")
                .build());

        telecoms.add(ContactPoint.builder()
                .system(ContactPoint.SystemCode.email)
                .use(ContactPoint.UseCode.work)
                .value("pirker@spengergasse.at")
                .rank(1)
                .period(new Period(LocalDateTime.of(2010,01,01,0,0,0),
                        LocalDateTime.of(2030,01,01,0,0,0)))
                .build());

        telecoms.add(ContactPoint.builder()
                .system(ContactPoint.SystemCode.phone)
                .use(ContactPoint.UseCode.work)
                .value("01234256")
                .rank(1)
                .period(new Period(LocalDateTime.of(2010,01,01,0,0,0),
                        LocalDateTime.of(2030,01,01,0,0,0)))
                .build());

        Patient p = Patient.builder()
                .telecom(telecoms)
                .identifier(identifiers)
                .name(names)
                .gender(Patient.GenderCode.female)
                .deceasedDateTime(LocalDateTime.of(1990,01,02,15,0,0))
                .birthDate(LocalDate.of(1960,01,02))
                .address(addresses)
                .active(false)
                .build();
        return p;
    }
}

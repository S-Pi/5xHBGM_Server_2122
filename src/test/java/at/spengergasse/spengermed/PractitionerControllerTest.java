package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.repository.PractitionerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@AutoConfigureMockMvc
@SpringBootTest
public class PractitionerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Autowired
    PractitionerRepository practitionerRepository;

    //Erster Test, um alle Practitioneren unter der URL /api/practitioner mit GET abzufragen.
    //andExpect überprüft, ob der zurückgegebene Status 200 (OK) ist.
    @Test
    public void
    getAllPractitioners(){
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/practitioner"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Ein einzelner Practitioner wird mit der id mit GET abgefragt. Dabei muss der Practitioner mit der id in der DB existieren.
    //im import.sql muss dieser Practitioner somit eingefügt werden.
    //andExpect überprüft, ob der zurückgegebene Status 200 (OK) ist.
    @Test
    public void
    getAPractitioner(){
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/practitioner/p1"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Es wird ein neuer Practitioner mit POST an den Controller geschickt und somit in der DB gespeichert.
    //Wir können die Methode aus PractitionerRepositoryTest, die uns eine Practitioneren Instanz erzeugt auch hier verwenden.
    //Der erwartete Rückgabecode ist "CREATED" Also 201.
    @Test
    public void
    postAPractitioner(){
        Practitioner practitioner = PractitionerRepositoryTest.returnOnePractitioner();
        String json= null;
        try {
            json = om.writeValueAsString(practitioner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/practitioner/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //PUT aktualisiert einen Practitioneren. Dieser muss somit bereits in der DB existieren (über import.sql)
    //Die id im Practitioneren und die id in der URL sollten gesetzt sein und müssen in der DB existieren.
    //Wir erwarten ein 200- OK für einen aktualisierten Practitioneren.
    //Kein 201 CREATED, sonst wäre der Practitioner neu angelegt worden.
    @Test
    public void
    putAPractitioner(){
        List<Practitioner> practitionerList = new ArrayList<Practitioner>();
        practitionerRepository.findAll().forEach(practitionerList::add);
        Practitioner practitioner = practitionerList.get(0);
        //var practitioner = PractitionerRepositoryTest.returnOnePractitioner();
        assertNotNull(practitioner);

        //Ein paar Attribute werden geändert
        practitioner.setActive(!practitioner.isActive());
        practitioner.setGender(Practitioner.GenderCode.unknown);

        String json= null;
        try {
            json = om.writeValueAsString(practitioner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.put("/api/practitioner/7439re")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Der Practitioner wird geöscht.
    //Die id muss es bereits geben.
    //Erwartete Antwort ist 200 (OK)
    @Test
    public void
    deleteAPractitioner(){
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.delete("/api/practitioner/p2"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

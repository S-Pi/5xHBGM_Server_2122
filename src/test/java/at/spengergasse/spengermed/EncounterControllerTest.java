package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.repository.EncounterRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@AutoConfigureMockMvc
@SpringBootTest
public class EncounterControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Autowired
    EncounterRepository encounterRepository;

    //Erster Test, um alle Encounteren unter der URL /api/encounter mit GET abzufragen.
    //andExpect überprüft, ob der zurückgegebene Status 200 (OK) ist.
    @Test
    public void
    getAllEncounters(){
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/encounter"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Ein einzelner Encounter wird mit der id mit GET abgefragt. Dabei muss der Encounter mit der id in der DB existieren.
    //im import.sql muss dieser Encounter somit eingefügt werden.
    //andExpect überprüft, ob der zurückgegebene Status 200 (OK) ist.
    @Test
    public void
    getAEncounter(){
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/encounter/enc1"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Es wird ein neuer Encounter mit POST an den Controller geschickt und somit in der DB gespeichert.
    //Wir können die Methode aus EncounterRepositoryTest, die uns eine Encounteren Instanz erzeugt auch hier verwenden.
    //Der erwartete Rückgabecode ist "CREATED" Also 201.
    @Test
    public void
    postAEncounter(){
        Encounter encounter = EncounterRepositoryTest.returnOneEncounter();
        String json= null;
        try {
            json = om.writeValueAsString(encounter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/encounter/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //PUT aktualisiert einen Encounteren. Dieser muss somit bereits in der DB existieren (über import.sql)
    //Die id im Encounteren und die id in der URL sollten gesetzt sein und müssen in der DB existieren.
    //Wir erwarten ein 200- OK für einen aktualisierten Encounteren.
    //Kein 201 CREATED, sonst wäre der Encounter neu angelegt worden.
    @Test
    public void
    putAEncounter(){
        List<Encounter> encounterList = new ArrayList<Encounter>();
        encounterRepository.findAll().forEach(encounterList::add);
        Encounter encounter = encounterList.get(0);
        //var encounter = EncounterRepositoryTest.returnOneEncounter();
        assertNotNull(encounter);

        //Ein paar Attribute werden geändert
        encounter.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
        encounter.setStatus(Encounter.StatusCode.onleave);

        String json= null;
        try {
            json = om.writeValueAsString(encounter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.put("/api/encounter/7439re")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Der Encounter wird geöscht.
    //Die id muss es bereits geben.
    //Erwartete Antwort ist 200 (OK)
    @Test
    public void
    deleteAEncounter(){
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.delete("/api/encounter/gjuerighirgh"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

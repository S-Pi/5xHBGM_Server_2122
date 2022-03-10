package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping(path = "/api/encounter")
@RestController
public class EncounterController {

    @Autowired
    private EncounterRepository encounterRepository;


    @GetMapping
    public @ResponseBody
    Iterable<Encounter> getAllEncounters() {
        // This returns a JSON or XML with the encounters
        return encounterRepository.findAll();
    }

    //get encounter by id
    @GetMapping("/{id}")
    public ResponseEntity<Encounter> getEncounter(@PathVariable String id) {
        return encounterRepository
                .findById(id)
                .map(encounter -> ResponseEntity.ok().body(encounter))
                .orElse(ResponseEntity.notFound().build());
    }

    //create encounter
    @PostMapping()
    public ResponseEntity<Encounter> createEncounter(@Valid @RequestBody Encounter encounter) {
        var saved = encounterRepository.save(encounter);
        return ResponseEntity.created(URI.create("api/encounter/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encounter> updateEncounter(@PathVariable(value = "id") String encounterId, @Valid @RequestBody Encounter encounterDetails) {
        return encounterRepository
                .findById(encounterId)
                .map(
                        encounter -> {
                            encounter.setStatus(encounterDetails.getStatus());
                            encounter.setStatusHistory(encounterDetails.getStatusHistory());
                            encounter.setType(encounterDetails.getType());
                            encounter.setSubject(encounterDetails.getSubject());
                            encounter.setEpisodeOfCare(encounterDetails.getEpisodeOfCare());
                            encounter.setParticipant(encounterDetails.getParticipant());
                            encounter.setAppointment(encounterDetails.getAppointment());
                            encounter.setPeriod(encounterDetails.getPeriod());
                            encounter.setReasonReference(encounterDetails.getReasonReference());
                            encounter.setDiagnosis(encounterDetails.getDiagnosis());
                            encounter.setPartOf(encounterDetails.getPartOf());

                            Encounter updatedEncounter = encounterRepository.save(encounter);
                            return ResponseEntity.ok(updatedEncounter);
                        }
                ).orElseGet(()-> createEncounter(encounterDetails));
    }

    //Delete an Encounter
    @DeleteMapping("/{id}")
    public ResponseEntity<Encounter> deleteEncounter(@PathVariable(value = "id") String encounterId) {
        return encounterRepository
                .findById(encounterId)
                .map(
                        encounter -> {
                            encounterRepository.delete(encounter);
                            return ResponseEntity.ok().<Encounter>build();
                        }
                ).orElse(ResponseEntity.notFound().build());
    }
}

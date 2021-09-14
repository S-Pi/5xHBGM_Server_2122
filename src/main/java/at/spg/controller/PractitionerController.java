package at.spg.controller;

import at.spg.model.Encounter;
import at.spg.model.Practitioner;
import at.spg.repository.EncounterRepository;
import at.spg.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping(path="/api/practitioner")
@RestController
public class PractitionerController {

	@Autowired
	private PractitionerRepository practitionerRepository;
  
	
  @GetMapping
  public @ResponseBody Iterable<Practitioner> getAllEncounters() {
    // This returns a JSON or XML with the practitioners
    return practitionerRepository.findAll();
  }

  //get Practitioner by id
  @GetMapping("/{id}")
    public ResponseEntity<Practitioner> getPractitioner(@PathVariable String id){
      return practitionerRepository
              .findById(id)
              .map(practitioner -> ResponseEntity.ok().body(practitioner))
              .orElse(ResponseEntity.notFound().build());
  }

  //Create Practitioner
    @PostMapping()
    public ResponseEntity<Practitioner> createPractitioner(@Valid @RequestBody Practitioner practitioner){
      practitioner.getName().forEach(n -> n.setId(null)); //To get new Name
        var saved = practitionerRepository.save(practitioner);
        return ResponseEntity.created(URI.create("/api/practitioner/" + saved.getId())).body(saved);
    }

    //Update Practitioner
    @PutMapping("/{id}")
    public ResponseEntity<Practitioner> updatePractitioner(@PathVariable(value="id") String practitionerId, @Valid @RequestBody Practitioner practitionerDetails){
      return practitionerRepository
              .findById(practitionerId)
              .map(
                      practitioner -> {
                          practitioner.setActive(practitionerDetails.isActive());
                          practitioner.setName(practitionerDetails.getName());
                          practitioner.setTelecom(practitionerDetails.getTelecom());
                          practitioner.setAddress(practitionerDetails.getAddress());
                          practitioner.setGender(practitionerDetails.getGender());
                          practitioner.setBirthDate(practitionerDetails.getBirthDate());
                          practitioner.setPhoto(practitionerDetails.getPhoto());
                          practitioner.setCommunication(practitionerDetails.getCommunication());

                          Practitioner updatedPractitioner = practitionerRepository.save(practitioner);
                          return ResponseEntity.ok(updatedPractitioner);
                      })
              .orElseGet(()-> createPractitioner(practitionerDetails));

    }

    //Delete Practitioner
    @DeleteMapping("/{id}")
    public ResponseEntity<Practitioner> deletePractitioner(@PathVariable(value="id") String practitionerId){
      return practitionerRepository
              .findById(practitionerId)
              .map(
                      practitioner -> {
                          practitionerRepository.delete(practitioner);
                          return ResponseEntity.ok().<Practitioner>build();
                      })
              .orElse(ResponseEntity.notFound().build());
    }
}

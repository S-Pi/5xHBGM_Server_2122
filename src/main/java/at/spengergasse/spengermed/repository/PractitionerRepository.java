package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Practitioner;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PractitionerRepository extends PagingAndSortingRepository<Practitioner, String> {
}

package at.spg.repository;

import at.spg.model.Practitioner;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PractitionerRepository extends PagingAndSortingRepository<Practitioner, String> {
}

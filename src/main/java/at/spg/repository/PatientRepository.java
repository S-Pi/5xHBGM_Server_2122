package at.spg.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import at.spg.model.Patient;

public interface PatientRepository extends PagingAndSortingRepository<Patient, String>{
    List<Patient> findById(@Param("id") long id);

}

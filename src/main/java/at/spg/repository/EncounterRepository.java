package at.spg.repository;

import at.spg.model.Encounter;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EncounterRepository extends PagingAndSortingRepository<Encounter, String> {
}

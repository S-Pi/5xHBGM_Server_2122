package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Encounter;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EncounterRepository extends PagingAndSortingRepository<Encounter, String> {
}

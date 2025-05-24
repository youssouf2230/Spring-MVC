package net.youssouf.hopital.repositories;

import net.youssouf.hopital.entities.Patient;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNameContainingIgnoreCase(String keyword,Pageable pageable);
}

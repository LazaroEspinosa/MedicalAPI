package com.kosmos.medicalappointment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.medicalappointment.model.Medico;

/*
 * Aunque en teoria se puede agregar otro repositorio en el mismo file,
 * con fines de organizacion y facilidad de lectura es necesario crear un repositorio en una interface nueva.
 */

public interface MedicoRepository extends JpaRepository<Medico, Long>{

}

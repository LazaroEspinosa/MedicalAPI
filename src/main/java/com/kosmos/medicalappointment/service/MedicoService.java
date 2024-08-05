package com.kosmos.medicalappointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.kosmos.medicalappointment.dto.DTOMedicoListado;
import com.kosmos.medicalappointment.dto.DTOMedico;
import com.kosmos.medicalappointment.model.Medico;
import com.kosmos.medicalappointment.repository.AppointmentRepository;
import com.kosmos.medicalappointment.repository.MedicoRepository;
/*
 * Aqui se implementan todas las operaciones de las reglas de negocio.
 * El Metodo addMedico es similar al de addAppointment en AppointmentController, omitimos su explicacion.
 * 
 * El metodo ListaMedico explicado:
 * (Pageable paginacion) Recibe como parametro un objeto tipo Pageable (Ya Explicado en controller).
 * medicoRepository.findAll(paginacion) hace que la instancia de MedicoRespository llama a su metodo findAll
 * usando una instancia de Pageable como parametro, devolviendo un Page compuesta de objetos Medico.
 * La razon por la cual devuelve un Page de Medicos es porque asi se establecio en MedicoRepository extends JpaRepository<Medico, Long>.
 * .map(DTOMedicoListado::new) toma cada medico devuelto y lo convierte a tipo DatosListadoMedico.
 * Page<DTOMedicoListado> Devuelve un valor de tipo Page que esta compuesto de n objetos de DTOMedicoListado
 * 
 * Page y Pageable son interfaces que se utilizan para implementar la paginación y ordenación de los resultados de las consultas.
 * Pageable contiene los datos necesarios para que la URL tenga los parametros de la solicitud
 * (Es como un complemento de la URL para manipular la informacion que se mostrara)
 * 
 * Pageable representa información de paginación, como el número de la página y el tamaño de la página.
 * También puede contener información sobre el orden en que se deben devolver los resultados. Ejemplo:
 * PageRequest.of(int page, int size, Sort sort)
 * Crea una instancia de PageRequest con el número de página, el tamaño de la página y el ordenamiento especificado.
 * Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
 * 
 * Page representa una página de datos que se obtiene de una consulta de base de datos.
 * Proporciona información sobre la página actual, el número total de páginas, el número total de elementos y el contenido de la página actual.
 * Para utilizar la paginación en un repositorio de Spring Data JPA, simplemente añade un parámetro Pageable a tu método de consulta. El repositorio devolverá un objeto Page
 * Métodos importantes en Page:
 * getContent(): Devuelve la lista de elementos en la página actual.
 * getTotalPages(): Devuelve el número total de páginas.
 * getTotalElements(): Devuelve el número total de elementos en todas las páginas.
 * getNumber(): Devuelve el número de la página actual.
 * getSize(): Devuelve el tamaño de la página.
 * hasNext(): Indica si hay una página siguiente.
 * hasPrevious(): Indica si hay una página anterior.
 * 
 */
@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public String addMedico(DTOMedico dtoMedico) {
		Medico medico = new Medico(dtoMedico);
		medicoRepository.save(medico);
		return "Medico added";
	}
	
	public Page<DTOMedicoListado> listaMedico(Pageable paginacion) {
		return medicoRepository.findAll(paginacion).map(DTOMedicoListado::new);
	}
}

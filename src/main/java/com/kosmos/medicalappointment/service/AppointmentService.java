package com.kosmos.medicalappointment.service;

/*
 * Service es una capa que sirve para implementar todas las reglas de negocio.
 * Al utilizar Service es necesario instanciarlo en la clase controller:
 * 
 * @Autowired
 * private AppointmentService appointmentService;
 * 
 * Eventualmente se puede usar la instancia para utilizar uno de los metodos de la clase:
 * appointmentService.saveAppointment(dtoAppointment);
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosmos.medicalappointment.dto.DTOAppointment;
import com.kosmos.medicalappointment.model.Appointment;
import com.kosmos.medicalappointment.repository.AppointmentRepository;

/*
 * Implementaciones:
 * 
 * showByMedico: 
 * Establece que recibe como parametro un String con el nombre del medico y los requerimientos de paginacion.
 * Devuelve una lista tipo Page con DTOs de Appointment.
 * Para eso llama a appointmentRepository que como se establecio inicialmente, devolvera una Page de Appointments
 * Usando findByMedico hacemos que Spring JPA nos ayude a encontrar los registros que coincidan con el nombre del medico que se compartio.
 * Usando map(DTOAppointment::new) hacemos que cada modelo Appointment sea convertido a un nuevo DTO.
 */

/*
 * Errores contados(3), Errores solucionados(3):
 * 1. Al agregar la clase AppointmentService y querer iniciar la aplicacion, surgio el siguiente error:
 * 
 * Field appointmentService in com.kosmos.medicalappointment.controller.AppointmentController 
 * required a bean of type 'com.kosmos.medicalappointment.service.AppointmentService' that could not be found.
 * 
 * La forma de solucionarlo fue agregando la anotacion @Service a la clase.
 * @Service: se utiliza para definir beans en la capa de servicio. Indica que una clase realiza tareas de servicio
 * Cuando anotas una clase con @Service, le estás diciendo a Spring que esta clase debe ser gestionada como un bean
 * y que pertenece a la capa de servicio.
 * Esto permite que Spring escanee, detecte y registre esta clase como un bean en el contexto de la aplicación.
 * 
 * 2. Al implementar:
 * public Page<DTOAppointment> showAllAppointments(Pageable paginacion) {
 * 		return appointmentRepository.findAll(paginacion).map(DTOAppointment::new);
 * }
 * Surge el error:
 * The type DTOAppointment does not define DTOAppointment(Appointment) that is applicable here
 * 
 * El error fue extenso. Al iniciar el proyecto, se comenzo mapeando lo necesario para el appointment. De forma que en el DTO, sus parametros eran otros DTOs.
 * Las opciones eran crear un DTOAppointmentList como para los Medicos. Pero era un tanto redundante pues los datos de Post y Get si son los mismos.
 * Por lo tanto se soluciona modificando el DTO y la entidad (Model). Consultar Appointment para + info.
 * 
 * 3. Error similar al 2:The type DTOAppointment does not define DTOAppointment(DTOAppointment) that is applicable here
 * Al querer implementar:
 * public Page<DTOAppointment>showByMedico(String medico, Pageable paginacion) {
 * 		return appointmentRepository.findByMedico(medico, paginacion).map(DTOAppointment::new);
 * }
 * Solucion en AppointmentRepository.
 */

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	private static final DateTimeFormatter formatterStringToDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	public String addAppointment(DTOAppointment dtoAppointment) {
		Appointment appointment = new Appointment(dtoAppointment);
		appointmentRepository.save(appointment);
		return "Appointment added";
	}
	
	public Page<DTOAppointment> showAllAppointments(Pageable paginacion) {
		return appointmentRepository.findAll(paginacion).map(DTOAppointment::new);
	}

	public Page<DTOAppointment>showByMedico(String medico, Pageable paginacion) {
		return appointmentRepository.findByMedico(medico, paginacion).map(DTOAppointment::new);
	}
}

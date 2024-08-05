package com.kosmos.medicalappointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.medicalappointment.dto.DTOMedico;
import com.kosmos.medicalappointment.dto.DTOMedicoListado;
import com.kosmos.medicalappointment.service.MedicoService;

import jakarta.validation.Valid;

/*
 * Errores encontrados(3) Errores solucionados(3):
 * 
 * "status": 404, "error": "Not Found", "message": "No static resource Medico/new.", "path": "/Medico/new"
 * >>la direccion ingresada en insomnia debe respetar upper and lower cases establecidas en @RequestMapping
 * 
 * "message": "No static resource Medico/new.", "path": "/Medico/new", "message": "could not execute statement 
 * [Unknown column 'materno' in 'field list'] [insert into medicos (cedula,especialidad,materno,nombre,paterno) 
 * values (?,?,?,?,?)]; SQL [insert into medicos (cedula,especialidad,materno,nombre,paterno) values (?,?,?,?,?)]",
 * "path": "/medico/new"
 * 
 * 	"status": 500, "error": "Internal Server Error", "message": "could not execute statement [Unknown column 'materno' in 'field list']
 * [insert into medicos (cedula,especialidad,materno,nombre,paterno)
 * values (?,?,?,?,?)]; SQL [insert into medicos (cedula,especialidad,materno,nombre,paterno) values (?,?,?,?,?)]",
 * "path": "/medico/new"
 * >>Los dos ultimos errores eran por un mal JSON. Al usar el mismo DTOMedico para Appointment y Medico asumi usar el mismo JSON.
 * >>Pero no, Al ser directo, no necesito una clave Medico con un valor que anide otras claves, sino valores claves equivalentes a los parametros y atributos.
 * 
 * Contrario a lo que pensaba, las claves del JSON, los parametros del DTO, atributos del modelo y campos de la tabla no deben llevar el mismo nombre identicamente.
 * Pero si deben tener cierto orden y claridad, por ejemplo, la tabla usa _ para separar apellidoPaterno que es el atributo del modelo Medico,
 *  a su vez, el DTO y JSON solo usan la clave y parametro paterno. Estos dos si deben ser iguales para saber donde asignar el nombre
 */

/*
 * Detalles:
 * @Valid se agrega al @RequestBody para que la dependencia valide que los datos de ingresados por el usuario.
 * Para verificar que las validaciones estan haciendo su trabajo, se puede enviar un parametro vacio en donde @NotBlank esta especificado.
 * El error mostrado debe ser un Error 40 Bad Request.
 * 
 * El metodo showAllMedicos tiene la intencion de mostrar todos los medicos registrados en la DB.
 * El metodo recibe un parametro tipo Pageable con las especificaciones del usuario.
 * Este dato se establece desde la URL usando Query params o se puede asignar en el codigo: Pageable paginacion = PageRequest.of(0, 10, Sort.by("name").ascending());
 * El metodo usa la instancia de MedicoService para llamar al metodo listaMedico(paginacion). Este metodo debe devolver un Page<DTOMedicoListado>.
 * Consultar MedicoService para mas informacion.
 */

@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	@RequestMapping("/new")
	public String newMedico(@RequestBody @Valid DTOMedico dtoMedico) {
		//System.out.println(dtoMedico);
		medicoService.addMedico(dtoMedico);
		return "newMedico completed";
	}
	

	@GetMapping
	@RequestMapping("/showAll")
	public Page<DTOMedicoListado> showAllMedicos(Pageable paginacion) {
		System.out.println("This is paginacion value"+paginacion);
		return medicoService.listaMedico(paginacion);
	}

}

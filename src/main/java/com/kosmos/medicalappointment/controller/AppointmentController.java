package com.kosmos.medicalappointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.medicalappointment.dto.DTOAppointment;
import com.kosmos.medicalappointment.service.AppointmentService;

import jakarta.validation.Valid;


/*
 * La arquitectura del desarrollo de l proyecto esta basada en el modelo de capas:
 * Controller, Service, Repository, DTO, Model y Configuration
 * Comenzamos creando el proyeco en https://start.spring.io/
 * Asignamos el nombre de la empresa al Group com.kosmos y el del proyecto al Artifact: medicalAPI.
 * Las dependencias a utilizar son Spring Web, Spring Data JPA, MySQL Driver, Lombok, Validation, Spring Boot DevTools, FlyWay
 * Es posible que Lombok no funcione correctamente en Eclipse.
 * Usar https://projectlombok.org/setup/eclipse y seguir las instrucciones. Metodo funcional comprobado: via eclipse plugin installer.
 * 
 * Controller
 * @RestController es la combinacion de @Controller y @ResponseBody. Indica que esta clase es un controlador RESTful.
 * @RequestMapping ("direccion/compuesta/aqui") se utiliza para asignar direcciones web.
 * Ya declarada cual es la clase controller y especificar su direccion de solicitud podemos continuar con el proyecto.
 * Comenzamos trabajando con un metodo POST en insomnia. La idea es usar el metodo para agregar los registros de citas a una tabla en una base de datos.
 * Para indicar que metodo de la clase se usara con la solicitud POST agregamos un @PostMapping.
 * Al ser el unico metodo, no es necesario especificar su direccion de solicitud con el @RequestMapping.
 * 
 * Comenzamos trabajando con un metodo POST en insomnia. La idea es usar el metodo para agregar los registros de citas a una tabla en una base de datos.
 * Para indicar que metodo de la clase se usara con la solicitud P OST agregamos un @PostMapping.
 * Al ser el unico metodo, no es necesario especificar su direccion de solicitud con el @RequestMapping.
 * Insomnia nos ayuda simulando el FrontEnd y envia el Body del request POST.
 * Por lo tanto el metodo recibira ese body como un parametro y para comprobar que funciona, devolvera un string.
 * Spring boot no puede por si solo identificar que es el parametro que recibe, pues desconoce si el usuario usara ese parametro para algo mas.
 * Para que SpringBoot lo detecte, sera necesario usar @RequestBody.
 * Por arquitectura, lo que el controller recibira en esta ocasion sera un DTO tipo Class Record y sera necesario mapearla de forma adecuada.
 * Naturalmente, declaramos que el parametro es de tipo DTOAppointment y su nombre sera dtoAppointment. El IDE nos ayuda y sugiere crear el Record.
 * 
 * Cambio:
 * Se implementa la clase ApointmentService para separar las reglas de negocio de otras clases.
 * La primer optimizacion es transportar el proceso de convertir un DTO a un Model y de paso agregarlo en la DB en un solo metodo.
 * createAppointment() recibe el DTO y llama al metodo saveAppointment() de la clase AppointmentService usando una instancia previamente creada.
 * Usa el DTO y lo registra como entidad en la DB.
 * 
 * @Valid se agrega al @RequestBody para que la dependencia valide que los datos de ingresados por el usuario.
 * 1 Error solucionado: Consultar AppointmentService para mas detalles.
 * 
 * PENDIENTES:
 * ALTERNATIVAS MAS SEGURAS AL @AUTOWIRED.
 * AGREGAR BindingResult
 */

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	@RequestMapping("/new")
	public String createAppointment(@Valid @RequestBody DTOAppointment dtoAppointment) {
		appointmentService.addAppointment(dtoAppointment);
		 //System.out.println(dtoAppointment.medico());
		
		return "Appointment scheduled: "+dtoAppointment;
	}
}

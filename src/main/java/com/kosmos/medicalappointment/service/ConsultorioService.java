package com.kosmos.medicalappointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosmos.medicalappointment.dto.DTOConsultorio;
import com.kosmos.medicalappointment.model.Consultorio;
import com.kosmos.medicalappointment.repository.ConsultorioRepository;

/*
 * Errores detectados (1) Resueltos (1):
 * Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
 * 2024-08-03T10:28:58.685-06:00 ERROR 12512 --- [medicalappointment] [  restartedMain] o.s.b.d.LoggingFailureAnalysisReporter   : 
 * Description:
 * Field consultorioService in com.kosmos.medicalappointment.controller.ConsultorioController required a bean of type
 * 'com.kosmos.medicalappointment.service.ConsultorioService' that could not be found.
 * The injection point has the following annotations:
 * 	- @org.springframework.beans.factory.annotation.Autowired(required=true)
 * Action: Consider defining a bean of type 'com.kosmos.medicalappointment.service.ConsultorioService' in your configuration.
 * 
 * Solucion: Agregar @Service a la clase para que el bean sea reconocido
 */
@Service
public class ConsultorioService {
	
	@Autowired
	ConsultorioRepository consultorioRepository;
	
	public String addConsultorio(DTOConsultorio dtoConsultorio) {
		Consultorio  consultorio = new Consultorio (dtoConsultorio);
		consultorioRepository.save(consultorio);
		return "Consultorio added";
	}

}

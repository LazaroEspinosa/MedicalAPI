package com.kosmos.medicalappointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.medicalappointment.dto.DTOConsultorio;
import com.kosmos.medicalappointment.service.ConsultorioService;

/*
 * Errores: Detectados (1) Solucionados(1)
 * Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
 * 2024-08-03T10:31:53.686-06:00 ERROR 16300
 */

@RestController
@RequestMapping("/consultorio")
public class ConsultorioController {
	
	@Autowired
	ConsultorioService consultorioService;
	
	@PostMapping
	@RequestMapping("/new")
	String newConsultorio(@RequestBody DTOConsultorio dtoConsultorio){
		consultorioService.addConsultorio(dtoConsultorio);
		return "newConsultorio completed";
	}
}

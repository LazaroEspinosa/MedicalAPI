package com.kosmos.medicalappointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.medicalappointment.dto.DTOAppointment;
import com.kosmos.medicalappointment.entity.Appointment;
import com.kosmos.medicalappointment.repository.AppointmentRepository;

@RestController
public class AppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@PostMapping("/appointment/new")
	public String createAppointment(@RequestBody DTOAppointment dtoAppointment) {
		appointmentRepository.save(new Appointment(dtoAppointment));
		return "Appointment scheduled: "+dtoAppointment;
	}

}

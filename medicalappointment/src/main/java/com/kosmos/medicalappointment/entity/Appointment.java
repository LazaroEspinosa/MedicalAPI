package com.kosmos.medicalappointment.entity;

import java.time.LocalDateTime;

import com.kosmos.medicalappointment.dto.DTOAppointment;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Appointment")
@Table(name="appointments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@Embedded	
	Consultorio consultorio;
	@Embedded
	Medico medico;
	LocalDateTime fechaDeConsulta;
	String paciente;
	
	public Appointment(DTOAppointment dtoAppointment) {
		this.fechaDeConsulta=dtoAppointment.fechaDeConsulta();
		this.paciente=dtoAppointment.paciente();
		this.consultorio=new Consultorio(dtoAppointment.consultorio());
		this.medico=new Medico(dtoAppointment.medico());
	}
}

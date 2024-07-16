package com.kosmos.medicalappointment.entity;

import com.kosmos.medicalappointment.dto.DTOMedico;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
	
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String especialidad;
	
	public Medico(DTOMedico medico) {
		this.nombre=medico.nombre();
		this.apellidoPaterno=medico.apellidoPaterno();
		this.apellidoMaterno=medico.apellidoMaterno();
		this.especialidad=medico.especialidad();
	}

}

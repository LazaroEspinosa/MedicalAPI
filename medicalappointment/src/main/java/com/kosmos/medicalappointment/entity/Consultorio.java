package com.kosmos.medicalappointment.entity;

import com.kosmos.medicalappointment.dto.DTOConsultorio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Consultorio {
	
	String piso;
	String numero;
	
	public Consultorio(DTOConsultorio consultorio) {
		this.piso=consultorio.piso();
		this.numero=consultorio.numero();
	}

}

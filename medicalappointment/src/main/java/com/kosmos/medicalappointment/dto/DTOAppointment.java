package com.kosmos.medicalappointment.dto;

import java.time.LocalDateTime;

public record DTOAppointment(
		DTOConsultorio consultorio,
		DTOMedico medico,
		LocalDateTime fechaDeConsulta,
		String paciente
		) {

}

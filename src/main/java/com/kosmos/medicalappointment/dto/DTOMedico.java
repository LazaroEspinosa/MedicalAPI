package com.kosmos.medicalappointment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
 * DTOMedico se crea para mapear correctamente los valores de la clave Medico del JSON marcado con @RequestBody.
 * Dentro de este, no hay mas parametros compuestos, entonces al ser todos sus valores String- 
 * (pueden ser int, long u otros) no se requiere crear mas DTOs derivados de este.
 */
public record DTOMedico(
		@NotBlank(message="El nombre es obligatorio")
		String nombre,
		
		@NotBlank(message="El apellido paterno es obligatorio")
		String paterno,
		
		@NotBlank(message="El apellido materno es obligatorio")
		String materno,
		
		@Pattern(regexp="\\d{10}", message="El numero telefonico debe tener 10 digitos")
		String telefono,
		
		@NotBlank(message="El numero de cedula es obligatorio")
		String cedula,
		
		String especialidad) {
}

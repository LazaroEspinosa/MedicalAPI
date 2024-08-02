package com.kosmos.medicalappointment.dto;

/*
 * DTOMedico se crea para mapear correctamente los valores de la clave Medico del JSON marcado con @RequestBody.
 * Dentro de este, no hay mas parametros compuestos, entonces al ser todos sus valores String- 
 * (pueden ser int, long u otros) no se requiere crear mas DTOs derivados de este.
 */
public record DTOConsultorio(
		String piso,
		String numero) {
}

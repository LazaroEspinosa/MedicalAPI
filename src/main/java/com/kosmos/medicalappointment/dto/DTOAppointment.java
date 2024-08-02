package com.kosmos.medicalappointment.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Data Transfer Object (DTO)
 * Los DTO son exclusivamente para portar informacion. 
 * Es cierto que pueden tener metodos, pero la arquitectura de capas ayuda a tener un codigo mejor organizado y por tanto entendible.
 * El DTO es un record, y normalmente no tiene metodos. Pero si parametros, estos deben estar mapeados correctamente.
 * Los parametros consultorio y medico, son compuestos, es decir, la clave del JSON que reciben no contiene un valor String o int.
 * Sino otra serie de datos en su interior, por lo tanto, se crean sus respectivos DTOs que recibiran esa informacion adecuadamente.
 * Notese, que no existe una anotacion a comparacion del controller o de la entidad.
 * Al usar @RequestBody, y mapear correctamente los records, SpringBoot se encarga de almacenar en las instancias los valores correspondientes.
 * Al finalizar el mapeo de los DTOs se requiere crear los modelos y entidades correspondientes.
 * 
 * Validations:
 * Aseguran que los datos ingresados en las aplicaciones cumplan con ciertos criterios
 * antes de ser procesados o almacenados en la base de datos.
 * Para que las validations puedan ser correctamente verificadas, el requestbody debe usar @Valid (Consultar AppointmentController Class)
 * @NotBlank, @NotNull, @NotEmpty, @Size, @Min y @Max, @Pattern, @Email, @Positive y @PositiveOrZero, @Negative y @NegativeOrZero, @Future y @Past
 * 
 * La diferencia de NotNull y NotBlank, es que el primero te dice que el campo no puede estar vacio
 * NotBlank adicional a eso, tampoco permite que sea relleno con espacios. Adicional a eso, NotNull se usa para instancias.
 * 
 */
public record DTOAppointment(
		
		@NotBlank(message="Seleccionar un medico es obligatorio")
		DTOMedico medico,
		@NotBlank
		String fecha,
		@NotBlank
		String paciente,
		@NotNull
		DTOConsultorio consultorio
		) {

}

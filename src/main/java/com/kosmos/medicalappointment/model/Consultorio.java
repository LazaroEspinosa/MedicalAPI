package com.kosmos.medicalappointment.model;

import com.kosmos.medicalappointment.dto.DTOConsultorio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Model
 * La clase presente es generada a partir del tipo de Request Body que recibe DTOAppointment.
 * En el JSON recibido, la clave Consultorio contiene otras claves con valores. Su correcto mapeo conlleva a crear DTOs derivados como DTOConsultorio.
 * A su vez, la clase Entity, necesita clases model derivadas por sus atributos de instancias. Por eso se crea el constructor que recibe el DTOConsultorio.
 * El constructor facilita que los valores recibidos del JSON sean correctamente asignados a las clases model.
 * 
 * Pero la clase Entity solicita que el valor asignado de sus atributos sean todos String,
 * por lo que es necesario y valido crear metodos que gestionen de forma superficial la informacion que portan.
 * consultorioNumber() es un metodo que retorna una variable de tipo String, cumpliendo con lo establecido por la Entity.
 * El consutructor creado previamente porta con los datos compartidos por el DTO y lo siguiente es asignar a una variable un String:
 * String consultorioNumber=this.piso+"-"+this.numero; asi devolvemos una cadena con el numero y piso de consultorio.
 * Ahorramos un campo en la tabla y entregamos una informacion mas clara.
 */
//@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Consultorio{
	
	String piso;
	String numero;
	
	public Consultorio(DTOConsultorio consultorio) {
		this.piso=consultorio.piso();
		this.numero=consultorio.numero();
	}

	public String consultorioNumber() {
		String consultorioNumber=this.piso+"-"+this.numero;
		return consultorioNumber;
	}

}

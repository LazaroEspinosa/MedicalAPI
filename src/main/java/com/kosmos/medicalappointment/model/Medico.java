package com.kosmos.medicalappointment.model;

import com.kosmos.medicalappointment.dto.DTOMedico;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Model
 * La clase presente es generada a partir del tipo de Request Body que recibe DTOAppointment.
 * En el JSON recibido, la clave Consultorio contiene otras claves con valores. Su correcto mapeo conlleva a crear DTOs derivados como DTOMedico.
 * A su vez, la clase Entity, necesita clases model derivadas por sus atributos de instancias. Por eso se crea el constructor que recibe el DTOMedico.
 * El constructor facilita que los valores recibidos del JSON sean correctamente asignados a las clases model.
 * 
 * Pero la clase Entity solicita que el valor asignado de sus atributos sean todos String,
 * por lo que es necesario y valido crear metodos que gestionen de forma superficial la informacion que portan.
 * fullName() es un metodo que retorna una variable de tipo String, cumpliendo con lo establecido por la Entity.
 * El consutructor creado previamente porta con los datos compartidos por el DTO y lo siguiente es asignar a una variable un String:
 * this.nombre+" "+this.paterno+" "+this.materno; asi devolvemos una cadena con el nombre completo del medico.
 * Ahorramos n campos en la tabla y entregamos una informacion mas clara.
 * 
 * Actualizacion:
 * La regla de negocio solicita una tabla de los medicos. Para eso completamos los atributos necesarios como id y cedula.
 * Agregamos las anotaciones @Id, @GeneratedValue(strategy=GenerationType.IDENTITY)
 * Luego agregamos las anotaciones necesarias para pasarlo de un modelo a una entidad @Entity(name="Medico"), @Table(name="medicos").
 * Se sugiere usar @ManyToOne si el atributo fuera una instancia. Pero no es el caso.
 */

// @Embeddable
@Entity(name="Medico")
@Table(name="medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String telefono;
	String cedula;
	String especialidad;
	
	public Medico(DTOMedico medico) {
		this.nombre=medico.nombre();
		this.apellidoPaterno=medico.paterno();
		this.apellidoMaterno=medico.materno();
		this.telefono=medico.telefono();
		this.cedula=medico.cedula();
		this.especialidad=medico.especialidad();
	}

	public String fullName() {
		String fullName=
				this.nombre+" "+this.apellidoPaterno+" "+this.apellidoMaterno;		
		return fullName;
	}

}

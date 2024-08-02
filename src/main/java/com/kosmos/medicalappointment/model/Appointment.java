package com.kosmos.medicalappointment.model;

/*
 * Descripcion: Model, Entity
 * 
 * En el paquete model, se desarrollan todas las clases que tendran una interaccion directa con la DB.
 * Por lo tanto, es necesario que su mapeo sea semejante a las tablas en las que se almacenara.
 * 
 * @Entity: Una clase marcada con esta anotacion indica que las instancias creadas con esta clase seran almacenados en las bases de datos.
 * A diferencia del DTO, los model tienen atributos y no solo parametros.
 * LOS ATRIBUTOS DEBEN DE SER EQUIVALENTES A LOS CAMPOS EN LAS TABLAS:
 * Si declaramos que todos nuestros atributos son de tipo String(u otro tipo simple), entonces la tabla debe tener el mismo numero de campos.
 * Si declaramos que los atributos son instancias de otras clases, entonces los campos en la tabla deben de ser iguales a cada atributo simple de los atributos compuestos.
 * En cristiano significa:
 * Si declaramos un atributo tipo Medico y esta clase tiene atributos String de nombre, apellido, especialidad, etc. 
 * entonces la tabla en lugar de usar un campo para medico, usara un campo para nombre, otro para apellido y asi sucesivamente.
 * Adicional a eso, los atributos de instancias, deben llevar la anotacion @Embedded y la respectiva clase de la instancia @Embeddable.
 * 
 * Dicho esto entendemos lo siguiente:
 * La tabla appointments guardara en sus registros instancias de tipo Appointment. Y sus campos seran los atributos.
 * Como queremos que los campos sean |id|medico|paciente|fecha|consultorio| debemos lograr que los atributos de instancias devuelvan un string.
 * De lo contrario, los campos deberan ser mas.
 * 
 * Para lograr que el valor devuelto sea un String se usa un constructor que recibira como parametro el DTOAppointment.
 * Para los atributos simples (String en este caso) basta usar las lineas this.paciente=dtoAppointment.paciente();
 * dtoAppointment.paciente() devolvera un string con el nombre del paciente, pues asi lo tiene establecido el DTO.
 * Por otro lado, los atributos compuestos deberan usar this.consultorio=new Consultorio(dtoAppointment.consultorio()).consultorioNumber();
 * new Consultorio() hace una instancia de la clase Consultorio con los atributos que contiene.
 * El parametro que acepta es dtoAppointment.consultorio() para acceder a los valores que guarda el DTOAppointment para la clave consultorio.
 * A ese punto, la variable devuelta es un objeto tipo Consultorio. Para que sea un String usamos .consultorioNumber(),
 * Un metodo dentro de la clase Consultorio que devuelve un String.
 * 
 * Ir a la clase consultorio o medico para conocer mas detalles.
 * 
 * Establecida la clase que recibira la tabla, es necesario crear el repositorio, que sera el puente entre la entidad y la DB.
 * En las mismas notas se explicara como funciona flyway.
 * 
 * Inicialmente y para fines de testing, el atributo de fecha del appointment se entregaba en String. para cambiarlo a un formato de fecha hay distintas formas.
 * La mas facil es hacer una conversion en la entidad. Para eso es necesario primero cambiar el tipo de dato de String fecha a LocalDateTime fecha.
 * Ahroa declaramos 
 * private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
 * guardando en formatter una configuracion o formato especifico de tiempo.
 * se está utilizando una instancia predefinida de DateTimeFormatter que ya está creada y configurada en la clase DateTimeFormatter.
 * No se está creando una nueva instancia con new. Con static se indica a la entidad que es un atributo propio de la clase y sin interaccion con la DB.
 * Luego, dentro del CONSTRUCTOR declaramos
 * LocalDateTime localdatetime=LocalDateTime.parse(dtoAppointment.fecha(), formatter);
 * Toma la fecha del DTO y la convierte a tipo LocalDateTime con el formato previamente configurado usando formatter.
 * Es valido usarlo aqui y no en la capa de Service cuando son conversiones de datos simples. Por ultimo
 * this.fecha=localdatetime;
 * Para asignar el nuevo valor con el tipo de dato requerido por la entidad que se ha cambiado previamente en el atributo LocalDateTime.
 * Lo siguiente es actualizar la tabla usando una nueva migracion y declarar un cambio de tipo de dato:
 * ALTER TABLE appointments MODIFY COLUMN fecha DATETIME NOT NULL;
 * Cambiando de la tabla appointment el tipo de dato de fecha a datetime.
 * 
 * 
 * SpringBoot Data JPA
 * @Entity Indica a Springboot una entidad, por lo tanto, una instancia de este tipo es un nuevo registro en una tabla.
 * @Table indica cual es el nombre de la tabla, en caso de que esto no se especifique, Springboot tomara el nombre de @Entity.
 * @Id definie el identificador unico de cada entity.
 * 
 * Lombok:
 * @NoArgsConstructor Creacion de constructor sin argumentos.
 * @AllArgsConstructor Creacion de constructor con todos los argumentos.
 * @EqualsAndHashCode(of="id") Combinacion de @Equals y @HashCode y sirve para comparar y entregar registros no duplicados.
 * 
 * Pendientes:
 * Modificadores de acceso
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import lombok.AllArgsConstructor;

/*
 * Errores contados (1) Errores solucionados(1):
 * 
 * Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled. y un resto de 
 * El error fue ocasionado por declarar:
 * private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
 * El problema ocurre porque DateTimeFormatter no es un tipo que Hibernate pueda mapear directamente a una columna de la base de datos.
 * Además, DateTimeFormatter no debería ser un campo persistente en tu entidad.
 * Al no estar dentro de un metodo, Springboot lo detecta como un atributo de la entidad y a su vez como un campo en la tabla pero al no ser mapeable, surge el error.
 * La solucion es declarar al atributo como estatico, dando a entender que se limita a su uso en la clase.
 * private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
 */

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
	//@Embedded
	String medico;
	LocalDateTime fecha;
	String paciente;
	//@Embedded	
	String consultorio;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;	
	
	public Appointment(DTOAppointment dtoAppointment) {
		
		LocalDateTime localdatetime=LocalDateTime.parse(dtoAppointment.fecha(), formatter);
		
		this.consultorio=new Consultorio(dtoAppointment.consultorio()).consultorioNumber();
		this.medico=new Medico(dtoAppointment.medico()).fullName();
		this.fecha=localdatetime;
		this.paciente=dtoAppointment.paciente();
	}
}

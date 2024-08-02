package com.kosmos.medicalappointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.medicalappointment.model.Appointment;


/*
 * Repository
 * Los repositorios son interface que extienden de JPARepository.
 * Su funcion es tomar entidades y realizar la gestion con la DB. 
 * Al extender de JPARepository, se puede acceder a una basta cantidad de metodos para alterar una base de datos.
 * Guardar, eliminar, buscar, entre otros.
 * No necesita anotaciones. la interface solicitara una entidad y procedera a realizar la transaccion en la base de datos.
 * Para eso se necesitan considerar 2 modificaciones adicionales aplication.properties y migrations.
 * 
 * En aplication.properties:
 * Establecemos el nombre del proyecto o la aplicacion.
 * spring.application.name=medicalappointment
 * Establecemos el nombre de la base de datos que crearemos para almacenar ahi los registros, es decir, los objetos de las clases Entity.
 * La DB no puede ser creada con flyway (No que yo sepa), crearla de forma manual.
 * spring.datasource.url=jdbc:mysql://localhost/kosmosmedicoapp
 * Declaramos el usuario de la DB y su password.
 * spring.datasource.username=root
 * spring.datasource.password=12345
 * 
 * Migration (FlyWay):
 * Las migration son instrucciones o QUERIES que hacemos a nuestra base de datos desde nuesto codigo usando archivos .sql.
 * La ventaja es que no tenemos que abrir nuestra base de datos para crear o alterar tablas. Usando una DB simple puede que no tenga sentido
 * Pero si hablamos de proyectos grandes, acceder a una DB puede ser complicado. FlyWay ayuda con esa parte.
 * Las migraciones se almacenan en src/main/resources/db/migration
 * El nombre del archivo es importante para que FlyWay pueda ejecutar correctamente la solicitud o no ejecutarla para nada.
 * 
 * V4__alterTableAppointment_changeFechadeConsultaToVarchar.sql
 * V1__, el numero varia a la version consecutiva siguiente de la ya creada.
 * descripcion_basica para llevar un control u orden en cada migracion
 * .sql necesario para que FlyWay pueda ejecutar la orden.
 * 
 * En su interior, llevara una Query como si del Workbench se tratara.
 * Al inicializar el servidor, flyway tratara de ejecutar la query.
 * 
 * Que pasa si tengo un error? Lo mas rapido es abrir la base de datos y borrar el registro del intento fallido para modificar el archivo y reintentar.
 * Asi el versionado seguiria en orden.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}

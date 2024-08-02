package com.kosmos.medicalappointment.dto;

import com.kosmos.medicalappointment.model.Medico;
/*
 * DTOListaMedicos
 * Por regla de negocio, se debe tener un metodo que pueda mostrar los medicos disponibles.
 * Si usamos el DTOMedico, mostraremos informacion innecesaria, por eso se crea DTOMedicoListado.
 * Se crea un constructor que recibira un modelo y que en esta ocasion igual es una entidad de tipo Medico.
 * Y este asignara sus valores guardados al DTO.
 * Lo siguiente es usar un metodo en MedicoService que ayude a aplicar la regla del negocio.
 */

/*
 * Errores(1)Soluciones(1):
 * Al momento de crear el constructor, su procedimiento se declaro de la siguiente forma:
 * this.nombreCompleto=medico.fullName();
 * this.telefono=medico.getTelefono();
 * this.especialidad=medico.getEspecialidad();
 * Dando el error: A non-canonical constructor must start with an explicit invocation to a constructor.
 * Para solucionarlo, se usa el metodo this():
 * this(medico.fullName(),medico.getTelefono(),medico.getEspecialidad());
 */

public record DTOMedicoListado(
		String nombreCompleto,
		String telefono,
		String especialidad)
{
	public DTOMedicoListado(Medico medico) {
		this(medico.fullName(),medico.getTelefono(),medico.getEspecialidad());
	}
}
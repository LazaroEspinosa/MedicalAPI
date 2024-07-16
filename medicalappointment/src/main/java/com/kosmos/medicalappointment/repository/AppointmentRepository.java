package com.kosmos.medicalappointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.medicalappointment.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}

package com.hphis.service;

import java.util.List;

import com.hphis.pojo.Paciente;

public interface PacienteService {

	public List<Paciente> getAllPacientes();


	public void addPaciente(Paciente paciente);


	public void editPaciente(Paciente paciente);


	public void deletePaciente(Integer nhc);

	public List<Paciente> getPacientesConFiltro(Paciente paciente);

}

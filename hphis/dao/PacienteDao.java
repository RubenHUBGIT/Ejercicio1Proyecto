package com.hphis.dao;

import com.hphis.pojo.Paciente;

import java.util.List;

public interface PacienteDao {

	public List<Paciente> getPacientes();

	public void savePaciente(Paciente paciente);

	public void updatePaciente(Paciente paciente);

	public void deletePaciente(Paciente deletePaciente);

	public List<Paciente> getPacientesFiltrado(Paciente paciente);

}

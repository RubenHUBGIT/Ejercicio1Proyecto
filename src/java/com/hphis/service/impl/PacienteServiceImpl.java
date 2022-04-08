package com.hphis.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.dao.PacienteDao;
import com.hphis.pojo.Paciente;
import com.hphis.service.PacienteService;

public class PacienteServiceImpl implements PacienteService {

    private PacienteDao pacienteDao;
    
    public PacienteDao getPacienteDao() {
		return pacienteDao;
	}

	public void setPacienteDao(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	@Override
    public List<Paciente> getAllPacientes() {

        List<Paciente> pacientes;
        try {
            pacientes = pacienteDao.getPacientes();
        }
        catch (Exception e) {
            throw e;
        }

        return pacientes;
    }

}

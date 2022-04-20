package com.hphis.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.dao.PacienteDao;
import com.hphis.pojo.CheckPojo;
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
	
	@Override
	public void addPaciente(Paciente paciente) {		
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        
        boolean faltanDatos = false;
        
        try {        	
        	
        	if(paciente.getNombre().isEmpty()) {
        		faltanDatos=true;
        	}
        	if(paciente.getApellidos().isEmpty() && !faltanDatos) {
        		faltanDatos=true;
        	}
        	if(paciente.getCentro().isEmpty() && !faltanDatos) {
        		faltanDatos=true;
        	}
        	if(paciente.getUbicacion().isEmpty() && !faltanDatos) {
        		faltanDatos=true;
        	}
        	
        	if(!faltanDatos) {
        		pacienteDao.savePaciente(paciente);

                session.getTransaction().commit();
        	}else{
        		session.getTransaction().rollback();
        	}
        	
            
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
	
	@Override
	public void editPaciente(Paciente paciente) {		
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        try {
        	Paciente editedPaciente = (Paciente) session.get(Paciente.class, paciente.getNhc());
        	if(!paciente.getNombre().isEmpty()) {
        		editedPaciente.setNombre(paciente.getNombre());
        	}
        	if(!paciente.getApellidos().isEmpty()) {
        		editedPaciente.setApellidos(paciente.getApellidos());
        	}
        	if(!paciente.getCentro().isEmpty()) {
        		editedPaciente.setCentro(paciente.getCentro());
        	}
        	if(!paciente.getUbicacion().isEmpty()) {
        		editedPaciente.setUbicacion(paciente.getUbicacion());
        	}
        	
            pacienteDao.updatePaciente(editedPaciente);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
	
	@Override
	public void deletePaciente(Integer nhc) {		
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        try {
        	Paciente deletePaciente = (Paciente) session.get(Paciente.class, nhc);
        	
        	
            pacienteDao.deletePaciente(deletePaciente);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

	@Override
	public List<Paciente> getPacientesConFiltro(Paciente paciente) {

		List<Paciente> pacientes;
		try {		  
			pacientes = pacienteDao.getPacientesFiltrado(paciente);

		} catch (Exception e) {
			throw e;
		}

		return pacientes;
	}
}

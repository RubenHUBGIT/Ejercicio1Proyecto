package com.hphis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.dao.PacienteDao;
import com.hphis.pojo.Paciente;

public class PacienteDaoImpl implements PacienteDao {

  
    @Override
    public List<Paciente> getPacientes() {
        Session session = HibernateUtil.currentSession();
        Criteria criteria = session.createCriteria(Paciente.class);
        return criteria.addOrder( Order.asc("nhc")).list();
    }
    
    @Override
    public void savePaciente(Paciente paciente) {
        Session session = HibernateUtil.currentSession();
        session.save(paciente);
    }
    
    @Override
    public void updatePaciente(Paciente paciente) {
        Session session = HibernateUtil.currentSession();
        session.update(paciente);
    }
    
    @Override
    public void deletePaciente(Paciente paciente) {
        Session session = HibernateUtil.currentSession();
        session.delete(paciente);
    }
    
    @Override
    public List<Paciente> getPacientesFiltrado(Paciente paciente) {
    	List<Paciente> pacientes;
    	Session session = HibernateUtil.currentSession();
    	String queryStr = generarQuery(paciente);
    	
    	Query query = HibernateUtil.currentSession().createQuery(queryStr);
    	pacientes = (List<Paciente>) query.list();
		  
	    return pacientes;
    }
    
    private String generarQuery(Paciente paciente) {
    	String query = "FROM Paciente WHERE ";
    	
    	boolean masCondiciones = false;
    	
    	if(!paciente.getNombre().isEmpty()) {
    		query += "nombre = '" + paciente.getNombre() + "'";
    		masCondiciones = true;
    	}
    	if(!paciente.getApellidos().isEmpty()) {
    		if(masCondiciones) {
    			query += " AND ";
    		}
    		query += "apellidos = '" + paciente.getApellidos() + "'";
    		masCondiciones = true;
    	}
    	if(!paciente.getCentro().isEmpty()) {
    		if(masCondiciones) {
    			query += " AND ";
    		}
    		query += "centro = '" + paciente.getCentro() + "'";
    		masCondiciones = true;
    	}
    	if(!paciente.getUbicacion().isEmpty()) {
    		if(masCondiciones) {
    			query += " AND ";
    		}
    		query += "ubicacion = '" + paciente.getUbicacion() + "'";
    		masCondiciones = true;
    	}
    	query += " ORDER BY nhc";
    	return query;
    }

}

package com.hphis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.dao.CentroDao;
import com.hphis.pojo.Centro;
import com.hphis.pojo.Paciente;

public class CentroDaoImpl implements CentroDao {
	
    @Override
    public List<Centro> getCentros() {
        Session session = HibernateUtil.currentSession();
        Criteria criteria = session.createCriteria(Centro.class);
        return criteria.addOrder( Order.asc("codCentro")).list();
    }	
    
    @Override
    public void saveCentro(Centro centro) {
        Session session = HibernateUtil.currentSession();
        session.save(centro);
    }
    
    @Override
    public void updateCentro(Centro centro) {
        Session session = HibernateUtil.currentSession();
        session.update(centro);
    }
    
    @Override
    public void deleteCentro(Centro centro) {
        Session session = HibernateUtil.currentSession();
        session.delete(centro);
    }
    
    @Override
    public List<Centro> getCentrosFiltrado(Centro centro){
    	List<Centro> centros;
    	Session session = HibernateUtil.currentSession();
    	String queryStr = generarQuery(centro);
    	
    	Query query = HibernateUtil.currentSession().createQuery(queryStr);
    	centros = (List<Centro>) query.list();
		  
	    return centros;
    }
    
    private String generarQuery(Centro centro) {
    	String query = "FROM Centro WHERE ";
    	
    	boolean masCondiciones = false;
    	
    	if(centro.getCodCentro() != null) {
    		query += "cod_centro = '" + centro.getCodCentro() + "'";
    		masCondiciones = true;
    	}
    	if(!centro.getCentro().isEmpty()) {
    		if(masCondiciones) {
    			query += " AND ";
    		}
    		query += "nombre_centro = '" + centro.getCentro() + "'";
    		masCondiciones = true;
    	}    	
    	query += " ORDER BY cod_centro";
    	return query;
    }
}

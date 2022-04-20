package com.hphis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.dao.UbicacionDao;
import com.hphis.pojo.Centro;
import com.hphis.pojo.Ubicacion;

public class UbicacionDaoImpl implements UbicacionDao {
	
	@Override
    public List<Ubicacion> getUbicaciones() {
        Session session = HibernateUtil.currentSession();
        Criteria criteria = session.createCriteria(Ubicacion.class);
        return criteria.addOrder( Order.asc("codUbicacion")).list();
    }

	@Override
    public void saveUbicacion(Ubicacion ubicacion) {
        Session session = HibernateUtil.currentSession();
        session.save(ubicacion);
    }
    
    @Override
    public void updateUbicacion(Ubicacion ubicacion) {
        Session session = HibernateUtil.currentSession();
        session.update(ubicacion);
    }
    @Override
    public void deleteUbicacion(Ubicacion ubicacion) {
        Session session = HibernateUtil.currentSession();
        session.delete(ubicacion);
    }
    
    @Override
	public List<Ubicacion> getUbicacionesFiltrado(Ubicacion ubicacion) {
		List<Ubicacion> ubicaciones;
		Session session = HibernateUtil.currentSession();
		String queryStr = generarQuery(ubicacion);
		
		Query query = HibernateUtil.currentSession().createQuery(queryStr);
		ubicaciones = (List<Ubicacion>) query.list();
		
		return ubicaciones;
		
	}

	private String generarQuery(Ubicacion ubicacion) {
		String query = "FROM Ubicacion WHERE ";
		
		boolean masCondiciones = false;
		
		if(ubicacion.getCodUbicacion() != null) {
    		query += "cod_ubicacion = '" + ubicacion.getCodUbicacion() + "'";
    		masCondiciones = true;
    	}
		if(!ubicacion.getUbicacion().isEmpty()) {
			if(masCondiciones) {
    			query += " AND ";
    		}
			query += "ubicacion = '" + ubicacion.getUbicacion() + "'";
			masCondiciones = true;
		}
		query += " ORDER BY cod_ubicacion";		
		return query;
	}
    
}

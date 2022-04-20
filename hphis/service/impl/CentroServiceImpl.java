package com.hphis.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.dao.CentroDao;
import com.hphis.pojo.CheckPojo;
import com.hphis.pojo.Paciente;
import com.hphis.pojo.Centro;
import com.hphis.service.CentroService;

public class CentroServiceImpl implements CentroService{

private CentroDao centroDao;
    
    public CentroDao getCentroDao() {
		return centroDao;
	}

	public void setCentroDao(CentroDao centroDao) {
		this.centroDao = centroDao;
	}

	@Override
	public List<Centro> getAllCentros() {

        List<Centro> centros;
        try {
            centros = centroDao.getCentros();
        }
        catch (Exception e) {
            throw e;
        }

        return centros;
    }
	
	@Override
	public void addCentro(Centro centro) {
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		try {
			centroDao.saveCentro(centro);

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	@Override
	public void deleteCentro(Integer codCentro) {		
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        try {
        	Centro deleteCentro = (Centro) session.get(Centro.class, codCentro);
        	
        	
            centroDao.deleteCentro(deleteCentro);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
	
	@Override
	public void editCentro(Centro centro) {		
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        try {
        	Centro editedCentro = (Centro) session.get(Centro.class, centro.getCodCentro());
        	if(!centro.getCentro().isEmpty()) {
        		editedCentro.setCentro(centro.getCentro());
        	}       	        	
            centroDao.updateCentro(editedCentro);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
	
	public List<Centro> getCentrosConFiltro(Centro centro){
		List<Centro> centros;
		try {		  
			centros = centroDao.getCentrosFiltrado(centro);

		} catch (Exception e) {
			throw e;
		}

		return centros;
	}
	
}

package com.hphis.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.dao.UbicacionDao;
import com.hphis.pojo.Ubicacion;
import com.hphis.service.UbicacionService;

public class UbicacionServiceImpl implements UbicacionService{

private UbicacionDao ubicacionDao;
    
    public UbicacionDao getUbicacionDao() {
		return ubicacionDao;
	}

	public void setUbicacionDao(UbicacionDao ubicacionDao) {
		this.ubicacionDao = ubicacionDao;
	}

	@Override
    public List<Ubicacion> getAllUbicaciones() {

        List<Ubicacion> ubicaciones;
        try {
            ubicaciones = ubicacionDao.getUbicaciones();
        }
        catch (Exception e) {
            throw e;
        }

        return ubicaciones;
    }
	
	@Override
	public void addUbicacion(Ubicacion ubicacion) {
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		try {
			ubicacionDao.saveUbicacion(ubicacion);

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	@Override
	public void editUbicacion(Ubicacion ubicacion) {		
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        try {
        	Ubicacion editedUbicacion = (Ubicacion) session.get(Ubicacion.class, ubicacion.getCodUbicacion());
        	if(!ubicacion.getUbicacion().isEmpty()) {
        		editedUbicacion.setUbicacion(ubicacion.getUbicacion());
        	}       	        	
            ubicacionDao.updateUbicacion(editedUbicacion);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
	
	@Override
	public void deleteUbicacion(Integer codUbicacion) {		
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        try {
        	Ubicacion deleteUbicacion = (Ubicacion) session.get(Ubicacion.class, codUbicacion);
        	
        	
        	ubicacionDao.deleteUbicacion(deleteUbicacion);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
	
	@Override
    public List<Ubicacion> getUbicacionesConFiltro(Ubicacion ubicacion) {
        List<Ubicacion> ubicaciones;
        try {
        	ubicaciones = ubicacionDao.getUbicacionesFiltrado(ubicacion);
        }
        catch (Exception e) {
            throw e;
        }

        return ubicaciones;
    }
	
}

package com.hphis.dao;

import com.hphis.pojo.Ubicacion;

import java.util.List;

public interface UbicacionDao {
	
	public List<Ubicacion> getUbicaciones();
	
	public List<Ubicacion> getUbicacionesFiltrado(Ubicacion ubicacion);

	public void saveUbicacion(Ubicacion ubicacion);
	
	public void updateUbicacion(Ubicacion ubicacion);

	public void deleteUbicacion(Ubicacion deleteUbicacion);
}

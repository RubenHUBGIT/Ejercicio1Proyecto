package com.hphis.service;

import java.util.List;

import com.hphis.pojo.Ubicacion;

public interface UbicacionService {
	
	public List<Ubicacion> getAllUbicaciones();
	
	public List<Ubicacion> getUbicacionesConFiltro(Ubicacion ubicacion);

	public void addUbicacion(Ubicacion ubicacion);
	
	public void editUbicacion(Ubicacion ubicacion);

	public void deleteUbicacion(Integer codUbicacion);
	
}

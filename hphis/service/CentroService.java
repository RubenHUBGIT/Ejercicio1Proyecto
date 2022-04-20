package com.hphis.service;

import java.util.List;

import com.hphis.pojo.Centro;

public interface CentroService {

	public List<Centro> getAllCentros();	
	
	public void addCentro(Centro centro);
	public void editCentro(Centro centro);

	public void deleteCentro(Integer codCentro);

	public List<Centro> getCentrosConFiltro(Centro centro);
}

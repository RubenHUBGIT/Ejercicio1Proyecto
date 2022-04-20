package com.hphis.dao;

import com.hphis.pojo.Centro;

import java.util.List;

public interface CentroDao {

	public List<Centro> getCentros();
	
	public void saveCentro(Centro centro);
	public void updateCentro(Centro centro);

	public void deleteCentro(Centro deleteCentro);

	public List<Centro> getCentrosFiltrado(Centro centro);
}

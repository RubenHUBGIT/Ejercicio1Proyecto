package com.hphis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hphis.pojo.Ubicacion;
import com.hphis.service.UbicacionService;

public class UbicacionController {

	private UbicacionService ubicacionService;

	public void setUbicacionService(UbicacionService ubicacionService) {
		this.ubicacionService = ubicacionService;
	}

	public ModelAndView getUbicaciones(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Ubicacion> ubicaciones = ubicacionService.getAllUbicaciones();
		Map params = new HashMap<>();
		params.put("ubicacionList", ubicaciones);
		return new ModelAndView("jsonView", params);
	}
	
	public ModelAndView addUbicacion(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Ubicacion ubicacion = new Ubicacion();

		ubicacion.setUbicacion(request.getParameter("ubicacion"));
		ubicacionService.addUbicacion(ubicacion);
		
		return new ModelAndView("jsonView");
	}

	public ModelAndView editUbicacion(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Ubicacion ubicacion = new Ubicacion();

		Integer cod_ubicacion = Integer.valueOf(request.getParameter("cod_ubicacion"));

		ubicacion.setCodUbicacion(cod_ubicacion);
		ubicacion.setUbicacion(request.getParameter("ubicacion"));

		ubicacionService.editUbicacion(ubicacion);
		
		return new ModelAndView("jsonView");
	}

	public ModelAndView deleteUbicacion(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer codUbicacion = Integer.valueOf(request.getParameter("codUbicacion"));
		ubicacionService.deleteUbicacion(codUbicacion);

		return new ModelAndView("jsonView");
	}

	public ModelAndView getUbicacionesFiltrado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Ubicacion ubicacion = new Ubicacion();
		Integer cod_ubicacion=null;
		if(!request.getParameter("codUbicacion").isEmpty()) {
			cod_ubicacion = Integer.valueOf(request.getParameter("codUbicacion"));
		}
		ubicacion.setCodUbicacion(cod_ubicacion);
		ubicacion.setUbicacion(request.getParameter("ubicacion"));
		
		List<Ubicacion> ubicaciones = ubicacionService.getUbicacionesConFiltro(ubicacion);

		Map params = new HashMap<>();
		params.put("ubicacionList", ubicaciones);
		return new ModelAndView("jsonView", params);
	}
	
}
package com.hphis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hphis.pojo.Centro;
import com.hphis.pojo.Paciente;
import com.hphis.service.CentroService;

public class CentroController {

	private CentroService centroService;

	public void setCentroService(CentroService centroService) {
		this.centroService = centroService;
	}

	public ModelAndView getCentros(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Centro> centros = centroService.getAllCentros();
		Map params = new HashMap<>();
		params.put("centroList", centros);
		return new ModelAndView("jsonView", params);
	}

	public ModelAndView addCentro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Centro centro = new Centro();

		centro.setCentro(request.getParameter("centro"));
		centroService.addCentro(centro);
		
		return new ModelAndView("jsonView");
	}

	public ModelAndView editCentro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Centro centro = new Centro();

		Integer cod_centro = Integer.valueOf(request.getParameter("cod_centro"));

		centro.setCodCentro(cod_centro);
		centro.setCentro(request.getParameter("centro"));

		centroService.editCentro(centro);
		
		return new ModelAndView("jsonView");
	}
	public ModelAndView deleteCentro (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
    	Integer codCentro = Integer.valueOf(request.getParameter("codCentro"));	
    	centroService.deleteCentro(codCentro);    	
    	
    	return new ModelAndView("jsonView");
	}
	
	public ModelAndView getCentrosFiltrado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Centro centro = new Centro();
		Integer cod_centro=null;
		if(!request.getParameter("codCentro").isEmpty()) {
			cod_centro = Integer.valueOf(request.getParameter("codCentro"));
		}
		centro.setCodCentro(cod_centro);
		centro.setCentro(request.getParameter("centro"));
    	
    	List<Centro> centros = centroService.getCentrosConFiltro(centro);

		Map params = new HashMap<>();
		params.put("centroList", centros);
		return new ModelAndView("jsonView", params);
	}
}
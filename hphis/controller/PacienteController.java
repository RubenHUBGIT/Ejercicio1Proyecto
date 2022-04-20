package com.hphis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hphis.pojo.Paciente;
import com.hphis.service.PacienteService;

public class PacienteController {
	
	private PacienteService pacienteService;
	
  public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

    public ModelAndView getPacientes (HttpServletRequest request, HttpServletResponse response) throws Exception {
    	List<Paciente> pacientes = pacienteService.getAllPacientes();
    	Map params = new HashMap<>();
        params.put("pacienteList",pacientes);
        return new ModelAndView("jsonView", params);
    }
    
    public ModelAndView addPaciente (HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Paciente paciente = new Paciente();
    	
    	paciente.setNombre(request.getParameter("nombre"));
    	paciente.setApellidos(request.getParameter("apellidos"));
    	paciente.setCentro(request.getParameter("centro"));
    	paciente.setUbicacion(request.getParameter("ubicacion"));
    	
    	pacienteService.addPaciente(paciente);    	
    	
    	return new ModelAndView("jsonView");
    }
    public ModelAndView editPaciente (HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Paciente paciente = new Paciente();
    	
    	Integer nhc = Integer.valueOf(request.getParameter("nhc"));
    	
    	paciente.setNhc(nhc);
    	paciente.setNombre(request.getParameter("nombre"));
    	paciente.setApellidos(request.getParameter("apellidos"));
    	paciente.setCentro(request.getParameter("centro"));
    	paciente.setUbicacion(request.getParameter("ubicacion"));
    	
    	
    	pacienteService.editPaciente(paciente);  
    	
    	return new ModelAndView("jsonView");
    }
	public ModelAndView deletePaciente (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    	Integer nhc = Integer.valueOf(request.getParameter("nhc"));	
	    	pacienteService.deletePaciente(nhc);
	    	
	    	return new ModelAndView("jsonView");	    	
	}
	
	public ModelAndView getPacientesFiltrado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Paciente paciente = new Paciente();
		
		paciente.setNombre(request.getParameter("nombre"));
    	paciente.setApellidos(request.getParameter("apellidos"));
    	paciente.setCentro(request.getParameter("centro"));
    	paciente.setUbicacion(request.getParameter("ubicacion"));
    	
    	List<Paciente> pacientes = pacienteService.getPacientesConFiltro(paciente);

		Map params = new HashMap<>();
		params.put("pacienteList", pacientes);
		return new ModelAndView("jsonView", params);
	}
}
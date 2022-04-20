package com.hphis.controller;

import com.hphis.pojo.Paciente;
import com.hphis.service.CheckService;
import com.hphis.service.PacienteService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralController extends MultiActionController {

    private CheckService checkService;

    public void setCheckService(CheckService checkService) {
        this.checkService = checkService;
    }

	public ModelAndView agendaUrgencias(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String everythingIsOK = checkService.isEverythingOK();
        if (everythingIsOK.equals("yes!")) {
            return new ModelAndView("agendaUrgenciasView");
        }
        else {
            throw new Exception("Error found!");
        }
    }
}
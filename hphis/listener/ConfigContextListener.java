package com.hphis.listener;

import com.hphis.corp.Configuracion;
import com.hphis.corp.Excepciones.PropiedadNoEncontradaException;
import com.hphis.corp.FuncionesGenericas;
import com.hphis.corp.RecDatos;
import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.corp.infraestructura.ParametrosConfigXMLDatabase;
import com.hphis.corp.sql.api.impl.oracle.OracleDB;
import com.hphis.corp.sql.api.provider.DatabaseApiProvider;
import com.hphis.xml.ParserPropiedadesXML;
import org.hibernate.Session;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.net.URL;

public class ConfigContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();

        String basePath = context.getRealPath("");

        if(basePath==null){

            Class clazz = getClass();
            URL url = clazz.getResource("");
            basePath = url.getFile();

            int posWebInf = basePath.indexOf("/WEB-INF");

            basePath = basePath.substring(0,posWebInf);

            //System.out.println("file: " + basePath);
        }

        String hphisdir = basePath + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "com" + File.separator + "hphis" + File.separator;
        FuncionesGenericas.setHPHISDIR(hphisdir);

        String hphisdirweb = basePath + File.separator;
        FuncionesGenericas.setHPHISDIRWEB(hphisdirweb);

        /*try {
           String motor = Configuracion.getParamStatic(ParametrosConfigXMLDatabase.MAESTROS_MOTOR);
        } catch (PropiedadNoEncontradaException e) {
            e.printStackTrace();
        }*/
        RecDatos.setDatabaseEngine(new OracleDB());
        DatabaseApiProvider.setApiBuilder(new OracleDB());
        HibernateUtil.makeSessionFactory("ORACLE");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

package com.hphis.filter;

import com.hphis.corp.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.context.internal.ManagedSessionContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HibernateSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Session hibernateSession = HibernateUtil.createSession();

        try {
            ManagedSessionContext.bind(hibernateSession);

            try {
                filterChain.doFilter(servletRequest, servletResponse);

            } catch (IOException e) {
                throw e;

            } catch (ServletException e) {
                throw e;
            }

            hibernateSession = ManagedSessionContext.unbind(HibernateUtil.getSessionFactory());
        }
        finally{

            // cierre de la sesion
            try{

                boolean isTxRollback = HibernateUtil.closeSession(((HttpServletRequest)servletRequest).getRequestURL().toString());
                if (isTxRollback) {
                    System.out.println(("[OPEN TRANSACTION] Se ha cerrado automáticamente por el sistema una transacción abierta: "
                            + "[url: " + ((HttpServletRequest)servletRequest).getRequestURI().toString() + "]"));
                }
            }
            catch(Exception e){
                System.out.println("Excepción en el close session de hibernate");
            }
        }

    }

    @Override
    public void destroy() {

    }
}

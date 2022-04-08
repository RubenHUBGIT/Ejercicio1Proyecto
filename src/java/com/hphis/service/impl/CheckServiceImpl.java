package com.hphis.service.impl;

import com.hphis.dao.CheckDao;
import org.hibernate.Session;

import com.hphis.corp.hibernate.util.HibernateUtil;
import com.hphis.pojo.CheckPojo;
import com.hphis.service.CheckService;

public class CheckServiceImpl implements CheckService {

    private CheckDao checkDao;

    public CheckDao getCheckDao() {
        return checkDao;
    }

    public void setCheckDao(CheckDao checkDao) {
        this.checkDao = checkDao;
    }

    @Override
    public String isEverythingOK() {

        String phrase = null;
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        try {
            CheckPojo pojo = checkDao.getPojo(1);
            pojo.setPhrase("yes!");
            checkDao.save(pojo);
            phrase = checkDao.getPhrase(1);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return phrase;
    }
    
}

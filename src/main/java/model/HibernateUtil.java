/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.media.jfxmedia.logging.Logger;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Tommi
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Exception e) {
            Logger.logMsg(4, e.getMessage());
        }
    }

    /**
     * Palauttaa Sessionfactoryn.
     *
     * @return sessionfactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
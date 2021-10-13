package com.example.application.model;

import controllers.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class ClientDAO {

    public Clients findById(UUID CLIENTID) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Clients.class, CLIENTID);
    }

    public void save(Clients clients) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(clients);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Clients clients) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(clients);
        tx1.commit();
        session.close();
    }

    public void delete(Clients clients) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(clients);
        tx1.commit();
        session.close();
    }

    public PaymentSchedule findPaymentScheduleById(UUID paymentId) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(PaymentSchedule.class, paymentId);
    }

    public void findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From Clients");

        List<Clients> resultList = q.list();
        for (Clients next : resultList) {
            System.out.println("next Clients: " + next);
        }
    }


    
}

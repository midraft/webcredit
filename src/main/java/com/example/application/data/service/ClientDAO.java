package com.example.application.data.service;

import controllers.HibernateSessionFactoryUtil;
import model.PaymentSchedule;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public interface ClientDAO {

    public Client findById(UUID CLIENTID) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, CLIENTID);
    }

    public void save(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    public void update(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(client);
        tx1.commit();
        session.close();
    }

    public void delete(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(client);
        tx1.commit();
        session.close();
    }

    public PaymentSchedule findPaymentScheduleById(UUID paymentId) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, CLIENTID);
    }

    public List<Client> findAll() {
        List<Client> clients = (List<Client>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Client").list();
        return clients;
    }
}

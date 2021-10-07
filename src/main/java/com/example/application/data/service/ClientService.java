package com.example.application.data.service;

import com.example.application.model.ClientDAO;
import com.example.application.model.Clients;
import com.example.application.model.PaymentSchedule;

import java.util.UUID;

public class ClientService {

    private final ClientDAO clientDAO = new ClientDAO();

    public ClientService() {
    }
        
   
    public Clients findById(UUID CLIENTID) {
        return clientDAO.findById(CLIENTID);
    }

      
    public void save(Clients clients) {
        clientDAO.save(clients);
    }

    
    public void update(Clients clients) {
        clientDAO.update(clients);
    }

    
    public void delete(Clients clients) {
        clientDAO.delete(clients);
    }

    public  void findAll() {
        clientDAO.findAll();
    }
      
    public PaymentSchedule findPaymentScheduleById(UUID paymentId) {
        return clientDAO.findPaymentScheduleById(paymentId);
    }

    
   
    
}

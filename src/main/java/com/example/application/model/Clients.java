package com.example.application.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

/**
 * Модель данных для взаимодействия приложение с базой данных.
 */
@Entity
@Table(name = "CLIENTS")


public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID CLIENTID = UUID.randomUUID();


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<PaymentSchedule> paymentSchedule;


    private String SURNAME;
    private String NAME;
    private String PATRONYMIC;
    private String PHONE;
    private String EMAIL;
    private int PASSPORTSERIES;
    private int PASSPORTID;
    private double CREDIT_AMOUNT;
    private double CREDIT_TERM;
    private double ENTRY_INTEREST_RATE;



    
    
    
    public Clients(UUID CLIENTID, String SURNAME, String NAME, String PATRONYMIC, String PHONE, String EMAIL, int PASSPORTSERIES, int PASSPORTID, double CREDIT_AMOUNT, double CREDIT_TERM,double ENTRY_INTEREST_RATE ) {
        this.CLIENTID = CLIENTID;
        this.SURNAME = SURNAME;
        this.PATRONYMIC = PATRONYMIC;
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.EMAIL = EMAIL;
        this.PASSPORTSERIES = PASSPORTSERIES;
        this.PASSPORTID = PASSPORTID;
        this.CREDIT_AMOUNT = CREDIT_AMOUNT;
        this.CREDIT_TERM = CREDIT_TERM;
        this.ENTRY_INTEREST_RATE = ENTRY_INTEREST_RATE;
    }

    public Clients() {

    }

    public UUID getCLIENTID() {
        return CLIENTID;
    }

    public void setCLIENTID(UUID CLIENTID) {
        this.CLIENTID = CLIENTID;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME = SURNAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPATRONYMIC() {
        return PATRONYMIC;
    }

    public void setPATRONYMIC(String PATRONYMIC) {
        this.PATRONYMIC = PATRONYMIC;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getPASSPORTSERIES() {
        return PASSPORTSERIES;
    }

    public void setPASSPORTSERIES(int PASSPORTSERIES) {
        this.PASSPORTSERIES = PASSPORTSERIES;
    }

    public int getPASSPORTID() {
        return PASSPORTID;
    }

    public void setPASSPORTID(int PASSPORTID) {
        this.PASSPORTID = PASSPORTID;
    }

    public double getCREDIT_AMOUNT() {
        return CREDIT_AMOUNT;
    }

    public void setCREDIT_AMOUNT(double CREDIT_AMOUNT) {
        this.CREDIT_AMOUNT = CREDIT_AMOUNT;
    }

    public double getCREDIT_TERM() {
        return CREDIT_TERM;
    }

    public void setCREDIT_TERM(double CREDIT_TERM) {
        this.CREDIT_TERM = CREDIT_TERM;
    }

    public double getENTRY_INTEREST_RATE() {
        return ENTRY_INTEREST_RATE;
    }

    public void setENTRY_INTEREST_RATE(double ENTRY_INTEREST_RATE) {
        this.ENTRY_INTEREST_RATE = ENTRY_INTEREST_RATE;
    }

    @Override
    public Clients clone() {
        try {
            return (Clients)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

package model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Модель данных для взаимодействия приложение с базой данных.
 */
@Entity
@Table(name = "CLIENT")


public class Client implements Cloneable {

    private UUID CLIENTID = UUID.randomUUID();
    private String SURNAME;
    private String NAME;
    private String PATRONYMIC;
    private String PHONE;
    private String EMAIL;
    private String PASSPORTSERIES;
    private String PASSPORTID;
    private int CREDIT_AMOUNT;
    private int CREDIT_TERM;
    private double ENTRY_INTEREST_RATE;

    @OneToMany(mappedBy = "Client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CLIENTID", updatable = false)
    private List<PaymentSchedule> PaymentSchedule;

    public Client() {
    }
    public Client(UUID CLIENTID, String SURNAME, String NAME, String PATRONYMIC, String PHONE, String EMAIL, String PASSPORTSERIES, String PASSPORTID, int CREDIT_AMOUNT, int CREDIT_TERM,double ENTRY_INTEREST_RATE ) {
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

    public String getPASSPORTSERIES() {
        return PASSPORTSERIES;
    }

    public void setPASSPORTSERIES(String PASSPORTSERIES) {
        this.PASSPORTSERIES = PASSPORTSERIES;
    }

    public String getPASSPORTID() {
        return PASSPORTID;
    }

    public void setPASSPORTID(String PASSPORTID) {
        this.PASSPORTID = PASSPORTID;
    }

    public int getCREDIT_AMOUNT() {
        return CREDIT_AMOUNT;
    }

    public void setCREDIT_AMOUNT(int CREDIT_AMOUNT) {
        this.CREDIT_AMOUNT = CREDIT_AMOUNT;
    }

    public int getCREDIT_TERM() {
        return CREDIT_TERM;
    }

    public void setCREDIT_TERM(int CREDIT_TERM) {
        this.CREDIT_TERM = CREDIT_TERM;
    }

    public double getENTRY_INTEREST_RATE() {
        return ENTRY_INTEREST_RATE;
    }

    public void setENTRY_INTEREST_RATE(double ENTRY_INTEREST_RATE) {
        this.ENTRY_INTEREST_RATE = ENTRY_INTEREST_RATE;
    }

    @Override
    public Client clone() {
        try {
            return (Client)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

package com.example.application.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Модель данных для взаимодействия приложение с базой данных.
 */
@Entity
@Table(name = "PAYMENTSCHEDULE")

public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paymentId", updatable = false, nullable = false, unique=true)
    private final UUID paymentId = UUID.randomUUID();

    @JoinColumn(name = "number")
    private int number;
    private String date;
    private Double monthlyPaymentFormat;
    private Double percentFormat;
    private Double mainDebtFormat;
    private Double remainder;
    @Column(name = "personId",nullable=false)
    private UUID personId = UUID.randomUUID();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clients_clientid")
    private Clients clients;

    public PaymentSchedule() {

    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    /**
     * Внешний ключь для зависимости график платежей к клиенту
     */


    public PaymentSchedule(int number, String date, double monthlyPaymentFormat, double percentFormat, double mainDebtFormat, double remainder) {
        this.number = number;
        this.date = date;
        this.monthlyPaymentFormat = monthlyPaymentFormat;
        this.percentFormat = percentFormat;
        this.mainDebtFormat = mainDebtFormat;
        this.remainder = remainder;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMonthlyPaymentFormat() {
        return monthlyPaymentFormat;
    }

    public void setMonthlyPaymentFormat(Double monthlyPaymentFormat) {
        this.monthlyPaymentFormat = monthlyPaymentFormat;
    }

    public Double getPercentFormat() {
        return percentFormat;
    }

    public void setPercentFormat(Double percentFormat) {
        this.percentFormat = percentFormat;
    }

    public Double getMainDebtFormat() {
        return mainDebtFormat;
    }

    public void setMainDebtFormat(Double mainDebtFormat) {
        this.mainDebtFormat = mainDebtFormat;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    @Override
    public String toString(){

        return"models.Client{" +
                "paymentId=" + paymentId +
                ", number='" + number + '\'' +
                ", date=" + date + '\'' +
                ", monthlyPaymentFormat=" + monthlyPaymentFormat + '\'' +
                ", percentFormat=" + percentFormat + '\'' +
                ", mainDebtFormat=" + mainDebtFormat + '\'' +
                ", remainder=" + remainder +
                '}';
    }

    @Override
    public PaymentSchedule clone() {
        try {
            return (PaymentSchedule) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentSchedule that = (PaymentSchedule) o;
        return paymentId != null && Objects.equals(paymentId, that.paymentId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

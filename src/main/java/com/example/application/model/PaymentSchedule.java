package com.example.application.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * Модель данных для взаимодействия приложение с базой данных.
 */
@Entity
@Table(name = "PAYMENTSCHEDULE")

public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final UUID paymentId = UUID.randomUUID();

    @JoinColumn(name = "number")
    private int number;
    private String date;
    private double monthlyPaymentFormat;
    private double percentFormat;
    private double mainDebtFormat;
    private double remainder;
    @Column(name = "personId",nullable=false)
    private UUID personId = UUID.randomUUID();
    /**
     * Внешний ключь для зависимости график платежей к клиенту
     */
    @ManyToOne(optional=false)
    @JoinColumn(name = "personId", insertable=false, updatable=false)
    private Clients client;

    public PaymentSchedule(){
    }

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

    public double getMonthlyPaymentFormat() {
        return monthlyPaymentFormat;
    }

    public void setMonthlyPaymentFormat(double monthlyPaymentFormat) {
        this.monthlyPaymentFormat = monthlyPaymentFormat;
    }

    public double getPercentFormat() {
        return percentFormat;
    }

    public void setPercentFormat(double percentFormat) {
        this.percentFormat = percentFormat;
    }

    public double getMainDebtFormat() {
        return mainDebtFormat;
    }

    public void setMainDebtFormat(double mainDebtFormat) {
        this.mainDebtFormat = mainDebtFormat;
    }

    public double getRemainder() {
        return remainder;
    }

    public void setRemainder(double remainder) {
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
}

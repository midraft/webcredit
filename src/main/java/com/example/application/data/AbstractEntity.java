package com.example.application.data;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Entity
    @Table(name = "customers")
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        private UUID CLIENTID;

        private String SURNAME;

        private String NAME;

        private String PATRONYMIC;

        private String PHONE;

        private String EMAIL;

        private String PASSPORTSERIES;

        private String PASSPORTID;


        // standard constructors / setters / getters / toString
    }

    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity)) {
            return false; // null or other class
        }
        AbstractEntity other = (AbstractEntity) obj;

        if (id != null) {
            return id.equals(other.id);
        }
        return super.equals(other);
    }
}
package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private Long passcode;
    private Boolean active;
    private Long ecCode;
    private String bjValue;

    public Post() {}

    public Post(Long id, String email, Long passcode, Boolean active, Long ecCode, String bjValue) {
        this.id = id;
        this.email = email;
        this.passcode = passcode;
        this.active = active;
        this.ecCode = ecCode;
        this.bjValue = bjValue;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPasscode() {
        return passcode;
    }

    public void setPasscode(Long passcode) {
        this.passcode = passcode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getEcCode() {
        return ecCode;
    }

    public void setEcCode(Long ecCode) {
        this.ecCode = ecCode;
    }

    public String getBjValue() {
        return bjValue;
    }

    public void setBjValue(String bjValue) {
        this.bjValue = bjValue;
    }
}
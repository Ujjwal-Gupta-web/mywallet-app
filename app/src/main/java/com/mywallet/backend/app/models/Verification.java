package com.mywallet.backend.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "verifications")
public class Verification {
    @Id
    private String username;
    private String otp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    protected Verification() {
    }

    @Override
    public String toString() {
        return "Verification{" +
                "username='" + username + '\'' +
                ", otp='" + otp + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Verification(String username, String otp, Date lastUpdatedAt) {
        this.username = username;
        this.otp = otp;
        this.lastUpdatedAt = lastUpdatedAt;
    }
}

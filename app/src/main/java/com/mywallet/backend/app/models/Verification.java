package com.mywallet.backend.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "verification")
public class Verification {
    @Id
    private String username;
    private String otp;
    private Date lastUpdatedAt;

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

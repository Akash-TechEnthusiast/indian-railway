package com.india.railway.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Foreign key in User table
    @NotNull(message = "user must not be null")
    private User user;

    private LocalDateTime expirationDate;

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationDate = LocalDateTime.now().plusHours(1); // 1 hour expiry
    }

    // Getters and setters
}

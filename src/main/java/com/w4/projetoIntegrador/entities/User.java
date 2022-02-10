package com.w4.projetoIntegrador.entities;

import com.w4.projetoIntegrador.enums.ProfileTypes;

import org.hibernate.validator.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = 1L;

    public boolean isActive() {
        return enabled;
    }

    public void setActive(boolean active) {
        this.enabled = active;
    }

    @Id
    @Column(length = 20)
    @NotNull
    private String username;

    @Column
    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    private String password;
    
    @Column
    @NotNull
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProfileTypes profileType;
}

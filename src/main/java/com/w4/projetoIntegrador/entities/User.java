package com.w4.projetoIntegrador.entities;

import com.w4.projetoIntegrador.enums.ProfileTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private String username;

    @Column
    @NotBlank
    @Email
    private String email;

    @Column
    @NotBlank
    private String password;
    
    @Column
    @NotBlank
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private ProfileTypes profileType;
}

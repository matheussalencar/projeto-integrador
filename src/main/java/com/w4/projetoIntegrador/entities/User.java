package com.w4.projetoIntegrador.entities;

import com.w4.projetoIntegrador.enums.ProfileTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String username;

    @Column
    private String email;

    @Column
    private String password;
    
    @Column
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private ProfileTypes profileType;
}

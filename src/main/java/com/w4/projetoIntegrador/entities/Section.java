package com.w4.projetoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.w4.projetoIntegrador.enums.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sections")
@JsonIgnoreProperties({"type","totalSpace","warehouse"})
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonAlias({"sectionCode"})
    private Integer id;
    private ProductTypes type;
    private float totalSpace;

    @ManyToOne
    private Warehouse warehouse;
    @Transient
    private String warehouseCode;

    @ManyToOne
    private Inbound inbound;
}

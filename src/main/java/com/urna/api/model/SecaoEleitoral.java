package com.urna.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "secoes_eleitorais")
public class SecaoEleitoral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String local;

    // Relacionamento real com a tabela zonas_eleitorais
    @ManyToOne
    @JoinColumn(name = "zona_id", nullable = false)
    private ZonaEleitoral zona;

    public SecaoEleitoral() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public ZonaEleitoral getZona() {
        return zona;
    }

    public void setZona(ZonaEleitoral zona) {
        this.zona = zona;
    }
}
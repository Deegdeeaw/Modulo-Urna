package com.urna.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "zonas_eleitorais")
public class ZonaEleitoral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(length = 100, nullable = false)
    private String cidade;

    public ZonaEleitoral() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // Relacionamento com UF
    @ManyToOne
    @JoinColumn(name = "uf_id", nullable = false)
    private Uf uf;

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }
}
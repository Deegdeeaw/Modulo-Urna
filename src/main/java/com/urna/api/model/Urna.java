package com.urna.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "urnas")
public class Urna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(length = 20, nullable = false)
    private String status;

    // Relacionamento real com a tabela secoes_eleitorais
    @ManyToOne
    @JoinColumn(name = "secao_id", nullable = false)
    private SecaoEleitoral secao;

    // Relacionamento real com a tabela eleicoes
    @ManyToOne
    @JoinColumn(name = "eleicao_id", nullable = false)
    private Eleicao eleicao;

    public Urna() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SecaoEleitoral getSecao() {
        return secao;
    }

    public void setSecao(SecaoEleitoral secao) {
        this.secao = secao;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }
}
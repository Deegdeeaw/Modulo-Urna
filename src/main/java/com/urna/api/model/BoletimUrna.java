package com.urna.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "boletins_urna")
public class BoletimUrna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Urna
    @ManyToOne
    @JoinColumn(name = "urna_id", nullable = false)
    private Urna urna;

    // Relacionamento com Eleicao
    @ManyToOne
    @JoinColumn(name = "eleicao_id", nullable = false)
    private Eleicao eleicao;

    @Column(length = 64, nullable = false, unique = true)
    private String hash;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String assinatura;

    // Mapeamento para o tipo JSONB do PostgreSQL
    @Column(name = "dados_json", columnDefinition = "jsonb", nullable = false)
    private String dadosJson;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }

    public BoletimUrna() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Urna getUrna() {
        return urna;
    }

    public void setUrna(Urna urna) {
        this.urna = urna;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public String getDadosJson() {
        return dadosJson;
    }

    public void setDadosJson(String dadosJson) {
        this.dadosJson = dadosJson;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
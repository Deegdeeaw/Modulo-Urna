package com.urna.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "controle_votos")
public class ControleVoto {

    // O ID desta classe é o próprio ID do Eleitor
    @Id
    @Column(name = "eleitor_id")
    private Long eleitorId;

    // @MapsId avisa que o relacionamento abaixo define a chave primária
    @OneToOne
    @MapsId
    @JoinColumn(name = "eleitor_id")
    private Eleitor eleitor;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @PrePersist
    protected void onCreate() {
        this.dataHora = LocalDateTime.now();
    }

    public ControleVoto() {
    }

    public Long getEleitorId() {
        return eleitorId;
    }

    public void setEleitorId(Long eleitorId) {
        this.eleitorId = eleitorId;
    }

    public Eleitor getEleitor() {
        return eleitor;
    }

    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
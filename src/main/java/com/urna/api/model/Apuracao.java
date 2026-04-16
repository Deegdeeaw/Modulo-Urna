package com.urna.api.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "apuracao",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"candidato_id", "eleicao_id"})
        }
)
public class Apuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Candidato
    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    // Relacionamento com Eleicao
    @ManyToOne
    @JoinColumn(name = "eleicao_id", nullable = false)
    private Eleicao eleicao;

    @Column(name = "total_votos")
    private Integer totalVotos;

    public Apuracao() {
        // Inicializa com 0 para refletir o "DEFAULT 0" do banco de dados
        this.totalVotos = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }
}
package com.urna.api.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "candidatos",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"numero", "eleicao_id"})
        }
)
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer numero;

    // Relacionamento com Partido
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;

    // Relacionamento com Cargo
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    // Relacionamento com Eleicao
    @ManyToOne
    @JoinColumn(name = "eleicao_id", nullable = false)
    private Eleicao eleicao;

    public Candidato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }
}
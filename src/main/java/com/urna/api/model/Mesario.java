package com.urna.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mesarios")
public class Mesario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    // Relacionamento com SecaoEleitoral
    @ManyToOne
    @JoinColumn(name = "secao_id", nullable = false)
    private SecaoEleitoral secao;

    public Mesario() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public SecaoEleitoral getSecao() {
        return secao;
    }

    public void setSecao(SecaoEleitoral secao) {
        this.secao = secao;
    }
}
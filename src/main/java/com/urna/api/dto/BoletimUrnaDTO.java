package com.urna.api.dto;

import java.util.List;

public class BoletimUrnaDTO {

    private Long urnaId;
    private Long eleicaoId;
    private List<VotoDTO> votos;

    public BoletimUrnaDTO() {
    }

    public Long getUrnaId() {
        return urnaId;
    }

    public void setUrnaId(Long urnaId) {
        this.urnaId = urnaId;
    }

    public Long getEleicaoId() {
        return eleicaoId;
    }

    public void setEleicaoId(Long eleicaoId) {
        this.eleicaoId = eleicaoId;
    }

    public List<VotoDTO> getVotos() {
        return votos;
    }

    public void setVotos(List<VotoDTO> votos) {
        this.votos = votos;
    }
}
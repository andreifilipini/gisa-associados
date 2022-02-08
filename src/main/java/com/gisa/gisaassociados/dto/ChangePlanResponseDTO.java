package com.gisa.gisaassociados.dto;

import com.gisa.gisaassociados.model.TipoAtendimento;
import com.gisa.gisaassociados.model.TipoPlano;
import com.gisa.gisacore.dto.BasicTransactionResponseDTO;
import lombok.Getter;

@Getter
public class ChangePlanResponseDTO extends BasicTransactionResponseDTO {

    private TipoAtendimento tipoAtendimento;
    private TipoPlano tipoPlano;

    public ChangePlanResponseDTO(Long transactionId, boolean resultado, TipoAtendimento tipoAtendimento, TipoPlano tipoPlano) {
        super(transactionId, resultado);
        this.tipoAtendimento = tipoAtendimento;
        this.tipoPlano = tipoPlano;
    }
}

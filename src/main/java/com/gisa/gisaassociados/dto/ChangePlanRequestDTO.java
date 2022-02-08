package com.gisa.gisaassociados.dto;

import com.gisa.gisacore.dto.BasicTransactionRequestDTO;
import lombok.Getter;

@Getter
public class ChangePlanRequestDTO extends BasicTransactionRequestDTO {

    private Long idAssociado;
    private String planoId;

    public ChangePlanRequestDTO(Long transactionId, Long idAssociado, String planoId) {
        super(transactionId);
        this.idAssociado = idAssociado;
        this.planoId = planoId;
    }
}

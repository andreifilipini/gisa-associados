package com.gisa.gisaassociados.dto;

import com.gisa.gisaassociados.model.StatusPlano;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HistoricoPlanoDTO {

	private final String id;
	private final StatusPlano status;
}

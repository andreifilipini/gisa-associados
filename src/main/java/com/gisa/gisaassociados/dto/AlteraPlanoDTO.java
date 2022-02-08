package com.gisa.gisaassociados.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AlteraPlanoDTO {

	private final String planoId;

	@Setter
	private String userId;
}

package com.gisa.gisaassociados.controller;

import com.gisa.gisaassociados.dto.AlteraPlanoDTO;
import com.gisa.gisaassociados.dto.HistoricoPlanoDTO;
import com.gisa.gisaassociados.model.HistoricoPlano;
import com.gisa.gisaassociados.service.AssociadoService;
import com.gisa.gisacore.exception.InfraException;
import com.gisa.gisacore.model.RoleEnum;
import com.gisa.gisacore.util.CipherUtil;
import com.gisa.gisacore.util.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

	@Inject
	private AssociadoService planoService;

	@Inject
	private JwtTokenUtil jwtTokenUtil;

	@PutMapping("/plano")
	public ResponseEntity alteraPlano(@RequestHeader("Authorization") String auth,
			@RequestBody AlteraPlanoDTO planoDTO) {
		try {
			if(!jwtTokenUtil.verifyContainRole(auth, RoleEnum.CONVENIADO)) {
				planoDTO.setUserId(jwtTokenUtil.getSubject(auth));
			}

			return ResponseEntity.ok().body(toDTO(planoService.atualizarPlano(planoDTO.getUserId(), planoDTO.getPlanoId())));
		} catch (InfraException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	private HistoricoPlanoDTO toDTO(HistoricoPlano historicoPlano) {
		return HistoricoPlanoDTO.builder()
				.id(CipherUtil.encrypt32("hp_".concat(historicoPlano.getPlanoId().toString())))
				.status(historicoPlano.getStatus())
				.build();
	}

}

package com.gisa.gisaassociados.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
public class HistoricoPlano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(updatable = false)
	private Associado associado;

	@NotNull
	@Column(updatable = false)
	private String planoId;

	private LocalDateTime atualizacao;

	@Enumerated(EnumType.STRING)
	private StatusPlano status;

	@Enumerated(EnumType.STRING)
	private TipoAtendimento tipoAtendimento;

	@Enumerated(EnumType.STRING)
	private TipoPlano tipoPlano;

	public HistoricoPlano(Associado associado, String planoId) {
		super();
		this.associado = associado;
		this.planoId = planoId;
		atualizaStatus(StatusPlano.PENDENTE);
	}

	public void ativar(TipoAtendimento tipoAtendimento, TipoPlano tipoPlano) {
		atualizaStatus(StatusPlano.ATIVO);
		this.tipoAtendimento = tipoAtendimento;
		this.tipoPlano = tipoPlano;
	}

	public void inativar() {
		atualizaStatus(StatusPlano.INATIVO);
	}

	private void atualizaStatus(StatusPlano statusPlano) {
		this.status = statusPlano;
		this.atualizacao = LocalDateTime.now();
	}

}

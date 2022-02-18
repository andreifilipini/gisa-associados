package com.gisa.gisaassociados.service;

import com.gisa.gisaassociados.messages.AlteraPlanoProducer;
import com.gisa.gisaassociados.model.Associado;
import com.gisa.gisaassociados.model.HistoricoPlano;
import com.gisa.gisaassociados.model.TipoAtendimento;
import com.gisa.gisaassociados.model.TipoPlano;
import com.gisa.gisaassociados.repository.AssociadoRepository;
import com.gisa.gisaassociados.repository.HistoricoPlanoRepository;
import com.gisa.gisacore.exception.InfraException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class AssociadoService {

	@Inject
	private AssociadoRepository associadoRepository;

	@Inject
	private HistoricoPlanoRepository historicoPlanoRepository;

	@Inject
	private AlteraPlanoProducer alteraPlanoProducer;

	public HistoricoPlano atualizarPlano(String userId, String planoId) {
		Optional<Associado> associadoOpt = associadoRepository.findByUserId(userId);
		if(associadoOpt.isEmpty()) {
			throw new InfraException("Associado não encontrado");
		}
		Associado associado = associadoOpt.get();

		if(associado.getHistoricoPlano() != null && associado.getHistoricoPlano().getPlanoId().equalsIgnoreCase(planoId)) {
			throw new InfraException("Associado já vinculado à esse plano");
		}

		HistoricoPlano historicoPlano = new HistoricoPlano(associado, planoId);

		historicoPlanoRepository.save(historicoPlano);

		alteraPlanoProducer.send(historicoPlano.getId(), associado.getId(), historicoPlano.getPlanoId());

		return historicoPlano;
	}

	public void confirmaNovoPlano(Long transactionId, TipoAtendimento tipoAtendimento, TipoPlano tipoPlano) {
		Optional<HistoricoPlano> historicoPlanoOpt = historicoPlanoRepository.findById(transactionId);
		if(historicoPlanoOpt.isPresent()) {
			HistoricoPlano novoPlano = historicoPlanoOpt.get();
			Associado associado = novoPlano.getAssociado();
			Optional<HistoricoPlano> planoAntigo = Optional.ofNullable(associado.getHistoricoPlano());

			novoPlano.ativar(tipoAtendimento, tipoPlano);
			if (planoAntigo.isPresent()) {
				planoAntigo.get().inativar();
			}
			associado.setHistoricoPlano(novoPlano);

			if (planoAntigo.isPresent()) {
				historicoPlanoRepository.save(planoAntigo.get());
			}
			historicoPlanoRepository.save(novoPlano);
			associadoRepository.save(associado);
		}
	}

	public void cancelaNovoPlano(Long transactionId) {
		Optional<HistoricoPlano> historicoPlanoOpt = historicoPlanoRepository.findById(transactionId);
		if(historicoPlanoOpt.isPresent()) {
			HistoricoPlano historicoPlano = historicoPlanoOpt.get();
			historicoPlano.inativar();

			historicoPlanoRepository.save(historicoPlano);
		}
	}
}

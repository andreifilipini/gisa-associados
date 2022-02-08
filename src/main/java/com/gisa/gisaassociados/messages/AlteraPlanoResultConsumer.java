package com.gisa.gisaassociados.messages;

import com.gisa.gisaassociados.dto.ChangePlanResponseDTO;
import com.gisa.gisaassociados.service.AssociadoService;
import com.gisa.gisacore.messages.AbstractRabbitConsumer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Slf4j
@Component
public class AlteraPlanoResultConsumer extends AbstractRabbitConsumer {

    @Inject
    private AssociadoService associadoService;

    @RabbitListener(queues = {"${queue.changePlanResult}"})
    protected void receive(@Payload String body) {
        executeLoggin(body);
    }

    @Override
    protected void execute(@Payload String body) {
        Gson gson = new Gson();
        ChangePlanResponseDTO response = gson.fromJson(body, ChangePlanResponseDTO.class);

        if(response.isApproved()) {
            associadoService.confirmaNovoPlano(response.getTransactionId(), response.getTipoAtendimento(), response.getTipoPlano());
        } else {
            associadoService.cancelaNovoPlano(response.getTransactionId());
        }

    }

    @Override
    protected Logger getLogger() {
        return log;
    }
}

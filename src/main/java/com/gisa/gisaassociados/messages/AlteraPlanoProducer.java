package com.gisa.gisaassociados.messages;

import com.gisa.gisaassociados.dto.ChangePlanRequestDTO;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class AlteraPlanoProducer {

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Value("${queue.changePlanLegacy}")
    private String changePlanLegacyQueueName;

    public void send(Long transactionId, Long idAssociado, String planoId) {
        ChangePlanRequestDTO request = new ChangePlanRequestDTO(transactionId, idAssociado, planoId);
        rabbitTemplate.convertAndSend(this.changePlanLegacyQueueName, new Gson().toJson(request));
    }
}

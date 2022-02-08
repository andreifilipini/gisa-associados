package com.gisa.gisaassociados.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Setter
    private String nome;

    @NotNull
    @Column(updatable = false)
    private String userId;

    @Setter
    private LocalDateTime dtNascimento;

    @Setter
    @ManyToOne
    private HistoricoPlano historicoPlano;

    public Associado(String nome, String userId, LocalDateTime dtNascimento, String planoId) {
        this.nome = nome;
        this.userId = userId;
        this.dtNascimento = dtNascimento;
        this.historicoPlano = new HistoricoPlano(this, planoId);
    }

    public void trocarPlano(HistoricoPlano historicoPlano) {
        if (historicoPlano != null) {
            this.historicoPlano.inativar();
        }
    }
}

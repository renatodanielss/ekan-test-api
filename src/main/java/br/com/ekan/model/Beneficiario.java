package br.com.ekan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "beneficiario")
@Data
@NoArgsConstructor
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    @Size(max = 100)
    @NotNull
    private String nome;

    @Column(name = "telefone")
    @Size(max = 11)
    @NotNull
    private String telefone;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", columnDefinition = "DATE")
    private Date dataNascimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inclusao", columnDefinition = "DATETIME", updatable = false)
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao", columnDefinition = "DATETIME")
    private Date dataAtualizacao;

    public Beneficiario(Integer id) {
        this.id = id;
    }

    @PrePersist
    void onPersist() {
        if (this.dataInclusao == null) {
            this.dataInclusao = new Date();
        }
        if (this.dataAtualizacao == null) {
            this.dataAtualizacao = new Date();
        }
    }

    @PreUpdate
    void onUpdate() {
        this.dataAtualizacao = new Date();
    }
}
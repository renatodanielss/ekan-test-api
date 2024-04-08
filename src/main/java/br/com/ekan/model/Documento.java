package br.com.ekan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "documento")
@Data
@NoArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo_documento")
    @Size(max = 30)
    @NotNull
    private String tipoDocumento;

    @Column(name = "descricao")
    @Size(max = 500)
    @NotNull
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id")
    private Beneficiario beneficiario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inclusao", columnDefinition = "DATETIME", updatable = false)
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao", columnDefinition = "DATETIME", updatable = false)
    private Date dataAtualizacao;

    public Documento(String tipoDocumento, String descricao, Beneficiario beneficiario) {
        this.tipoDocumento = tipoDocumento;
        this.descricao = descricao;
        this.beneficiario = beneficiario;
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
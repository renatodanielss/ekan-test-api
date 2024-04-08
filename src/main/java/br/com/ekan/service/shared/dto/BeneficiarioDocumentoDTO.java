package br.com.ekan.service.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiarioDocumentoDTO {
    @Size(max = 30)
    @NotNull
    private String tipoDocumento;

    @Size(max = 500)
    @NotNull
    private String descricao;

    @NotNull
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private Integer beneficiarioId;

    public BeneficiarioDocumentoDTO setBeneficiarioId(Integer beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
        return this;
    }
}

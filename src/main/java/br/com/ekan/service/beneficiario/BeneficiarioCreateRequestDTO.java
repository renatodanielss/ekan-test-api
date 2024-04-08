package br.com.ekan.service.beneficiario;

import br.com.ekan.config.JsonDateDeserializer;
import br.com.ekan.service.shared.dto.BeneficiarioDocumentoDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiarioCreateRequestDTO {
    @Size(max = 100, message = "É permitido o máximo de 100 caracteres")
    @NotNull(message = "O campo nome é obrigatório")
    private String nome;

    @Size(max = 100, message = "É permitido o máximo de 11 caracteres")
    @NotNull(message = "O campo telefone é obrigatório")
    private String telefone;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date dataNascimento;

    private List<BeneficiarioDocumentoDTO> beneficiarioDocumentoDTOs;
}

package br.com.ekan.service.documento;

import br.com.ekan.model.Documento;
import br.com.ekan.service.shared.dto.BeneficiarioDocumentoDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IDocumentoService {

    List<Documento> saveAll(List<BeneficiarioDocumentoDTO> beneficiarioDocumentoDTOs);

    List<Documento> findAllByBeneficiarioId(Integer beneficiarioId);
}

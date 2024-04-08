package br.com.ekan.service.documento;

import br.com.ekan.model.Beneficiario;
import br.com.ekan.model.Documento;
import br.com.ekan.repository.DocumentoRepository;
import br.com.ekan.service.shared.dto.BeneficiarioDocumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@SuppressWarnings("unused")
public class DocumentoService implements IDocumentoService {

    private final DocumentoRepository documentoRepository;

    @Autowired
    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @Override
    @Transactional
    public List<Documento> saveAll(List<BeneficiarioDocumentoDTO> beneficiarioDocumentoDTOs) {
        List<Documento> documentos =
                beneficiarioDocumentoDTOs.stream()
                        .map(d -> new Documento(d.getTipoDocumento(), d.getDescricao(), new Beneficiario(d.getBeneficiarioId())))
                        .collect(Collectors.toList());

        return this.documentoRepository.saveAll(documentos);
    }
    @Override
    @Transactional
    public List<Documento> findAllByBeneficiarioId(Integer beneficiarioId) {
        return this.documentoRepository.findAllByBeneficiario(new Beneficiario(beneficiarioId));
    }
}

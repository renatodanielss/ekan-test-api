package br.com.ekan.service.beneficiario;

import br.com.ekan.mapper.BeneficiarioMapper;
import br.com.ekan.model.Beneficiario;
import br.com.ekan.repository.BeneficiarioRepository;
import br.com.ekan.repository.DocumentoRepository;
import br.com.ekan.service.documento.IDocumentoService;
import br.com.ekan.service.shared.dto.BeneficiarioDocumentoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@SuppressWarnings("unused")
public class BeneficiarioService implements IBeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final DocumentoRepository documentoRepository;
    private final IDocumentoService documentoService;
    private final BeneficiarioMapper beneficiarioMapper;

    @Autowired
    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, DocumentoRepository documentoRepository,
                               IDocumentoService documentoService, BeneficiarioMapper beneficiarioMapper) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.documentoRepository = documentoRepository;
        this.documentoService = documentoService;
        this.beneficiarioMapper = beneficiarioMapper;
    }

    @Override
    @Transactional
    public Beneficiario save(BeneficiarioCreateRequestDTO beneficiarioCreateRequestDTO) {
        Beneficiario beneficiario = this.beneficiarioMapper.dtoToEntity(beneficiarioCreateRequestDTO);
        final Beneficiario beneficiarioSaved = this.beneficiarioRepository.save(beneficiario);

        List<BeneficiarioDocumentoDTO> beneficiarioDocumentoDTOs =
                beneficiarioCreateRequestDTO.getBeneficiarioDocumentoDTOs().stream()
                        .map(d -> d.setBeneficiarioId(beneficiarioSaved.getId()))
                        .collect(Collectors.toList());

        documentoService.saveAll(beneficiarioDocumentoDTOs);
        return beneficiarioSaved;
    }

    @Override
    @Transactional
    public Beneficiario update(Integer id, BeneficiarioUpdateRequestDTO beneficiarioUpdateRequestDTO) {
        Optional<Beneficiario> beneficiarioOptional = this.beneficiarioRepository.findById(id);
        if (beneficiarioOptional.isEmpty()) {
            throw new EntityNotFoundException("Beneficiário não encontrado!");
        }

        Beneficiario beneficiario = beneficiarioOptional.get();
        this.beneficiarioMapper.dtoToEntity(beneficiario, beneficiarioUpdateRequestDTO);
        return beneficiario;
    }

    @Override
    @Transactional
    public List<Beneficiario> findAll() {
        return this.beneficiarioRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        this.documentoRepository.deleteAllByBeneficiarioId(id);
        this.beneficiarioRepository.deleteById(id);
    }
}

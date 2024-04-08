package br.com.ekan.service.beneficiario;

import br.com.ekan.model.Beneficiario;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IBeneficiarioService {

    Beneficiario save(BeneficiarioCreateRequestDTO beneficiarioCreateRequestDTO);

    Beneficiario update(Integer id, BeneficiarioUpdateRequestDTO beneficiarioUpdateRequestDTO);

    List<Beneficiario> findAll();

    void deleteById(Integer id);
}

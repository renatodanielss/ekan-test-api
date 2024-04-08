package br.com.ekan.mapper;

import br.com.ekan.model.Beneficiario;
import br.com.ekan.service.beneficiario.BeneficiarioCreateRequestDTO;
import br.com.ekan.service.beneficiario.BeneficiarioUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BeneficiarioMapper {

    Beneficiario dtoToEntity(BeneficiarioCreateRequestDTO beneficiarioCreateRequestDTO);

    void dtoToEntity(@MappingTarget Beneficiario beneficiario, BeneficiarioUpdateRequestDTO beneficiarioUpdateRequestDTO);

}

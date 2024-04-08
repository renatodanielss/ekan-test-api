package br.com.ekan.repository;

import br.com.ekan.model.Beneficiario;
import br.com.ekan.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    List<Documento> findAllByBeneficiario(Beneficiario beneficiario);

    @Transactional
    @Modifying
    @Query("DELETE FROM Documento d WHERE d.beneficiario.id = :beneficiarioId")
    void deleteAllByBeneficiarioId(@Param("beneficiarioId") Integer beneficiarioId);
}
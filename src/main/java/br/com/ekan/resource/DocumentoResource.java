package br.com.ekan.resource;

import br.com.ekan.model.Documento;
import br.com.ekan.service.documento.IDocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@ApiResponses(value= {
		@ApiResponse(responseCode = "200", description = "Return ResponseModel with success message"),
		@ApiResponse(responseCode = "500", description = "Case has error, return a ResponseModel with Exception")
})
@RequestMapping("/documentos")
//@PreAuthorize("#oauth2.hasScope('bettha_logged')")
public class DocumentoResource {

	private final IDocumentoService documentoService;

	@Autowired
	public DocumentoResource(IDocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	@GetMapping
	@Operation(
			summary = "Busca todos os documentos pelo id de um beneficiário",
			description = "Esta operação busca todos os documentos pelo id de um beneficiário"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@SecurityRequirement(name = "basicAuth")
	public ResponseEntity<List<Documento>> findAllByBeneficiarioId(@RequestParam(name="beneficiarioId") Integer beneficiarioId) {
		return new ResponseEntity<>(this.documentoService.findAllByBeneficiarioId(beneficiarioId), HttpStatus.OK);
	}
}

package br.com.ekan.resource;

import br.com.ekan.model.Beneficiario;
import br.com.ekan.service.beneficiario.BeneficiarioCreateRequestDTO;
import br.com.ekan.service.beneficiario.BeneficiarioUpdateRequestDTO;
import br.com.ekan.service.beneficiario.IBeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ApiResponses(value= {
		@ApiResponse(responseCode = "200", description = "Return ResponseModel with success message"),
		@ApiResponse(responseCode = "500", description = "Case has error, return a ResponseModel with Exception")
})
@RequestMapping("/beneficiarios")
public class BeneficiarioResource {

	private final IBeneficiarioService beneficiarioService;

	@Autowired
	public BeneficiarioResource(IBeneficiarioService beneficiarioService) {
		this.beneficiarioService = beneficiarioService;
	}

	@PostMapping
	@Operation(
			summary = "Cria um beneficiário",
			description = "Esta operação cria um beneficiário"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@SecurityRequirement(name = "basicAuth")
	public @ResponseBody ResponseEntity<Beneficiario> save(@RequestBody @Valid BeneficiarioCreateRequestDTO beneficiarioCreateRequestDTO) {
		return new ResponseEntity<>(this.beneficiarioService.save(beneficiarioCreateRequestDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(
			summary = "Atualiza os dados de um beneficiário",
			description = "Esta operação atualiza os dados de um beneficiário"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@SecurityRequirement(name = "basicAuth")
	public @ResponseBody ResponseEntity<Beneficiario> update(@PathVariable("id") Integer id,
															 @RequestBody @Valid BeneficiarioUpdateRequestDTO beneficiarioUpdateRequestDTO) {
		return new ResponseEntity<>(this.beneficiarioService.update(id, beneficiarioUpdateRequestDTO), HttpStatus.OK);
	}

	@GetMapping
	@Operation(
			summary = "Busca todos os beneficiários",
			description = "Esta operação busca todos os beneficiários"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@SecurityRequirement(name = "basicAuth")
	public ResponseEntity<List<Beneficiario>> findAll() {
		return new ResponseEntity<>(this.beneficiarioService.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(
			summary = "Busca todos os beneficiários",
			description = "Esta operação busca todos os beneficiários"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@SecurityRequirement(name = "basicAuth")
	public void delete(@PathVariable("id") Integer id) {
		this.beneficiarioService.deleteById(id);
	}
}

package desafio.crud.usercrud.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;
import desafio.crud.usercrud.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	private UsuarioServiceImpl service;
	
	public UsuarioController(UsuarioServiceImpl service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objDto) {
		Usuario userValidado = service.validaUsuario(objDto);
		userValidado = service.inserir(userValidado);
        return ResponseEntity.noContent().build();
    }
}

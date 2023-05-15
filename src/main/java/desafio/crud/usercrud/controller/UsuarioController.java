package desafio.crud.usercrud.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;
import desafio.crud.usercrud.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	private UsuarioServiceImpl service;
	
	@Autowired
	public UsuarioController(UsuarioServiceImpl service) {
		this.service = service;
	}

	@PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objDto) throws ParseException {
		Usuario userValidado = service.validaUsuario(objDto);
		userValidado = service.inserir(userValidado);
        return ResponseEntity.created(null).build();
    }
}

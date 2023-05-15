package desafio.crud.usercrud.services;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;

@Service
public interface UsuarioService {
	
	public Usuario validaUsuario(UsuarioDTO dto) throws ParseException;
	
	public Usuario inserir(Usuario user);
}

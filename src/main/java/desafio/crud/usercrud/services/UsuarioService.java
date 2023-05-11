package desafio.crud.usercrud.services;

import java.text.ParseException;

import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;

public interface UsuarioService {
	
	public Usuario validaUsuario(UsuarioDTO dto) throws ParseException;
	
	public Usuario inserir(Usuario user);
}

package desafio.crud.usercrud.services;

import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;

public interface UsuarioService {
	
	public Usuario validaUsuario(UsuarioDTO dto);
	
	public Usuario inserir(Usuario user);
}

package desafio.crud.usercrud.services.impl;

import org.springframework.stereotype.Service;

import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;
import desafio.crud.usercrud.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Override
	public Usuario validaUsuario(UsuarioDTO dto) {
		// TODO Auto-generated method stub
		return FromDto(dto);
	}
	
	@Override
	public Usuario inserir(Usuario userValidado) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Usuario FromDto(UsuarioDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

package desafio.crud.usercrud.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;
import desafio.crud.usercrud.repository.UsuarioRepository;
import desafio.crud.usercrud.services.UsuarioService;
import desafio.crud.usercrud.services.exceptions.CustomValidationException;

@Qualifier("user")
@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Usuario validaUsuario(UsuarioDTO dto) throws ParseException {
		validaNome(dto.getNome());
		validaEmail(dto.getEmail());
		validaDataNascimento(dto.getDataNascimento());
		validaEndereco(dto.getEndereco());

		return FromDto(dto);
	}

	@Override
	@Transactional
	public Usuario inserir(Usuario obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}

	private void validaNome(String nome) {
		String pattern = "^[a-zA-Z0-9. ]*$";
		if (!nome.matches(pattern)) {
			throw new CustomValidationException("Não é permitido símbolos para o nome");
		}
	}

	private void validaEmail(String email) {
		Pattern patternProvedoresValidos = Pattern.compile("@gmail.com|@hotmail.com|@outlook.com|@yahoo.com");
		Matcher matcher = patternProvedoresValidos.matcher(email);
		if (!matcher.find()) {
			throw new CustomValidationException(
					"Digite de um desses provedores (Gmail, Hotmail, Outlook e Yahoo)");
		}
	}

	private void validaDataNascimento(String dataNascimento) {
		Date data= validaDataPadraoPtBr(dataNascimento);
		
		Calendar dataNasc = Calendar.getInstance();
		dataNasc.setTime(data);
		
		dataNasc.add(Calendar.YEAR, 18);
		Calendar dataAtual = Calendar.getInstance();
		if (!dataNasc.before(dataAtual)) {
			throw new CustomValidationException("Permitido cadastro de usuário apenas para maiores de 18 anos");
		}
	}
	
	private Date validaDataPadraoPtBr(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = null;
		try {
			dataFormatada = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new CustomValidationException(
					"Erro ao converter data: padrão deve ser dd/MM/yyyy .", e);
		}
		return dataFormatada;
	}

	private void validaEndereco(String endereco) {
		String pattern = "^[A-Za-z0-9 ]+$";
		if (!endereco.matches(pattern)) {
			throw new CustomValidationException("Não é permitido e caracteres especiais para o endereco");
		}
	}

	private Usuario FromDto(UsuarioDTO dto) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = sdf.parse(dto.getDataNascimento());
		
		Usuario user = new Usuario();
		user.setNome(dto.getNome());
		user.setEmail(dto.getEmail());
		user.setDataNascimento(dataNascimento);
		user.setEndereco(dto.getEndereco());
		user.setHabilidades(dto.getHabilidades());
		return user;
	}
}

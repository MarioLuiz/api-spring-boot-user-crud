package desafio.crud.usercrud.services.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import desafio.crud.usercrud.UserCrudApplication;
import desafio.crud.usercrud.domain.Usuario;
import desafio.crud.usercrud.dto.UsuarioDTO;
import desafio.crud.usercrud.repository.UsuarioRepository;
import desafio.crud.usercrud.services.exceptions.CustomValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = UserCrudApplication.class)
@AutoConfigureMockMvc
public class UsuarioServiceImplTest {

	@Mock
	private UsuarioRepository repository;

	@InjectMocks
	private UsuarioServiceImpl service;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void deveinserirUsuario() throws ParseException {
		// cenario
		Usuario user = builderUsuario();
		Mockito.when(repository.save(any())).thenReturn(user);

		// acao
		Usuario usuario = service.inserir(user);

		// verificacao
		Assert.assertNotNull(usuario);
	}
	
	@Test
	public void deveValidarUsuarioDto() throws ParseException {
		// cenario
		UsuarioDTO userDTO = builderUsuarioDTO();
		Calendar dataNasc = Calendar.getInstance();
		dataNasc.set(1993, 3, 2, 0, 0, 0);
		Date dataNascimento = dataNasc.getTime();
		ArrayList<String> habilidades = new ArrayList<String>(
				Arrays.asList("Esperto", "Curioso", "empreendedor", "Estudioso"));

		// acao
		Usuario user = service.validaUsuario(userDTO);

		// verificacao
		Assert.assertNotNull(user);
		Assert.assertThat(user.getNome(), is("Mariel Carlos"));
		Assert.assertThat(user.getEmail(), is("carlos@gmail.com"));
		Assert.assertTrue(isMesmaDataDiaMesAno(user.getDataNascimento(), dataNascimento));
		Assert.assertThat(user.getEndereco(), is("Rua Sao joao"));
		Assert.assertThat(user.getHabilidades(), is(habilidades));
	}

	@Test
	public void deveLancarErroAoValidarUsuarioDtoNome() throws ParseException {
		// cenario
		UsuarioDTO userDTO = builderUsuarioDTO();
		userDTO.setNome("Lindoia Raíça");

		// verificacao
		expectedException.expect(CustomValidationException.class);
		expectedException.expectMessage("Não é permitido símbolos para o nome");

		// acao
		service.validaUsuario(userDTO);
	}
	
	@Test
	public void deveLancarErroAoValidarUsuarioDtoEmail() throws ParseException {
		// cenario
		UsuarioDTO userDTO = builderUsuarioDTO();
		userDTO.setEmail("marioLuiz@bol.com");

		// verificacao
		expectedException.expect(CustomValidationException.class);
		expectedException.expectMessage("Digite de um desses provedores (Gmail, Hotmail, Outlook e Yahoo)");

		// acao
		service.validaUsuario(userDTO);
	}
	
	@Test
	public void deveLancarErroAoValidarUsuarioDtoDataMenorQue18Anos() throws ParseException {
		// cenario
		UsuarioDTO userDTO = builderUsuarioDTO();
		userDTO.setDataNascimento("25/02/2023");

		// verificacao
		expectedException.expect(CustomValidationException.class);
		expectedException.expectMessage("Permitido cadastro de usuário apenas para maiores de 18 anos");

		// acao
		service.validaUsuario(userDTO);
	}
	
	@Test
	public void deveLancarErroFormatacaoAoValidarUsuarioDtoData() throws ParseException {
		// cenario
		UsuarioDTO userDTO = builderUsuarioDTO();
		userDTO.setDataNascimento("02/25");

		// verificacao
		expectedException.expect(CustomValidationException.class);
		expectedException.expectMessage("Erro ao converter data: padrão deve ser dd/MM/yyyy .");

		// acao
		service.validaUsuario(userDTO);
	}
	
	@Test
	public void deveLancarErroAoValidarUsuarioDtoEndereco() throws ParseException {
		// cenario
		UsuarioDTO userDTO = builderUsuarioDTO();
		userDTO.setEndereco("Rua bento gonçalves - nº 2102");

		// verificacao
		expectedException.expect(CustomValidationException.class);
		expectedException.expectMessage("Não é permitido e caracteres especiais para o endereco");

		// acao
		service.validaUsuario(userDTO);
	}

	private UsuarioDTO builderUsuarioDTO() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNome("Mariel Carlos");
		dto.setEmail("carlos@gmail.com");
		dto.setDataNascimento("02/04/1993");
		dto.setEndereco("Rua Sao joao");
		ArrayList<String> habilidades = new ArrayList<String>();
		habilidades.addAll(Arrays.asList("Esperto", "Curioso", "empreendedor", "Estudioso"));
		dto.setHabilidades(habilidades);

		return dto;
	}
	
	private Usuario builderUsuario() {
		Calendar dataNasc = Calendar.getInstance();
		dataNasc.set(1993, 3, 2, 0, 0, 0);
		Date dataNascimento = dataNasc.getTime();
		
		Usuario dto = new Usuario();
		dto.setId((long) 42);
		dto.setNome("Mariel Carlos");
		dto.setEmail("carlos@gmail.com");
		dto.setDataNascimento(dataNascimento);
		dto.setEndereco("Rua Sao joao");
		ArrayList<String> habilidades = new ArrayList<String>();
		habilidades.addAll(Arrays.asList("Esperto", "Curioso", "empreendedor", "Estudioso"));
		dto.setHabilidades(habilidades);

		return dto;
	}

	private boolean isMesmaDataDiaMesAno(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
		return sameDay;
	}

}
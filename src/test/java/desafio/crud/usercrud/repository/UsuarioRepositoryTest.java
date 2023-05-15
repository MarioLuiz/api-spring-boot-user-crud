package desafio.crud.usercrud.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import desafio.crud.usercrud.domain.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void DeveInserirUsuarioERetornalo() {
		// cenario
		Usuario mariel = builderUsuario();
		entityManager.persist(mariel);
		entityManager.flush();

		// acao
		Optional<Usuario> encontrado = usuarioRepository.findById(mariel.getId());

		// veririficacao
		Assert.assertEquals(encontrado.get().getNome(), mariel.getNome());
	}

	private Usuario builderUsuario() {
		Calendar dataNasc = Calendar.getInstance();
		dataNasc.set(1993, 3, 2, 0, 0, 0);
		Date dataNascimento = dataNasc.getTime();

		Usuario dto = new Usuario();
		dto.setId(null);
		dto.setNome("Mariel Carlos");
		dto.setEmail("carlos@gmail.com");
		dto.setDataNascimento(dataNascimento);
		dto.setEndereco("Rua Sao joao");
		ArrayList<String> habilidades = new ArrayList<String>();
		habilidades.addAll(Arrays.asList("Esperto", "Curioso", "empreendedor", "Estudioso"));
		dto.setHabilidades(habilidades);

		return dto;
	}

}

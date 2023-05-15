package desafio.crud.usercrud.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import desafio.crud.usercrud.services.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mvc;

	@InjectMocks
	private UsuarioServiceImpl service;
	
	@Mock
	private UsuarioController usuarioController;

	@Test
    public void insert() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders
				.post("/usuarios")
				.content(getBodyContent())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
    }
	
	private String getBodyContent() {
		String BodyUsuarioDto = "{   \r\n"
				+ "    \"nome\": \"Carlota Joaquina\",\r\n"
				+ "    \"email\": \"carlota@gmail.com\",\r\n"
				+ "    \"dataNascimento\": \"25/05/1990\",\r\n"
				+ "    \"endereco\": \"Rua sao borja\",\r\n"
				+ "    \"habilidades\": [\"Paciente\",\"Curiosa\",\"Estudiosa\"]\r\n"
				+ "}";
		return BodyUsuarioDto;
	}
}
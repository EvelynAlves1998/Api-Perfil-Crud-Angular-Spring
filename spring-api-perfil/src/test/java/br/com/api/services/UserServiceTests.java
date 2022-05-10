package br.com.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.perfil.dtos.UserDTO;
import br.com.perfil.entities.UserEntity;
import br.com.perfil.repositories.UserRepository;
import br.com.perfil.services.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests {
	@Autowired
	private UserService service;

	// a anotação @MockBean serve para sinalizar que iremos
	// "MOCKAR(SIMULAR) a camada repository"
	@MockBean
	private UserRepository repository;

	@Test
	void getAllTest() {

		// vamos criar uma lista de entidade de usuario com o objeto
		// de retornar a mesma quando o usuarioRepository.findAll()
		// for acionado

		List<UserEntity> listaMockada = new ArrayList<UserEntity>();

//		UserEntity usuarioEntidade = new UserEntity();
//		UserEntidade.setCep("04567895");
//		UserEntidade.setNome("Evelyn Alves");
//		UserEntidade.setNumero(3);
//		UserEntidade.setRua("Rua de Teste");
//		UserEntidade.setId(1);

		UserEntity usuarioEntidade = createValidUsuario();

		listaMockada.add(usuarioEntidade);

		// quando o repository for acionado, retorno a lista Mockada
		when(repository.findAll()).thenReturn(listaMockada);

		List<UserDTO> retorno = service.getAll();

//		assertThat( listaMockada.get(0).getCep() ).isEqualTo( retorno.get(0).getCep() );
//		assertThat( listaMockada.get(0).getNome() ).isEqualTo( retorno.get(0).getNome() );
//		assertThat( listaMockada.get(0).getNumero() ).isEqualTo( retorno.get(0).getNumero() );
//		assertThat( listaMockada.get(0).getRua() ).isEqualTo( retorno.get(0).getRua() );
//		assertThat( listaMockada.get(0).getId() ).isEqualTo( retorno.get(0).getId() );

		// validar a resposta
		isUsuarioValid(retorno.get(0), listaMockada.get(0));

	}

	// quando o objeto é achado no banco de dados
	@Test
	void getOneWhenFoundObjectTest() {

		UserEntity usuarioEntidade = createValidUsuario();

		Optional<UserEntity> optional = Optional.of(usuarioEntidade);

		when(repository.findById(1)).thenReturn(optional);

		// execução
		UserDTO obj = service.getOne(1);

		// validação

//		assertThat( obj.getCep() ).isEqualTo( usuarioEntidade.getCep() );
//		assertThat( obj.getNome() ).isEqualTo( usuarioEntidade.getNome() );
//		assertThat( obj.getNumero() ).isEqualTo( usuarioEntidade.getNumero() );
//		assertThat( obj.getRua() ).isEqualTo( usuarioEntidade.getRua() );
//		assertThat( obj.getId() ).isEqualTo( usuarioEntidade.getId() );

		// validar a resposta
		isUsuarioValid(obj, usuarioEntidade);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTest() {

		// Optional.empty() -> simulando o caso de NÃO achar o registro no banco de
		// dados
		Optional<UserEntity> optional = Optional.empty();

		when(repository.findById(1)).thenReturn(optional);

		// execução
		UserDTO obj = service.getOne(1);

		// objeto com valores "padrão"
		UserEntity usuarioEntidade = new UserEntity();

		// validação

//		assertThat( obj.getCep() ).isEqualTo( usuarioEntidade.getCep() );
//		assertThat( obj.getNome() ).isEqualTo(  usuarioEntidade.getNome() );
//		assertThat( obj.getNumero() ).isEqualTo(  usuarioEntidade.getNumero() );
//		assertThat( obj.getRua() ).isEqualTo(  usuarioEntidade.getRua() );
//		assertThat( obj.getId() ).isEqualTo(  usuarioEntidade.getId() );

		// validar a resposta
		isUsuarioValid(obj, usuarioEntidade);
	}

	@Test
	void saveTest() {

		// 1-) Cenário
		// objeto com dados válidos de um usuario
		UserEntity usuarioEntidade = createValidUsuario();

		// quando o usuarioRepository.save for acionado, retornaremos um objeto de
		// usuario com dados válidos
		when(repository.save(usuarioEntidade)).thenReturn(usuarioEntidade);

		UserDTO usuarioSalvo = service.save(usuarioEntidade);

		// validar a resposta
		isUsuarioValid(usuarioSalvo, usuarioEntidade);

	}

	@Test
	void updateWhenFoundObj() {

		// Cenário

		UserEntity usuarioEntidade = createValidUsuario();
		Optional<UserEntity> optional = Optional.of(usuarioEntidade);

		// mocks
		when(repository.findById(usuarioEntidade.getId())).thenReturn(optional);
		when(repository.save(usuarioEntidade)).thenReturn(usuarioEntidade);

		// execução
		UserDTO usuarioAlterado = service.update(usuarioEntidade.getId(), usuarioEntidade);

		// validar a resposta
		isUsuarioValid(usuarioAlterado, usuarioEntidade);
	}

	@Test
	void updateWhenNotFoundObj() {

		// Cenário
		// Optional.empty() por conta que não achou o objeto a ser alterado
		Optional<UserEntity> optional = Optional.empty();

		UserEntity usuarioEntidade = createValidUsuario();

		// mocks
		when(repository.findById(1)).thenReturn(optional);

		// execução
		// estamos passando como argumento o usuarioEntidade pois
		// em suposição ele não estará no banco de dados neste cenário
		UserDTO usuarioAlterado = service.update(1, usuarioEntidade);

		// validar a resposta
		isUsuarioValid(usuarioAlterado, new UserEntity());
	}

	@Test
	void deleteTest() {

		// execução
		// assertDoesNotThrow espera uma lambda (método sem nome) e verifica se a lambda
		// executa sem erro
		assertDoesNotThrow(() -> service.delete(1));

		// verifico se o UserRepository.deleteById foi executado somente uma vez
		verify(repository, times(1)).deleteById(1);
	}

	private void isUsuarioValid(UserDTO obj, UserEntity usuarioEntidade) {

		assertThat(obj.getCep()).isEqualTo(usuarioEntidade.getCep());
		assertThat(obj.getNome()).isEqualTo(usuarioEntidade.getNome());
		assertThat(obj.getNumero()).isEqualTo(usuarioEntidade.getNumero());
		assertThat(obj.getRua()).isEqualTo(usuarioEntidade.getRua());
		assertThat(obj.getId()).isEqualTo(usuarioEntidade.getId());
	}

	private UserEntity createValidUsuario() {

		// instanciando o novo objeto do tipo UserEntity
		UserEntity usuarioEntidade = new UserEntity();

		// colocando valores nos atributos de UserEntity
		usuarioEntidade.setCep("04567895");
		usuarioEntidade.setNome("Evelyn Alves");
		usuarioEntidade.setNumero(3);
		usuarioEntidade.setRua("Rua de Teste");
		usuarioEntidade.setId(1);

		// retornando este novo objeto criado
		return usuarioEntidade;
	}

}

package br.com.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.perfil.entities.UserEntity;
import br.com.perfil.repositories.UserRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {

		UserEntity usuarioEntidade = createValidUsuario();

		// vai persistir a entidade no banco de dados para testar o findById
		// ao final do testes, esta entidade será deletada
		testEntityManager.persist(usuarioEntidade);

		// buscar a entidade no banco de dados para testar o findById

		// execução do findById
		Optional<UserEntity> usuario = repository.findById(usuarioEntidade.getId());

		// validando a respota - se o objeto encontrado não é nulo
		assertThat(usuario).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		// buscar uma entidade na qual não existe no banco de dados
		Optional<UserEntity> usuario = repository.findById(1);

		// temos que verificar se o opcional não possui valores, ou seja, isPresent()
		// possui valor falso
		assertThat(usuario.isPresent()).isFalse();
	}

	@Test
	void findAllTest() {

		UserEntity usuarioEntidade = createValidUsuario();

		// salvando temporariamente o usuario no banco de dados
		testEntityManager.persist(usuarioEntidade);

		// execução
		List<UserEntity> usuarios = this.repository.findAll();

		// verificar
		assertThat(usuarios.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {

		UserEntity usuarioEntidade = createValidUsuario();

		// salvando temporariamente o usuario no banco de dados
		testEntityManager.persist(usuarioEntidade);

		// execução
		UserEntity usuariorSalvo = repository.save(usuarioEntidade);

		// validação
		assertThat(usuariorSalvo.getId()).isNotNull();
		assertThat(usuariorSalvo.getCep()).isEqualTo(usuarioEntidade.getCep());
		assertThat(usuariorSalvo.getNome()).isEqualTo(usuarioEntidade.getNome());
		assertThat(usuariorSalvo.getNumero()).isEqualTo(usuarioEntidade.getNumero());
		assertThat(usuariorSalvo.getRua()).isEqualTo(usuarioEntidade.getRua());
	}

	@Test
	void deleteById() {

		UserEntity usuarioEntidade = createValidUsuario();

		// salvando temporariamente o usuario no banco de dados
		UserEntity usuarioTemporario = testEntityManager.persist(usuarioEntidade);

		// execução
		repository.deleteById(usuarioTemporario.getId());

		// verificação
		// busquei o usuario deletado e comparei a resposta

		Optional<UserEntity> deletado = repository.findById(usuarioTemporario.getId());

		assertThat(deletado.isPresent()).isFalse();
	}

	private UserEntity createValidUsuario() {

		// instanciando o novo objeto do tipo UserEntity
		UserEntity usuarioEntidade = new UserEntity();

		// colocando valores nos atributos de UserEntity
		usuarioEntidade.setCep("04567895");
		usuarioEntidade.setNome("Evelyn Alves");
		usuarioEntidade.setNumero(3);
		usuarioEntidade.setRua("Rua de Teste");
		// usuarioEntidade.setId(1);

		// retornando este novo objeto criado
		return usuarioEntidade;
	}
}

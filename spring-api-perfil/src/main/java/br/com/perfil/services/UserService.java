package br.com.perfil.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.perfil.dtos.UserDTO;
import br.com.perfil.entities.UserEntity;
import br.com.perfil.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	// listar todos os usuarios cadastrados
	public List<UserDTO> getAll() {
		List<UserEntity> lista = repository.findAll();

		List<UserDTO> listaDTO = new ArrayList<>();

		/*
		 * for (int i = 0; i < lista.size(); i++) { UserEntity UserEntity =
		 * lista.get(i); }
		 */
		// foreach
		// 1-) Tipo da variável de cada elemento da lista
		// 2-) nome local da variável
		// - 3 lista com elementos a ser percorrido

		for (UserEntity UserEntity : lista) {

//			UserDTO dto = new UserDTO();
//			
//			dto.setId( UserEntity.getId() );
//			dto.setCep( UserEntity.getCep() );
//			//dto.setCpf( UserEntity.getCpf() );
//			dto.setNome( UserEntity.getNome() );
//			dto.setNumero( UserEntity.getNumero() );
//			dto.setRua( UserEntity.getRua() );
//			listaDTO.add(dto);
			listaDTO.add(UserEntity.toDTO());
		}

		return listaDTO;
	}

	// listar usuarios pelo id
	public UserDTO getOne(int id) {
		// optional | (if x != null)
		Optional<UserEntity> optional = repository.findById(id);
		// orElse​ - Se um valor estiver presente, retorna o valor, caso contrário,
		// retorna o valor definido no parâmetro.
		UserEntity usuario = optional.orElse(new UserEntity());

		return usuario.toDTO();
	}

	// salvar usuarios
	public UserDTO save(UserEntity usuario) {
		return repository.save(usuario).toDTO();
	}
	
	// atualizar dados do usuario
	public UserDTO update(int id, UserEntity usuario) {

		// primeiro passo é verificar se o registro existe no banco de dados

		Optional<UserEntity> optional = repository.findById(id);
		// se existe no banco de dados
		if (optional.isPresent() == true) {
			// atualiza o objeto existente
			UserEntity usuarioBD = optional.get();
			usuarioBD.setNome(usuario.getNome());
			usuarioBD.setCep(usuario.getCep());
			usuarioBD.setCpf(usuario.getCpf());
			usuarioBD.setNumero(usuario.getNumero());
			usuarioBD.setRua(usuario.getRua());

			return repository.save(usuarioBD).toDTO();
		}
		// caso contrário, retorna um objeto vazio
		else {
			return new UserEntity().toDTO();
		}
	}
	
	//deletar usuario
	public void delete(int id) {
		
		repository.deleteById(id);
		
//		try {
//			repository.deleteById(id);
//		}
//		catch (Exception e) {
//			System.out.println("Registro não existente");			
//		}
		
	}
}

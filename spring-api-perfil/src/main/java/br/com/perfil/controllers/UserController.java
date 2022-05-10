package br.com.perfil.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.perfil.dtos.UserDTO;
import br.com.perfil.services.UserService;

@RestController
@RequestMapping("usuarios")
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAll() {
		// UserService.mostrar();
		// return listaUser;
		// return UserService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getOne(@PathVariable int id) {

//		for (int i = 0; i < listaUser.size(); i++) {
//			if (listaUser.get(i).getId() == id) {
//				return listaUser.get(i);
//			}
//		}
//		int indice = findIndex(id);
//		return (indice >= 0 ? listaUser.get(indice) : null);
//		if (indice >= 0) { 
//			return listaUser.get(indice);
//		}
//		return null;

		// return UserService.getOne(id, listaUser);
		// return UserService.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(service.getOne(id));
	}

	// criar usuarios
	@PostMapping()
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO usuario) {

//		usuario.setId(contador);
//		//contador++;
//		
//		listaUser.add(usuario);
//		contador++;

		// return service.save(usuario.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(usuario.toEntity()));
	}

	// atualizar dados do usuario
	@PatchMapping("{id}")
	public ResponseEntity<UserDTO> update(@PathVariable int id, @RequestBody UserDTO usuario) {

//		for (int i = 0; i < listaUser.size(); i++) {
//			if (listaUser.get(i).getId() == id) {
//				//if (usuario.getNome() != null)
//				
//				listaUser.get(i).setNome( usuario.getNome() );
//				listaUser.get(i).setCpf( usuario.getCpf() );
//				listaUser.get(i).setRua( usuario.getRua() );
//				listaUser.get(i).setNumero( usuario.getNumero() );
//				listaUser.get(i).setCep( usuario.getCep() );
//				
//				return listaUser.get(i);
//			}
//		}

//		int indice = findIndex(id);
//		
//		if (indice >= 0) {
//			
//			listaUser.get(indice).setNome( usuario.getNome() );
//			listaUser.get(indice).setCpf( usuario.getCpf() );
//			listaUser.get(indice).setRua( usuario.getRua() );
//			listaUser.get(indice).setNumero( usuario.getNumero() );
//			listaUser.get(indice).setCep( usuario.getCep() );
//			
//			return listaUser.get(indice);
//		}
//		
//		return null;

		// return service.update(id, usuario.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, usuario.toEntity()));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
//		for (int i = 0; i < listaUser.size(); i++) {
//			if (listaUser.get(i).getId() == id) {
//				listaUser.remove(i);
//			}
//		}	
		
//		int indice = findIndex(id);
//		
//		if (indice >= 0) listaUser.remove(indice);
		//service.delete(id, listaUser);
		service.delete(id);
		
		return ResponseEntity.ok().build();
	}
}

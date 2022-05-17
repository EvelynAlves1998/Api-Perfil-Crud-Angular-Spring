package br.com.perfil.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.perfil.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	@Length(min = 11, max = 11, message = "O número de cpf é formado por onze dígitos")
	private String cpf;
	private String rua;
	private String cep;
	private int numero;

	public UserDTO toDTO() {
		ModelMapper mapper = new ModelMapper();

		UserDTO dto = mapper.map(this, UserDTO.class);

		return dto;
	}

}

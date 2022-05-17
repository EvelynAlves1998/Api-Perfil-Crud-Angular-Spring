package br.com.perfil.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.perfil.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private int id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	@Length(min = 3, max = 80, message = "O número de caracteres deve ser entre 3 e 80")
	@Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas caracteres")
	private String nome;
	private String rua;
	private String cep;
	private int numero;

	public UserEntity toEntity() {

		ModelMapper mapper = new ModelMapper();

		return mapper.map(this, UserEntity.class);
	}
}

package br.com.perfil.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.perfil.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}

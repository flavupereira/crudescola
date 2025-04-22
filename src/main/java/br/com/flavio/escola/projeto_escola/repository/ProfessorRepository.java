package br.com.flavio.escola.projeto_escola.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.flavio.escola.projeto_escola.orm.Professor;
@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}

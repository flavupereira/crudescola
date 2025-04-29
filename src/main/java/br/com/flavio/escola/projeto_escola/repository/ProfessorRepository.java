package br.com.flavio.escola.projeto_escola.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import br.com.flavio.escola.projeto_escola.orm.Professor;
@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
	
	@Query("SELECT p FROM Professor p JOIN Disciplina d ON p.id = d.id")
	List<Professor> buscaProfessorMateria();
	
	@Query(value ="select * from professor p WHERE p.data_contratacao >= :data",
			nativeQuery = true)
	List<Professor>findContratacaoMaior(LocalDate data);
	
}
package br.com.flavio.escola.projeto_escola.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.flavio.escola.projeto_escola.orm.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso,Long>{

}

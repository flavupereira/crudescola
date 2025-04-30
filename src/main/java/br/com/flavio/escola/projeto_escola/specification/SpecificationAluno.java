package br.com.flavio.escola.projeto_escola.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.flavio.escola.projeto_escola.orm.Aluno;


public class SpecificationAluno {
	
	public static Specification<Aluno> nome(String nome){
		return (root, CriteriaQuery,CriteriaBuilder) ->
		CriteriaBuilder.like(root.get("nome"), "%" + nome +"%");
		
	}
	
	
	public static Specification<Aluno> cpf(String cpf){
		return (root, CriteriaQuery,CriteriaBuilder) ->
		CriteriaBuilder.equal(root.get("cpf"), cpf);
		
	}
	
	public static Specification<Aluno> data(LocalDate dataNascimento){
		return (root, CriteriaQuery,CriteriaBuilder) ->
		CriteriaBuilder.greaterThan(root.get("dataNascimento"), dataNascimento);
		
	}

}

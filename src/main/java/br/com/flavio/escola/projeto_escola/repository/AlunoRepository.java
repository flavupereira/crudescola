package br.com.flavio.escola.projeto_escola.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.flavio.escola.projeto_escola.orm.Aluno;
@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{
	
	List<Aluno>findByNome(String nome);
	List<Aluno>findByNomeContaining(String nome);
	
    List<Aluno>findByDataNascimentoAfter(LocalDate data);

//    @Query("SELECT a  FROM ALUNO a WHERE a.nome =: nome AND a.cpf=: cpf")
//    List<Aluno>findNomeCPF(String nome , String cpf);
    
    @Query("SELECT a FROM Aluno a WHERE a.nome = :nome AND a.cpf = :cpf")
    List<Aluno> findNomeCPF(@Param("nome") String nome, @Param("cpf") String cpf);
    
    @Query("SELECT DISTINCT a FROM Aluno a JOIN Avaliacao av ON a = av.aluno WHERE  av.nota >= 7 AND av.frequencia >=20 ")
    List<Aluno>findAlunosAprovados();
    
   

}

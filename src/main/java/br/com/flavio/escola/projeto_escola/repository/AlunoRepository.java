package br.com.flavio.escola.projeto_escola.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.flavio.escola.projeto_escola.orm.Aluno;
import br.com.flavio.escola.projeto_escola.orm.AlunoProjecao;
@Repository
public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long>,
                      CrudRepository<Aluno, Long>,JpaSpecificationExecutor<Aluno>{
	
	List<Aluno>findByNome(String nome);
	List<Aluno>findByNomeContaining(String nome);
	
    List<Aluno>findByDataNascimentoAfter(LocalDate data);

//    @Query("SELECT a  FROM ALUNO a WHERE a.nome =: nome AND a.cpf=: cpf")
//    List<Aluno>findNomeCPF(String nome , String cpf);
    
    @Query("SELECT a FROM Aluno a WHERE a.nome = :nome AND a.cpf = :cpf")
    List<Aluno> findNomeCPF(@Param("nome") String nome, @Param("cpf") String cpf);
    
    @Query("SELECT DISTINCT a FROM Aluno a JOIN Avaliacao av ON a = av.aluno WHERE  av.nota >= 7 AND av.frequencia >=20 ")
    List<Aluno>findAlunosAprovados();
    
    @Query(value ="select a.id , a.nome , a.email from aluno a;",
    	   nativeQuery = true)
    List<AlunoProjecao>findAlunoEmail();
    
   
   

}

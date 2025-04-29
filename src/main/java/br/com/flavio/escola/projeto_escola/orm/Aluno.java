package br.com.flavio.escola.projeto_escola.orm;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity 
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
    @Column(unique = true)
	private String cpf;

	@Column(columnDefinition = "DATE")
	private LocalDate dataNascimento;

	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_curso",
	joinColumns = {@JoinColumn(name = "aluno_id" , referencedColumnName = "id")},
	                inverseJoinColumns = {@JoinColumn(name = "curso_id" , referencedColumnName = "id")})
	private List<Curso> curso;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable( name = "aluno_disciplina",
	joinColumns = {@JoinColumn(name = "aluno_id", referencedColumnName = "id")},
	               inverseJoinColumns = {@JoinColumn (name = "disciplina_id" ,referencedColumnName = "id")})
	private List<Disciplina> disciplina;
	
	
	@OneToMany(mappedBy = "aluno", fetch = FetchType.EAGER)
	List<Avaliacao> avaliacao;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getData_nascimento() {
		return dataNascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.dataNascimento = data_nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public List<Curso> getCurso() {
		return curso;
	}


	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}


	public List<Disciplina> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(List<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public String toString() {
		return "id= " + id +
	            ", nome='" + nome + '\'' +
	            ", cpf='" + cpf + '\'' +
	            ", data_nascimento=" + dataNascimento +
	            ", email='" + email + '\'' ;
	}
	
	

}

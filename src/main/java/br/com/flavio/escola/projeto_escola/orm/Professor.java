package br.com.flavio.escola.projeto_escola.orm;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String especializacao;
    @Column
	private LocalDate data_contratacao;
	
	@OneToMany(mappedBy = "professor")
	List<Disciplina> disciplina;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public LocalDate getData_contratacao() {
		return data_contratacao;
	}

	public void setData_contratacao(LocalDate data_contratacao) {
		this.data_contratacao = data_contratacao;
	}

	public List<Disciplina> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(List<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}

	public Long getId() {
		return id;
	}

	
	
}

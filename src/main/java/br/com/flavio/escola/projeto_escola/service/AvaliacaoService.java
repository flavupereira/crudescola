package br.com.flavio.escola.projeto_escola.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Service;

import br.com.flavio.escola.projeto_escola.orm.Aluno;
import br.com.flavio.escola.projeto_escola.orm.Avaliacao;
import br.com.flavio.escola.projeto_escola.orm.Disciplina;
import br.com.flavio.escola.projeto_escola.repository.AlunoRepository;
import br.com.flavio.escola.projeto_escola.repository.AvalicaoRepository;
import br.com.flavio.escola.projeto_escola.repository.DisciplinaRepository;

@Service
public class AvaliacaoService {
	boolean system = true;
	private final AvalicaoRepository avalicaoRepository;
	private final AlunoRepository alunoRepository;
	private final DisciplinaRepository disciplinaRepository;

	public AvaliacaoService(AvalicaoRepository avalicaoRepository, AlunoRepository alunoRepository,
			DisciplinaRepository disciplinaRepository) {

		this.alunoRepository = alunoRepository;
		this.avalicaoRepository = avalicaoRepository;
		this.disciplinaRepository = disciplinaRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("0 - Sair ");
			System.out.println("1 - Salvar ");
			System.out.println("2 - Atualizar ");
			System.out.println("3 - Exibir ");
			System.out.println("4 - Deletar ");
			System.out.println("5 - Deletar ");

			Integer action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				Atualizar(scanner);
				break;
			case 3:
				exibir();
				break;
//			case 4:
//				deletar(scanner);
//				break;
			default:
				System.out.println("Finalizado a função Avaliação ");
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scanner) {
	    scanner.nextLine(); // Limpa o buffer

	    System.out.println("Alunos Disponíveis:");
	    Aluno alunoSelecionado = alunoB(scanner);

	    System.out.println("Disciplinas Disponíveis:");
	    Disciplina disciplinaSelecionada = disciplinaB(scanner);

	    System.out.println("Digite a frequência:");
	    Integer frequencia = scanner.nextInt();

	    System.out.println("Digite a nota do aluno:");
	    Float nota = scanner.nextFloat();

	    Avaliacao avaliacao = new Avaliacao();
	    avaliacao.setAluno(alunoSelecionado);
	    avaliacao.setDisciplina(disciplinaSelecionada);
	    avaliacao.setFrequencia(frequencia);
	    avaliacao.setNota(nota);

	    avalicaoRepository.save(avaliacao);

	    System.out.println("Avaliação salva com sucesso!");
	}

	private Aluno alunoB(Scanner scanner) {
	    Iterable<Aluno> alunos = alunoRepository.findAll();
	    alunos.forEach(a -> System.out.println("ID: " + a.getId() + " - Nome: " + a.getNome()));

	    Aluno alunoSelecionado = null;
	    while (alunoSelecionado == null) {
	        System.out.println("Digite o ID do aluno:");
	        Long idAluno = scanner.nextLong();
	        Optional<Aluno> alunoOpt = alunoRepository.findById(idAluno);
	        if (alunoOpt.isPresent()) {
	            alunoSelecionado = alunoOpt.get();
	        } else {
	            System.out.println("Aluno não encontrado. Tente novamente.");
	        }
	    }
	    return alunoSelecionado;
	}

	private Disciplina disciplinaB(Scanner scanner) {
	    Iterable<Disciplina> disciplinas = disciplinaRepository.findAll();
	    disciplinas.forEach(d -> System.out.println("ID: " + d.getId() + " - Nome: " + d.getNome()));

	    Disciplina disciplinaSelecionada = null;
	    while (disciplinaSelecionada == null) {
	        System.out.println("Digite o ID da disciplina:");
	        Long idDisciplina = scanner.nextLong();
	        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(idDisciplina);
	        if (disciplinaOpt.isPresent()) {
	            disciplinaSelecionada = disciplinaOpt.get();
	        } else {
	            System.out.println("Disciplina não encontrada. Tente novamente.");
	        }
	    }
	    return disciplinaSelecionada;
	}

	private void Atualizar(Scanner scanner) {
        System.out.println("Digite o ID  a ser alterado ");
        Long idAvaliacao = scanner.nextLong();
        
        Aluno alunos = alunoB(scanner);
        
        Disciplina disciplina = disciplinaB(scanner);
        
        System.out.println("Digite a nota ");
        Float nota = scanner.nextFloat();
        System.out.println("Digite a frequencia ");
        Integer frequencia = scanner.nextInt();
        
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(idAvaliacao);
        avaliacao.setAluno(alunos);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setNota(nota);
        avaliacao.setFrequencia(frequencia);
        
        avalicaoRepository.save(avaliacao);
	}

	private void exibir() {
		Iterable<Avaliacao> buscaDisciplina = avalicaoRepository.findAll();
		buscaDisciplina.forEach(d -> System.out.println(d));
	}

	
}

package br.com.flavio.escola.projeto_escola.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.flavio.escola.projeto_escola.orm.Aluno;
import br.com.flavio.escola.projeto_escola.orm.Professor;
import br.com.flavio.escola.projeto_escola.repository.AlunoRepository;
import br.com.flavio.escola.projeto_escola.repository.ProfessorRepository;

@Service
public class RelatorioService {

	private Boolean system = true;

	DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final AlunoRepository alunoRepository;
	private final ProfessorRepository professorRepository;

	public RelatorioService(AlunoRepository alunoRepository , ProfessorRepository professorRepository) {

		this.alunoRepository = alunoRepository;
		this.professorRepository = professorRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual opção Deseja executar ");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar Aluno por  nome");
			System.out.println("2 - Buscar Aluno a partir de uma data ");
			System.out.println("3 - Buscar Aluno por Nome e CPF ");
			System.out.println("4 - Buscar AlunoS provados com media maior 7 ");
			System.out.println("5 - Buscar professor por Materia");
			System.out.println("6 - Buscar professor data Contratação");

			int action = scanner.nextInt();

			scanner.nextLine();

			switch (action) {
			case 1:
				buscaAlunoNome(scanner);
				break;
			case 2:
				buscaDataNascimentoAlunoAposData(scanner);
				break;
			case 3:
				buscaAlunoPorCPF(scanner);
				break;
			case 4:
				buscaAlunosAprovados();
				break;
			case 5:
				buscaProfessorMateria();
				break;
			case 6:
				BusacaProfessorDataContratacao(scanner);
				break;
			default:
				system = false;
				break;

			}
		}

	}
	
	
	private void BusacaProfessorDataContratacao(Scanner scanner) {
		System.out.println("Digite a data que deseja Pequisar");
		String data = scanner.nextLine();
		LocalDate localDate = LocalDate.parse(data, fomatter);
		
		List<Professor> lista = professorRepository.findContratacaoMaior(localDate);
		lista.forEach(System.out::println);
		
	}

	private void buscaProfessorMateria() {
		List<Professor> lista = professorRepository.buscaProfessorMateria();
		lista.forEach(p -> {
			          p.getDisciplina().forEach(disciplina ->{
			        	System.out.println("Nome :" + p.getNome() + " - " +
			            "Disciplina :" + disciplina.getNome());  
			          });
		});
		
	}

	private void buscaAlunosAprovados() {
		List<Aluno> lista = alunoRepository.findAlunosAprovados();
		lista.forEach(a -> {
			if (a.getAvaliacao() != null && !a.getAvaliacao().isEmpty()) {
				a.getAvaliacao().forEach(avaliacao -> {
					System.out.println("Nome : " + a.getNome() + " - Nota : " + avaliacao.getNota() +
							" - " + "Frequencia :" + avaliacao.getFrequencia());
				});
			}
		});

	}

	private void buscaAlunoPorCPF(Scanner scanner) {
		System.out.println("Digite o nome e nome do Aluno ");
		String nome = scanner.nextLine();

		System.out.println("Digite o nome e CPF do Aluno");
		String cpf = scanner.nextLine();

		List<Aluno> lista = alunoRepository.findNomeCPF(nome, cpf);
		lista.forEach(e -> System.out.println(e));

	}

	private void buscaDataNascimentoAlunoAposData(Scanner scanner) {
		System.out.println("Digite a partir da data que deseja pesquisar");
		String data = scanner.nextLine();
		LocalDate localDate = LocalDate.parse(data, fomatter);
		List<Aluno> lista = alunoRepository.findByDataNascimentoAfter(localDate);
		lista.forEach(e -> System.out.println(e));

	}

	private void buscaAlunoNome(Scanner scanner) {
		System.out.println("Digite o nome do Aluno para pesquisa ");
		String nome = scanner.nextLine();

		List<Aluno> lista = alunoRepository.findByNome(nome);
		lista.forEach(e -> System.out.println(e));

	}

}

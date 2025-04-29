package br.com.flavio.escola.projeto_escola.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.flavio.escola.projeto_escola.orm.Disciplina;
import br.com.flavio.escola.projeto_escola.orm.Professor;
import br.com.flavio.escola.projeto_escola.repository.DisciplinaRepository;
import br.com.flavio.escola.projeto_escola.repository.ProfessorRepository;

@Service
public class ProfessorService {
	Boolean system = true;
	private final ProfessorRepository professorRepository;
	private final DisciplinaRepository disciplinaRepository;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public ProfessorService(ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository) {
		this.professorRepository = professorRepository;
		this.disciplinaRepository = disciplinaRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar ");

			Integer action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				System.out.println("Finalizado a função professor !!!!");
				system = false;
				break;

			}
		}
	}

	private void salvar(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Digite o nome ");
		String nome = scanner.nextLine();

		System.out.println("Digite a espcialização");
		String espcializacao = scanner.nextLine();

		System.out.println("Digite a data de contratação");
		String dataContratacao = scanner.nextLine();

		List<Disciplina> disciplinas = disciplina(scanner);

		Professor professor = new Professor();
		professor.setNome(nome);
		professor.setEspecializacao(espcializacao);
		professor.setData_contratacao(LocalDate.parse(dataContratacao, formatter));
		professor.setDisciplina(disciplinas);
		
		professorRepository.save(professor);

	}

	private List<Disciplina> disciplina(Scanner scanner) {
		Boolean isTrue = true;

		List<Disciplina> disciplinas = new ArrayList<>();

		Iterable<Disciplina> buscaDisciplinas = disciplinaRepository.findAll();
		buscaDisciplinas.forEach(e -> System.out.println("ID :" + e.getId() + " " + "Nome :" + e.getNome()));

		while (isTrue) {
			System.out.println("Digite o ID da disciplina (Para sair digite 0) ");
			Long disciplinaId = scanner.nextLong();

			if (disciplinaId != 0) {
				Optional<Disciplina> disciplina = disciplinaRepository.findById(disciplinaId);
				if (disciplina.isPresent()) {
					disciplinas.add(disciplina.get());
				} else {
					System.out.println("Disciplina não encotrada " + disciplinaId);
				}
			} else {
				isTrue = false;
			}

		}
		return disciplinas;
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o ID para ser alterado");
		Long id = scanner.nextLong();
		
		System.out.println("Digite o nome ");
		String nome = scanner.nextLine();

		System.out.println("Digite a espcialização");
		String espcializacao = scanner.nextLine();

		System.out.println("Digite a data de contratação");
		String dataContratacao = scanner.nextLine();

		System.out.println("Dados atualizados !!!");

	}

	private void visualizar() {
		Iterable<Professor> professores = professorRepository.findAll();
		professores.forEach(p -> System.out.println(p));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Digite o id para ser excluido ");
		Long id = scanner.nextLong();
		
		professorRepository.deleteById(id);
		System.out.println("Professor excluido !!!!");

	}

}

package br.com.flavio.escola.projeto_escola.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.flavio.escola.projeto_escola.orm.Aluno;
import br.com.flavio.escola.projeto_escola.orm.Curso;
import br.com.flavio.escola.projeto_escola.orm.Disciplina;
import br.com.flavio.escola.projeto_escola.repository.AlunoRepository;
import br.com.flavio.escola.projeto_escola.repository.CursoRepository;
import br.com.flavio.escola.projeto_escola.repository.DisciplinaRepository;

@Service
public class AlunoService {
	private Boolean system = true;
	private final AlunoRepository alunoRepository;
	private final CursoRepository cursoRepository;
	private final DisciplinaRepository disciplinaRepository;

	DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository,
			DisciplinaRepository disciplinaRepository) {

		this.alunoRepository = alunoRepository;
		this.cursoRepository = cursoRepository;
		this.disciplinaRepository = disciplinaRepository;

	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar ");
			

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				System.out.println("Saindo da Função Aluno !!!");
				system = false;
				break;

			}
		}

	}

	private void salvar(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Digite o Nome");
		String nome = scanner.nextLine();

		System.out.println("Digite o cpf");
		String cpf = scanner.nextLine();

		System.out.println("Digite o email");
		String email = scanner.nextLine();

		System.out.println("Digite a data de Nascimento");
		String dataNacimento = scanner.nextLine();

		// System.out.println("Digite o ID Curso");
		List<Curso> cursos = curso(scanner);
		// Long cursoId = scanner.nextLong();

		List<Disciplina> disciplinas = disciplina(scanner);

		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setCpf(cpf);
		aluno.setEmail(email);
		LocalDate localDate = LocalDate.parse(dataNacimento, fomatter);
		aluno.setData_nascimento(localDate);
		// Optional<Curso> CursoId = cursoRepository.findById(cursoId);
		aluno.setCurso(cursos);
		aluno.setDisciplina(disciplinas);

		alunoRepository.save(aluno);
		System.out.println("Salva");

	}

	private List<Curso> curso(Scanner scanner) {
		Boolean isTrue = true;

		List<Curso> curos = new ArrayList<>();
		Iterable<Curso> buscaCursos = cursoRepository.findAll();
		System.out.println("Cursos Disponiveis: ");

		buscaCursos.forEach(c -> System.out.println("ID: " + c.getId() + " " + "Nome :" + c.getNome()));

		while (isTrue) {
			System.out.println("Diagite a Disciplinaid (Para sair digite 0)");
			Long cursoId = scanner.nextLong();

			if (cursoId != 0) {
				Optional<Curso> curso = cursoRepository.findById(cursoId);
				if (curso.isPresent()) {
					curos.add(curso.get());
				} else {
					System.out.println("Disciplina não encontrada com ID: " + cursoId);
				}
			} else {
				isTrue = false;
			}
		}

		return curos;
	}

	private List<Disciplina> disciplina(Scanner scanner) {
		Boolean isTrue = true;

		List<Disciplina> disciplinas = new ArrayList<>();

		Iterable<Disciplina> buscaDisciplina = disciplinaRepository.findAll();

		System.out.println("Disciplinas disponíveis:");
		buscaDisciplina.forEach(d -> System.out.println("ID: " + d.getId() + " " + "Nome :" + d.getNome()));

		while (isTrue) {
			System.out.println("Diagite a Disciplinaid (Para sair digite 0)");
			Long discilinaId = scanner.nextLong();

			if (discilinaId != 0) {
				Optional<Disciplina> disciplina = disciplinaRepository.findById(discilinaId);
				if (disciplina.isPresent()) {
					disciplinas.add(disciplina.get());
				} else {
					System.out.println("Disciplina não encontrada com ID: " + discilinaId);
				}

			} else {
				isTrue = false;
			}
		}
		return disciplinas;
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id");
		Long id = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Digite o Nome");
		String nome = scanner.nextLine();

		System.out.println("Digite o CPF");
		String cpf = scanner.nextLine();

		System.out.println("Digite o email");
		String email = scanner.nextLine();

		System.out.println("Digite a data de nascimento");
		String dataNascimento = scanner.nextLine();

		Aluno aluno = new Aluno();
		aluno.setId(id);
		aluno.setNome(nome);
		aluno.setCpf(cpf);
		aluno.setEmail(email);
		aluno.setData_nascimento(LocalDate.parse(dataNascimento, fomatter));

		alunoRepository.save(aluno);
		System.out.println("Alterado....");

	}

	private void deletar(Scanner scanner) {
		System.out.println("Digite o ID a ser deletado");
		Long alunoId = scanner.nextLong();
		alunoRepository.deleteById(alunoId);
		System.out.println("Aluno deletado com sucesso !!!");

	}

	private void visualizar(Scanner scanner) {
		Iterable<Aluno> alunos = alunoRepository.findAll();
		alunos.forEach(aluno -> System.out.println(aluno));
	}
	
	

}

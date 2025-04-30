package br.com.flavio.escola.projeto_escola.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.convert.Jsr310Converters.LocalDateTimeToDateConverter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.flavio.escola.projeto_escola.orm.Aluno;
import br.com.flavio.escola.projeto_escola.repository.AlunoRepository;
import br.com.flavio.escola.projeto_escola.specification.SpecificationAluno;

@Service
public class RelatorioAlunoDinamico {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private AlunoRepository alunoRepository;

	public RelatorioAlunoDinamico(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public void incial(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.nextLine();

		if (nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}

		System.out.println("Digite o cpf");

		String cpf = scanner.nextLine();

		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}

		System.out.println("Digite a data de matricula ");

		String data = scanner.nextLine();
		LocalDate dataMatricula;

		if (data.equalsIgnoreCase("NULL")) {
			dataMatricula = null;
		} else {
			dataMatricula = LocalDate.parse(data, formatter);
		}

		List<Aluno> alunos = alunoRepository.findAll(Specification.where(SpecificationAluno.nome(nome))
				.or(SpecificationAluno.cpf(cpf)).or(SpecificationAluno.data(dataMatricula)));
		alunos.forEach(System.out::println);

	}

}

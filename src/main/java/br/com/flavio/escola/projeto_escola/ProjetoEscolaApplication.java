package br.com.flavio.escola.projeto_escola;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.flavio.escola.projeto_escola.service.AlunoService;
import br.com.flavio.escola.projeto_escola.service.AvaliacaoService;
import br.com.flavio.escola.projeto_escola.service.ProfessorService;


@SpringBootApplication
public class ProjetoEscolaApplication implements CommandLineRunner{

	private Boolean system = true;
	private final AlunoService alunoService;
	private final ProfessorService professorService;
	private final AvaliacaoService avaliacaoService;
	
	public ProjetoEscolaApplication(AlunoService alunoService,
	AvaliacaoService avaliacaoService,ProfessorService professorService) {
		
		this.alunoService = alunoService;
		this.professorService = professorService;
		this.avaliacaoService = avaliacaoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEscolaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qula função Deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Aluno");
			System.out.println("2 - Professor");
			System.out.println("3 - Avaliação");
	//		System.out.println("4 - Realtorios");
	//		System.out.println("4 - Realtorios Dinamicos");
			
			Integer function = scanner.nextInt();
			
			switch (function) {
			case 1: 
				alunoService.inicial(scanner);
				break;
			case 2: 
				professorService.inicial(scanner);
				break;
			case 3: 
				avaliacaoService.inicial(scanner);
				break;
			
			default:
				System.out.println("Finalizado !!!");
				system = false;
				break;
			}
		}
		
		
	}

}

package br.com.flavio.escola.projeto_escola;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.flavio.escola.projeto_escola.service.AlunoService;
import br.com.flavio.escola.projeto_escola.service.AvaliacaoService;
import br.com.flavio.escola.projeto_escola.service.ProfessorService;
import br.com.flavio.escola.projeto_escola.service.RelatorioAlunoDinamico;
import br.com.flavio.escola.projeto_escola.service.RelatorioService;


@SpringBootApplication
public class ProjetoEscolaApplication implements CommandLineRunner{

	private Boolean system = true;
	private final AlunoService alunoService;
	private final ProfessorService professorService;
	private final AvaliacaoService avaliacaoService;
	private final RelatorioService relatorioService;
	private final RelatorioAlunoDinamico relatorioAlunoDinamico;
	
	public ProjetoEscolaApplication(AlunoService alunoService,
	AvaliacaoService avaliacaoService,ProfessorService professorService,
	RelatorioService relatorioService,
	RelatorioAlunoDinamico relatorioAlunoDinamico ) {
		
		this.alunoService = alunoService;
		this.professorService = professorService;
		this.avaliacaoService = avaliacaoService;
		this.relatorioService = relatorioService;
		this.relatorioAlunoDinamico = relatorioAlunoDinamico;
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
	     	System.out.println("4 - Realtorios");
	     	System.out.println("5 - Realtorios Dinamicos");
			
	     	  Integer function = Integer.parseInt(scanner.nextLine());
			
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
			case 4: 
				relatorioService.inicial(scanner);
				break;
			case 5: 
				relatorioAlunoDinamico.incial(scanner);
				break;
			default:
				System.out.println("Finalizado !!!");
				system = false;
				break;
			}
		}
		
		
	}

}

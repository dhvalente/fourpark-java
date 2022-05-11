package br.com.fourpark;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Application {

	private static Scanner scanner = new Scanner(System.in);
	private static Estacionamento estacionamento = new Estacionamento();

	public static void main(String[] args) {
		menuPrincipal();
	}

	public static void menuPrincipal() {
		int opcao = 1;
		estacionamento.criarVagas(50);
		while (opcao != 0) {
	System.out.println("\n\n### FourPark - Sistema Comercial de Controle de Estacionamento ###");
	System.out.println("\n                   ==========================================");
	System.out.println("                  ||        1 - Registrar entrada           ||");
	System.out.println("                  ||        2 - Registrar saida             ||");
	System.out.println("                  ||        3 - Ver vagas disponíveis       ||");
	System.out.println("                  ||        4 - Ver vagas ocupadas          ||");
	System.out.println("                  ||        5 - Faturamento Total           ||");
	System.out.println("                  ||        6 - Faturamento por Dia         ||");
	System.out.println("                  ||        7 - Faturamento por Vaga        ||");
	System.out.println("                  ||        0 - Sair                        ||");
	System.out.println("                   ==========================================\n");
	System.out.print("Insira Uma opção:");

	System.out.print("\n");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 0:
				System.out.println("\nSistema encerrado");
				break;
			case 1:
				Proprietario proprietario = cadastroProprietario();
				Veiculo veiculo = cadastroVeiculo(proprietario);
				registarEntrada(veiculo);

				break;
			case 2:
				registarSaidaVaga();		
				break;
			case 3:
				estacionamento.vagasDisponiveis();
				break;
			case 4:
				estacionamento.vagasOcupadas();
				break;
			case 5:
				System.out.println("Método de ver faturamento total aqui...");
				break;
			case 6:
				System.out.println("Método de ver faturamento por dia aqui...");
				break;
			case 7:
				System.out.println("Método de ver faturamento por vaga aqui...");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
//////////////////////////////////////////////////////////////////////

	public static Proprietario cadastroProprietario() {
		System.out.print("Informe o nome do proprietário");
		String nome = scanner.next();
		System.out.print("Informe o cpf do proprietário");
		String cpf = scanner.next();
		System.out.print("Informe o contato do proprietário");
		String contato = scanner.next();
		return new Proprietario(nome, cpf, contato);
	}

	public static Veiculo cadastroVeiculo(Proprietario proprietario) {
		System.out.print("Informe a placa do veiculo");
		String placa = scanner.next();
		System.out.print("Informe o modelo do veiculo");
		String modelo = scanner.next();
		System.out.print("Informe o tipo do veiculo");
		String tipo = scanner.next();
		return new Veiculo(placa, modelo, tipo, proprietario);

	}

	public static void registarEntrada(Veiculo veiculo) {
		System.out.print("Informe a hora de entrada");
		String horaEntrada = scanner.next();
		DateTimeFormatter inputFormatHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime horaEntradaFormatada = LocalTime.parse(horaEntrada, inputFormatHora);

		System.out.print("Informe a data de entrada");
		String diaEntrada = scanner.next();
		DateTimeFormatter inputFormatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataEntradaFormatada = LocalDate.parse(diaEntrada, inputFormatData);

		System.out.print("Informe o valor da hora");
		Double valorEntrada = scanner.nextDouble();

		System.out.print("Informe o numero da vaga");
		Integer x = scanner.nextInt();
		Vaga vaga = estacionamento.vagas.get(x);

		Ticket ticket = new Ticket(horaEntradaFormatada, dataEntradaFormatada, valorEntrada, vaga, veiculo);
		estacionamento.registraEntrada(ticket);
	}

	public static void registarSaidaVaga() {
		System.out.print("Informe a hora de saida");
		String horaSaida = scanner.next();
		DateTimeFormatter inputFormatHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime horaSaidaFormatada = LocalTime.parse(horaSaida, inputFormatHora);
		System.out.print("Informe o numero da vaga");
		Integer numVaga = scanner.nextInt();				
		
		estacionamento.registraSaidaVaga(numVaga, horaSaidaFormatada);

	}

}
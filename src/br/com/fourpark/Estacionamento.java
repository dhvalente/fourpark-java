package br.com.fourpark;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
public class Estacionamento {

	private Ticket ticket;
	public List<Vaga> vagas;
	public List<Ticket> tickets;

	public Estacionamento() {
		this.vagas = new ArrayList<>();
		this.tickets = new ArrayList<>();
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void registraEntrada(Ticket ticket) {
		if (ticket.getVaga().getIsOcupado()) {
			System.out.println("Esta vaga já está ocupada, por favor selecione uma disponível.");

		} else {
			ticket.getVaga().setIsOcupado(true);
			this.tickets.add(ticket);
			this.ticket = ticket;

		}
	}

	public void registraSaidaVaga(Integer posicaoVaga, LocalTime horaSaida) {
		for (Vaga vaga : vagas) {
			if (vaga.getPosicao().equals(posicaoVaga)) {
				vaga.setIsOcupado(false);
				Double intervalo = Double.valueOf(ticket.getHoraEntrada().until(horaSaida, ChronoUnit.MINUTES));
				System.out.println("R$ " + (intervalo * (ticket.getValorHora() / 60)));
			}
		}
	}

	public void registraSaidaPlaca(String placaVeiculo, LocalTime horaSaida) {
		for (Ticket ticket : tickets) {
			if (ticket.getVeiculo().getPlaca().equals(placaVeiculo)) {
				ticket.getVaga().setIsOcupado(false);
				Double intervalo = Double.valueOf(ticket.getHoraEntrada().until(horaSaida, ChronoUnit.MINUTES));
				System.out.println("R$ " + (intervalo * (ticket.getValorHora() / 60)));
			}
		}
	}

	public Ticket getTicket(Integer numTicket) {
		return tickets.get(numTicket);
	}

	public String vagasDisponiveis() {
		for (Vaga vaga : vagas) {
			if (!vaga.getIsOcupado()) {
				System.out.println(" " + vaga + " ");
			}
		}
		return null;

	}

	public String vagasOcupadas() {
		for (Vaga vaga : vagas) {
			if (vaga.getIsOcupado()) {
				System.out.println(vaga);
			} else {
				System.out.println("Todas as vagas estão livres!");
				break;
			}
		}
		return null;

	}

	public void criarVagas(Integer qtdVagas) {
		vagas = new ArrayList<Vaga>();
		for (int x = 0; x <= (qtdVagas - 1); x++) {
			Vaga vaga = new Vaga(x);
			this.vagas.add(vaga);
		}
	}

}

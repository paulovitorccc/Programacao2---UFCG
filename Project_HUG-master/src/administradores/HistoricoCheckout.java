package administradores;

import java.time.LocalDate;

public class HistoricoCheckout {

	LocalDate dataCheckout;
	String nomeHospede;
	String numQuarto;
	double totalPago;
	
	public HistoricoCheckout(LocalDate localDate, String nomeHospede, String numQarto,
			double totalPago){
		
		this.dataCheckout = localDate;
		this.nomeHospede = nomeHospede;
		this.numQuarto = numQarto;
		this.totalPago = totalPago;
	}

	public LocalDate getDataCheckout() {
		return dataCheckout;
	}

	public void setDataCheckout(LocalDate dataCheckout) {
		this.dataCheckout = dataCheckout;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}

	public String getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(String numQuarto) {
		this.numQuarto = numQuarto;
	}

	public double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}
	
	
}

package it.ewallet.entity;

import java.time.LocalDate;

public class Movimento {
	
	private String iban;
	private String data = LocalDate.now().toString();
	private Tipo tipo;
	private double importo;
	
	
	public String getIban() {
		return iban;
	}
	public String getData() {
		return data;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public double getImporto() {
		return importo;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	

}

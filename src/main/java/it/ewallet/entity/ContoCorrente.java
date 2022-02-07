package it.ewallet.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ContoCorrente {
	
	private String iban;
	private String intestario;
	private String dataCreazioneConto = LocalDateTime.now().toString();
	private double saldo;
	
	public String getIban() {
		return iban;
	}
	public String getIntestario() {
		return intestario;
	}
	public String getDataCreazioneConto() {
		return dataCreazioneConto;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public void setIntestario(String intestario) {
		this.intestario = intestario;
	}
	public void setDataCreazioneConto() {
		this.dataCreazioneConto = LocalDate.now().toString();
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	

}

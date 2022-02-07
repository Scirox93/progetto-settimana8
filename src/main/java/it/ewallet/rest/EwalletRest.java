package it.ewallet.rest;

import java.util.ArrayList;


import java.util.List;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.ewallet.entity.ContoCorrente;
import it.ewallet.entity.Movimento;
import it.ewallet.entity.Tipo;

@Path("/ewallet")
public class EwalletRest {

	private static ArrayList<ContoCorrente> contocorrente =  new ArrayList<>();
	private static ArrayList<Movimento> movimento = new ArrayList<>();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertConto(ContoCorrente c) {
		for (ContoCorrente conto : contocorrente) {
			if(conto.getIban().equals(c.getIban())) {
				return Response.status(404).entity("ATTENZIONE!!!Conto corrente gia esistente").build();
			}
		}
		contocorrente.add(c);
		return Response.status(200).entity("Conto corrente inserito con successo").build();
	}



	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContoCorrente> getAllContoCorrente(){
		return contocorrente;
	}
	@GET
	@Path("/{iban}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContoCorrente getConto(@PathParam("iban") String iban) {
		for (ContoCorrente conto : contocorrente) {
			if(conto.getIban().equals(iban)) {
				return conto;
			}
		}
		return null;
	}
	
	@GET
	@Path("/allmovimento")
	@Produces(MediaType.APPLICATION_JSON)
	public  ArrayList<Movimento> getAllContoMovimento(){
		return movimento;
	}
	
	


	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContoCorrente(ContoCorrente c) {
		for (ContoCorrente conto : contocorrente) {
			if(conto.getIban().equals(c.getIban())) {
				int posizione = contocorrente.lastIndexOf(conto);
				contocorrente.set(posizione, c);
				return Response.status(200).entity("conto corrente aggiornato con successo").build();
			}

		}
		return Response.status(404).entity("IMPOSSIBILE AGGIONARE IL CONTO CORRENTE: iban non valido").build();
	}

	@DELETE
	@Path("/{iban}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeContoCorrente( @PathParam("iban") String iban ) {
		for (ContoCorrente conto : contocorrente) {
			if(conto.getIban().equals(iban)) {
				contocorrente.remove(conto);
				 return Response.status(200).entity("Rimozione avvenuta con successo").build();
			}
			
		}
		return Response.status(404).entity("ATTENZIONE!!! Impossibile rimuovere il Conto corrente, iban non presente").build();
	}


	@POST
	@Path("/movimento")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response movimento(Movimento m) {
		if(m.getTipo().equals(Tipo.VERSAMENTO)) {
			for (ContoCorrente conto : contocorrente) {
				if(m.getIban().equals(conto.getIban())) {
					if(m.getImporto()>0) {
						double nuovoSaldo = conto.getSaldo()+m.getImporto();
						conto.setSaldo(nuovoSaldo);
						movimento.add(m);
						 return Response.status(200).entity("Versamento effettuato").build();
					}
				}

			}
		}
		if( m.getTipo().equals(Tipo.PRELIEVO)){
			for (ContoCorrente conto : contocorrente) {
				if(conto.getIban().equals(m.getIban())) {
					if(m.getImporto()>0 && m.getImporto() < conto.getSaldo()) {
						double nuovoSaldo = conto.getSaldo()-m.getImporto();
						conto.setSaldo(nuovoSaldo);
						movimento.add(m);
						 return Response.status(200).entity("prelievo effetuato effettuato").build();
					}
				}

			}
			

		}

		return Response.status(404).entity("ATTENZIONE!!! dati non iseriti correttamente!!").build();
	}































}

package br.com.next.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.model.Atendimento;
import br.com.next.repositories.IRepositoryAtendimento;

@RestController
@RequestMapping("/Atendimento")
public class ControllerAtendimento extends ControllerGenerico<Atendimento> {

	@Autowired
	private IRepositoryAtendimento repository;

	@Override
	protected IRepositoryAtendimento getService() {
		return repository;
	}

	@RequestMapping(value = "/trazerUltimoAtendimento", method = RequestMethod.GET)
	public ResponseEntity<Atendimento> trazerUltimoAtendimento() {
		Atendimento atendimento = repository.trazerUltimoAtendimento();
		if (atendimento == null) {
			return new ResponseEntity<Atendimento>(atendimento, HttpStatus.PRECONDITION_REQUIRED);
		}
		return new ResponseEntity<Atendimento>(atendimento, HttpStatus.OK);
	}

	@RequestMapping(value = "/trazerUltimosTresAtendimento", method = RequestMethod.GET)
	public ResponseEntity<List<Atendimento>> trazerUltimosTresAtendimento() {
		List<Atendimento> atendimentos = repository.trazerUltimosAtendimentos();
		List<Atendimento> ultimosTres = new ArrayList<>();
		int contador = 0;
		if (atendimentos != null) {
			for (Atendimento atendimento : atendimentos) {
				ultimosTres.add(atendimento);
				contador++;
				if (contador == 3)
					break;
			}
		}
		return new ResponseEntity<List<Atendimento>>(ultimosTres, HttpStatus.OK);
	}

	@RequestMapping(value = "/calcularTempoMedioEspera", method = RequestMethod.GET)
	public ResponseEntity<Long> calcularTempoMedioEspera() {
		Timestamp horaAtual = new Timestamp(new Date().getTime());
		Atendimento atendimento = repository.trazerPrimeiroAtendimento();
		if (atendimento == null) {
			return new ResponseEntity<Long>(0L, HttpStatus.PRECONDITION_REQUIRED);
		}
		
		long diferenca = horaAtual.getTime() - atendimento.getDataAtendido().getTime();//as given
		long diferencaMinutos = TimeUnit.MILLISECONDS.toMinutes(diferenca); 
		
		int qtdAtendimentos = repository.trazerUltimosAtendimentos().size();
		Long tempoTotal = diferencaMinutos / qtdAtendimentos;
		tempoTotal = tempoTotal * qtdAtendimentos;
		if (tempoTotal == 0) {
			return new ResponseEntity<Long>(0L, HttpStatus.PRECONDITION_REQUIRED);
		}
		return new ResponseEntity<Long>(tempoTotal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/quantidadeAtendimentosPorGuiche", method = RequestMethod.GET)
	public ResponseEntity<List<Object[]>> quantidadeAtendimentosPorGuiche() {
		List<Object[]> atendimentos = repository.trazerAtendimentosPorGuiche();
		if (atendimentos == null) {
			return new ResponseEntity<List<Object[]>>(atendimentos, HttpStatus.PRECONDITION_REQUIRED);
		}
		return new ResponseEntity<List<Object[]>>(atendimentos, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

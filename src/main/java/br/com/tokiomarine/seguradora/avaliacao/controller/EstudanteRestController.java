package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteServiceImpl;


@RestController
@RequestMapping(value="/estudante")
public class EstudanteRestController {
	
	@Autowired
	private EstudanteServiceImpl service;

	// TODO IMPLEMENTAR CADASTRO DE ESTUDANTES (POST)
	@RequestMapping(value="/{name}/{email}/{telefone}/{matricula}/{curso}", method = RequestMethod.POST)
	public ResponseEntity<?> addEstudante(@PathVariable String name,@PathVariable String email,
			@PathVariable String telefone, @PathVariable int matricula, @PathVariable String curso) {
		try {
			Estudante novo = new Estudante();
			novo.setEmail(email);
			novo.setName(name);
			novo.setTelefone(telefone);
			novo.setMatricula(matricula);
			novo.setCurso(curso);
			
			service.cadastrarEstudante(novo);
			
			return ResponseEntity.ok().body("cadatrado");
		}catch (Exception e) {
			return ResponseEntity.ok().body("erro no cadatrado");
		}
	}

	// TODO IMPLEMENTAR ATUALIZACAO DE ESTUDANTES (PUT)
	@RequestMapping(value="/{id}/{name}/{email}/{telefone}/{matricula}/{curso}", method = RequestMethod.PUT)
	public ResponseEntity<?> editEstudante(@PathVariable String name,@PathVariable String email,
			@PathVariable String telefone, @PathVariable int id, @PathVariable int matricula,
			@PathVariable String curso) {
		try {
			Estudante novo = new Estudante();
			novo.setId(id);
			novo.setEmail(email);
			novo.setName(name);
			novo.setTelefone(telefone);
			novo.setMatricula(matricula);
			novo.setCurso(curso);
			
			service.atualizarEstudante(novo);
			
			return ResponseEntity.ok().body("cadatro atualizado");
		}catch (Exception e) {
			return ResponseEntity.ok().body("erro na atualizacao: " + e);
		}
	}


	// TODO IMPLEMENTAR A LISTAGEM DE ESTUDANTES (GET)
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findEstudanteId(@PathVariable Integer id) {
		try {
			Estudante obj = service.buscarEstudante(id);
			return ResponseEntity.ok().body(obj);
		}catch (Exception e) {
			return ResponseEntity.ok().body("erro na pesquisa");
		}
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<?> findAllEstudante() {
		try {
			List<Estudante> obj = service.buscarEstudantes();
			return ResponseEntity.ok().body(obj);
		}catch (Exception e) {
			return ResponseEntity.ok().body("erro na pesquisa");
		}
	}

	// TODO IMPLEMENTAR A EXCLUSÃO DE ESTUDANTES (DELETE)
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delEstudante(@PathVariable Integer id) {
		try {
			service.deletarEstudante(id);
			return ResponseEntity.ok().body("deletado");
		}catch (Exception e) {
			return ResponseEntity.ok().body("erro ao deletar");
		}
	}
}

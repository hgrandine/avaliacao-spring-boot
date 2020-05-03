package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

	@Autowired
	EstudandeService service;

	@GetMapping("criar")
	public String iniciarCastrado(Estudante estudante) {
		try {
			service.cadastrarEstudante(estudante);
			return "estudante cadastrado";
		}catch (Exception e) {
			return "erro no cadastro";
		}
		
		
	}

	@GetMapping("listar")
	public List<Estudante> listarEstudantes() {
		return service.buscarEstudantes();
	}

	@PostMapping("add")
	public String adicionarEstudante(@Valid Estudante estudante) {
		try {
			service.cadastrarEstudante(estudante);
		}catch (Exception e) {
			return "erro ao adicionar";
		}
		return "adicionado";
	}

	@GetMapping("editar/{id}")
	public String exibirEdicaoEstudante(int id) {
		try {
			Estudante estudante = service.buscarEstudante(id);
			service.atualizarEstudante(estudante);
			return "estudante atualizado";
		}catch (Exception e) {
			return "erro ao atualizar";
		}
		
	}

	@PostMapping("atualizar/{id}")
	public String atualizarEstudante(@PathVariable("id") long id, @Valid Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// estudante.setId(id);
			return "atualizar-estudante";
		}

		service.atualizarEstudante(estudante);

		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@GetMapping("apagar/{id}")
	public String apagarEstudante(@PathVariable("id") long id, Model model) {
		// TODO IMPLEMENTAR A EXCLUSAO DE ESTUDANTES
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}
}

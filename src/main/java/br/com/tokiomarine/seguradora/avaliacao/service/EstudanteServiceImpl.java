package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@Service
public class EstudanteServiceImpl implements EstudandeService {

	@Autowired
	EstudanteRepository repository;

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		Optional<Estudante> atualiza = repository.findById(estudante.getId());
		
		if(atualiza.isPresent()) {
			repository.save(estudante);
		}else {
			throw new IllegalArgumentException("Estudante nao encontrado:" + estudante.getId());
		}
	}

	@Override
	public List<Estudante> buscarEstudantes() {
		return repository.findAll();
	}

	@Override
	public Estudante buscarEstudante(int id) {
		return repository.findById(id).orElseThrow();
	}
	
	@Override
	public boolean deletarEstudante(int id) {
		try {
			Estudante deleta = this.buscarEstudante(id);
			repository.delete(deleta);
			return true;
		}catch (Exception e) {
			throw new IllegalArgumentException("Estudante nao encontrado:" + id);
		}
	}

}

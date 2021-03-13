package br.com.alura.forum.controller;

import java.net.URI;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.form.TopicoUpdate;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping(value="/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	@Transactional
	@Cacheable(value = "listaDeTopicos")
	public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso, Pageable paginacao) {
		
		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.converte(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);	
			return TopicoDTO.converte(topicos);
		}
		
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, 
			@RequestBody @Valid TopicoUpdate topicoUpdate) {
	
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if (optional.isPresent()) {
			Topico topico = topicoUpdate.atualizar(id,topicoRepository);

			return ResponseEntity.ok(new TopicoDTO(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> Create(@RequestBody @Valid TopicoForm topicoForm, 
			UriComponentsBuilder uriBuilder) {
		
		Topico topico = topicoForm.converter(cursoRepository);
		
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> Delete(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if (optional.isPresent()) {
			
			topicoRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}

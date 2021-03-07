package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.CursoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;

@RestController
public class CursoController {
	@Autowired
	private CursoRepository cursoRepository;
	
	@RequestMapping("/cursos")
	public List<CursoDTO> lista() {
		
		List<Curso> cursos = cursoRepository.findAll();
		
		return CursoDTO.converte(cursos);
	}
}

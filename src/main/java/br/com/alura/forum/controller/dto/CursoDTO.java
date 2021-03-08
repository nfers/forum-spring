package br.com.alura.forum.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.Curso;

public class CursoDTO {
	private Long id;
	private String nome;
	private String categoria;

	public CursoDTO(Curso curso) {
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.categoria = curso.getCategoria();
		
	}
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCategoria() {
		return categoria;
	}

	public static List<CursoDTO> converte(List<Curso> cursos) {
		
		return cursos.stream().map(CursoDTO::new).collect(Collectors.toList());
	}
}

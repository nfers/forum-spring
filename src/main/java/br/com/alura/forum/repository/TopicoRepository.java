package br.com.alura.forum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	/*Navega dentro da entidade Topico no relacionamento Curso e 
	o hibernate monta a query para o nome do curso*/
	Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);

	
	
}

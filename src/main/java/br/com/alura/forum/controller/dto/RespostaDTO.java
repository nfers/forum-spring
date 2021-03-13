package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Resposta;

public class RespostaDTO {
	
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private String nomeAutor;


	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.nomeAutor = resposta.getAutor().getNome();
	}


	public Long getId() {
		return id;
	}


	public String getMensagem() {
		return mensagem;
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}


	public String getNomeAutor() {
		return nomeAutor;
	}

}

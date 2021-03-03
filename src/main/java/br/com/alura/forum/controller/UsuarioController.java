package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.Usuario;

@Controller
public class UsuarioController {
	
	@RequestMapping("/usuario")
	@ResponseBody
	public List<Usuario> lista() {
		Usuario usuario = new Usuario();
		
		return Arrays.asList(null);
	}
}

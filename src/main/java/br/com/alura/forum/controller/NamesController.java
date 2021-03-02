package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NamesController {
	
	@RequestMapping("/api/names")
	@ResponseBody
	public String names() {
		return "[{'name1': 'First Name'}, {'name2': 'Second Name'}]";
	}

}

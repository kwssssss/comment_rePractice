package org.galapagos.controller;

import org.galapagos.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = "/board")
@AllArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@ModelAttribute("searchTypes")
	public Map<String, String> searchTypes() {
		
	}
}

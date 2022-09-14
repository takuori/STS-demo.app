package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/") //トップページにマッピングすることを記述している
	public String index() {
		return "index"; //src/main/resources/templates/index.htmlがテンプレートとして使用される
	}

}

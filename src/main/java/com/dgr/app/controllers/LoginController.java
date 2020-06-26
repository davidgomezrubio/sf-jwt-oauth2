package com.dgr.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal,
			RedirectAttributes flash) {

		// the login session is previously active
		if (principal != null) {
			flash.addFlashAttribute("info", "The user has logged in previously");
			return "redirect:/companies";
		}

		
		if (error != null) {
			flash.addFlashAttribute("error");
			model.addAttribute("error", "login error, incorrect username or password");
		}
		return "login";
	}

}

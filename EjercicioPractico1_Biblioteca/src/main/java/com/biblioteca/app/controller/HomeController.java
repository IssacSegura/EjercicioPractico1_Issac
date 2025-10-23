/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.controller;

/**
 *
 * @author issac
 */

import com.biblioteca.app.domain.Libro;
import com.biblioteca.app.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final LibroService libroService;
    
    @GetMapping("/")
    public String paginaInicio(Model model) {
        List<Libro> libros = libroService.obtenerTodos();
        if (!libros.isEmpty()) {
            model.addAttribute("libroDestacado", libros.get(0));
        }
        model.addAttribute("totalLibros", libros.size());
        return "index";
    }
}
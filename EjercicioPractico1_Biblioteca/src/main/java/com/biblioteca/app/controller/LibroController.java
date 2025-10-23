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
import com.biblioteca.app.domain.Categoria;
import com.biblioteca.app.service.LibroService;
import com.biblioteca.app.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {
    
    private final LibroService libroService;
    private final CategoriaService categoriaService;
    
    @GetMapping
    public String listarLibros(Model model) {
        List<Libro> libros = libroService.obtenerTodos();
        model.addAttribute("libros", libros);
        return "libros/listado";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        List<Categoria> categorias = categoriaService.obtenerTodas();
        model.addAttribute("libro", new Libro());
        model.addAttribute("categorias", categorias);
        return "libros/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarLibro(@Valid @ModelAttribute Libro libro, 
                              BindingResult result, 
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<Categoria> categorias = categoriaService.obtenerTodas();
            model.addAttribute("categorias", categorias);
            return "libros/formulario";
        }
        
        libroService.guardar(libro);
        redirectAttributes.addFlashAttribute("mensaje", "Libro guardado exitosamente");
        return "redirect:/libros";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Libro libro = libroService.obtenerPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de libro inválido: " + id));
        
        List<Categoria> categorias = categoriaService.obtenerTodas();
        model.addAttribute("libro", libro);
        model.addAttribute("categorias", categorias);
        return "libros/formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        libroService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Libro eliminado exitosamente");
        return "redirect:/libros";
    }
}
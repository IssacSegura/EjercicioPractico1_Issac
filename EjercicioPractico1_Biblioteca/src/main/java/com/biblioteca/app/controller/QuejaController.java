/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.controller;

/**
 *
 * @author issac
 */


import com.biblioteca.app.domain.Queja;
import com.biblioteca.app.service.QuejaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/quejas")
@RequiredArgsConstructor
public class QuejaController {
    
    private final QuejaService quejaService;
    
    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("queja", new Queja());
        return "quejas/formulario";
    }
    
    @PostMapping("/enviar")
    public String enviarQueja(@Valid @ModelAttribute Queja queja, 
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "quejas/formulario";
        }
        
        quejaService.guardar(queja);
        redirectAttributes.addFlashAttribute("mensaje", 
            "Su " + queja.getTipo().toString().toLowerCase() + " ha sido enviada exitosamente");
        return "redirect:/quejas";
    }
    
    @GetMapping("/listado")
    public String listarQuejas(Model model) {
        model.addAttribute("quejas", quejaService.obtenerTodas());
        return "quejas/listado";
    }
}
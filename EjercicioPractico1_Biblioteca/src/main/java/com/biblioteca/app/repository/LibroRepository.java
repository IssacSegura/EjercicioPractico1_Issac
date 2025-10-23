/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.repository;

/**
 *
 * @author issac
 */

import com.biblioteca.app.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByOrderByTituloAsc();
    List<Libro> findByCategoriaId(Long categoriaId);
    List<Libro> findByDisponibleTrue();
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
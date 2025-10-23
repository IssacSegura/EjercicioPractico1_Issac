/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.repository;
import com.biblioteca.app.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 *
 * @author issac
 */




public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByOrderByNombreAsc();
    boolean existsByNombre(String nombre);
}
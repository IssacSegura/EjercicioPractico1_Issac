/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.service;

/**
 *
 * @author issac
 */

import com.biblioteca.app.domain.Categoria;
import com.biblioteca.app.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> obtenerTodas();
    Optional<Categoria> obtenerPorId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
}

@Service
@Transactional
@RequiredArgsConstructor
class CategoriaServiceImpl implements CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findByOrderByNombreAsc();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }
    
    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existePorNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }
}
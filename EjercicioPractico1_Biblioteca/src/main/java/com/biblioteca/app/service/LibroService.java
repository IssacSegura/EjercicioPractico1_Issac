/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.service;
import com.biblioteca.app.domain.Libro;
import com.biblioteca.app.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author issac
 */




public interface LibroService {
    List<Libro> obtenerTodos();
    Optional<Libro> obtenerPorId(Long id);
    Libro guardar(Libro libro);
    void eliminar(Long id);
    List<Libro> buscarPorTitulo(String titulo);
    List<Libro> obtenerDisponibles();
}

@Service
@Transactional
@RequiredArgsConstructor
class LibroServiceImpl implements LibroService {
    
    private final LibroRepository libroRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Libro> obtenerTodos() {
        return libroRepository.findByOrderByTituloAsc();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }
    
    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }
    
    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Libro> obtenerDisponibles() {
        return libroRepository.findByDisponibleTrue();
    }
}
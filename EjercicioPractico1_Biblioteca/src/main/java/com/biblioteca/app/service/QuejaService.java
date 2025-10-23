/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.service;

/**
 *
 * @author issac
 */

import com.biblioteca.app.domain.Queja;
import com.biblioteca.app.repository.QuejaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface QuejaService {
    List<Queja> obtenerTodas();
    Queja guardar(Queja queja);
    List<Queja> obtenerNoTratadas();
}

@Service
@Transactional
@RequiredArgsConstructor
class QuejaServiceImpl implements QuejaService {
    
    private final QuejaRepository quejaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Queja> obtenerTodas() {
        return quejaRepository.findByOrderByCreatedAtDesc();
    }
    
    @Override
    public Queja guardar(Queja queja) {
        return quejaRepository.save(queja);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Queja> obtenerNoTratadas() {
        return quejaRepository.findByTratadoFalse();
    }
}
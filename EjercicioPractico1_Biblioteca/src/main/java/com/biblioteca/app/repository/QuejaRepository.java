/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.repository;

/**
 *
 * @author issac
 */

import com.biblioteca.app.domain.Queja;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
    List<Queja> findByOrderByCreatedAtDesc();
    List<Queja> findByTratadoFalse();
    List<Queja> findByTipo(Queja.TipoQueja tipo);
}
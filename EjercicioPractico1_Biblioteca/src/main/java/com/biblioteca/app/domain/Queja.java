/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.app.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author issac
 */



@Data
@Entity
@Table(name = "queja")
public class Queja implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 150)
    @Column(name = "nombre_cliente", length = 150)
    private String nombreCliente;
    
    @Email(message = "Formato de email inválido")
    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;
    
    @Size(max = 30)
    @Column(name = "telefono", length = 30)
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoQueja tipo = TipoQueja.CONSULTA;
    
    @Size(max = 200)
    @Column(name = "asunto", length = 200)
    private String asunto;
    
    @NotBlank(message = "El mensaje es obligatorio")
    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;
    
    @Column(name = "tratado", nullable = false)
    private Boolean tratado = false;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public enum TipoQueja {
        QUEJA, SUGERENCIA, CONSULTA
    }
}
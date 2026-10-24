package edu.ufg.aulas.entidades;

import jakarta.persistence.*;

// los pisos del edificio
@Entity
@Table(name = "plantas")
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int numero;

    public Planta() {
    }

    public Planta(int numero) {
        this.numero = numero;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    @Override
    public String toString() {
        return String.valueOf(numero);
    }
}

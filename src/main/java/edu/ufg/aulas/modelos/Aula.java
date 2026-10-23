package edu.ufg.aulas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que representa un aula física de la universidad.
 * Mapeada a la tabla "aulas" en la base de datos.
 *
 * @author Tatia
 */
@Entity
@Table(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Long idAula;

    @Column(name = "nombre_aula", nullable = false, length = 100)
    private String nombreAula;

    @Column(name = "edificio", nullable = false, length = 100)
    private String edificio;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "tiene_proyector", nullable = false)
    private boolean tieneProyector;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    // ── Constructores ────────────────────────────────────────────────

    public Aula() {
    }

    public Aula(String nombreAula, String edificio, int capacidad, boolean tieneProyector, String descripcion) {
        this.nombreAula = nombreAula;
        this.edificio = edificio;
        this.capacidad = capacidad;
        this.tieneProyector = tieneProyector;
        this.descripcion = descripcion;
    }

    // ── Getters y Setters ────────────────────────────────────────────

    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isTieneProyector() {
        return tieneProyector;
    }

    public void setTieneProyector(boolean tieneProyector) {
        this.tieneProyector = tieneProyector;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ── toString ─────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Aula{" +
                "idAula=" + idAula +
                ", nombreAula='" + nombreAula + '\'' +
                ", edificio='" + edificio + '\'' +
                ", capacidad=" + capacidad +
                ", tieneProyector=" + tieneProyector +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

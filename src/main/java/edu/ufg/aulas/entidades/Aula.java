package edu.ufg.aulas.entidades;

import jakarta.persistence.*;

/**
 * Entidad que representa un aula de la universidad
 * Tabla aulas en la base de datos
 *
 * @author Maryorie
 */
@Entity
@Table(name = "aulas",
        uniqueConstraints = @UniqueConstraint(name = "uk_aula_nombre_edificio",
                columnNames = {"nombre_aula", "edificio"}))
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Long idAula;

    @Column(name = "nombre_aula", nullable = false, length = 100)
    private String nombreAula;

    @Column(name = "edificio", nullable = false, length = 100)
    private String edificio;

    @Column(name = "planta", nullable = false)
    private int planta;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    // relacion con el tipo de aula
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_aula_id", nullable = false)
    private TipoAula tipoAula;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    // relacion con el estado
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_aula_id", nullable = false)
    private EstadoAula estado;

    public Aula() {
    }

    public Aula(String nombreAula, String edificio, int planta, int capacidad,
                TipoAula tipoAula, String responsable, EstadoAula estado) {
        this.nombreAula = nombreAula;
        this.edificio = edificio;
        this.planta = planta;
        this.capacidad = capacidad;
        this.tipoAula = tipoAula;
        this.responsable = responsable;
        this.estado = estado;
    }

    public Long getIdAula() { return idAula; }
    public void setIdAula(Long idAula) { this.idAula = idAula; }
    public String getNombreAula() { return nombreAula; }
    public void setNombreAula(String nombreAula) { this.nombreAula = nombreAula; }
    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }
    public int getPlanta() { return planta; }
    public void setPlanta(int planta) { this.planta = planta; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public TipoAula getTipoAula() { return tipoAula; }
    public void setTipoAula(TipoAula tipoAula) { this.tipoAula = tipoAula; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public EstadoAula getEstado() { return estado; }
    public void setEstado(EstadoAula estado) { this.estado = estado; }
}

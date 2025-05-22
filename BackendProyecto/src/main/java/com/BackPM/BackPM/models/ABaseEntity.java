package com.BackPM.BackPM.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass  
// Indica que esta clase no es una entidad completa, pero sus campos se heredan en las subclases
public abstract class ABaseEntity {

    @Id  
    // Marca este campo como la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    // La base de datos auto-incrementa el valor del ID
    private Long id;

    @Column(name = "status", nullable = false)  
    // Columna "status" en la tabla, no puede ser nula
    private Boolean status;

    @Column(name = "created_at", nullable = true)  
    // Fecha y hora de creación, puede ser nula inicialmente
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)  
    // Fecha y hora de última actualización, puede ser nula hasta la primera modificación
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)  
    // Fecha y hora de eliminación lógica, se usa para auditoría o "soft delete"
    private LocalDateTime deletedAt;

    @Column(name = "created_by", nullable = true)  
    // ID del usuario que creó el registro, puede ser nulo si no aplica
    private Long createdBy;

    @Column(name = "updated_by", nullable = true)  
    // ID del usuario que realizó la última actualización
    private Long updatedBy;

    @Column(name = "deleted_by", nullable = true)  
    // ID del usuario que eliminó lógicamente el registro
    private Long deletedBy;
    
    // ---------- Getters y Setters ----------

    /** @return El ID único generado por la base de datos */
    public Long getId() {
        return id;
    }

    /** @param id Asigna el ID generado por la base de datos */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return true si el registro está activo; false si está inactivo/eliminado */
    public Boolean getStatus() {
        return status;
    }

    /** @param status Cambia el estado activo (true) o inactivo/eliminado (false) */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /** @return Fecha y hora en que se creó el registro */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /** @param createdAt Establece la fecha y hora de creación del registro */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /** @return Fecha y hora de la última actualización */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /** @param updatedAt Actualiza la marca de tiempo de la última modificación */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /** @return Fecha y hora de eliminación lógica */
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    /** @param deletedAt Registra cuándo se eliminó lógicamente el registro */
    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    /** @return ID del usuario que creó el registro */
    public Long getCreatedBy() {
        return createdBy;
    }

    /** @param createdBy Asigna el ID del usuario que creó el registro */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /** @return ID del usuario que actualizó el registro por última vez */
    public Long getUpdatedBy() {
        return updatedBy;
    }

    /** @param updatedBy Asigna el ID del usuario que realizó la última actualización */
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    /** @return ID del usuario que eliminó lógicamente el registro */
    public Long getDeletedBy() {
        return deletedBy;
    }

    /** @param deletedBy Asigna el ID del usuario que eliminó lógicamente el registro */
    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }
}
//     * @param id identificador de la entidad a eliminar
//     */
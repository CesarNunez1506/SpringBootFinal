package com.vatta.dto;

import com.vatta.model.Category;

public class CategoryDTO {

    private Long id; // Identificador de la categoría
    private String name; // Nombre de la categoría

    // Constructor vacío (opcional)
    public CategoryDTO() {}

    // Constructor con parámetros
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor para convertir una categoría en DTO
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
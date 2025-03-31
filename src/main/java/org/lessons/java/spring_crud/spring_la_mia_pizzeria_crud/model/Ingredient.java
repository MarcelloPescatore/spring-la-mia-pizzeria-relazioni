package org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* nome */
    @NotBlank(message = "Il nome è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizze;

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return List<Pizza> return the pizze
     */
    public List<Pizza> getPizze() {
        return pizze;
    }

    /**
     * @param pizze the pizze to set
     */
    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

    @Override
    public String toString() {
        return nome;
    }

}

package org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "pizza")
public class Pizza {

    /* id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* nome */
    @NotBlank(message = "Il nome è obbligatorio")
    @Column(nullable = false)
    private String nome;

    /* descrizione */
    @Lob
    @Size(max = 500, message = "La descrizione non può essere più lunga di 500 caratteri.")
    private String descrizione;

    /* foto (url) */
    @NotBlank(message = "URL è obbligatorio")
    @URL(message = "Inserisci un URL valido")
    private String fotoUrl;

    /* prezzo */
    @NotNull(message = "Il prezzo è obbligatorio")
    @Min(value = 1, message = "Il prezzo deve essere almeno 1€")
    @Column(nullable = false)
    private Double prezzo;

    /* one to many */
    @OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    private List<SpecialOffer> specialOffers;


    /* Costruttori */
    public Pizza(){};

    public Pizza(String nome, String descrizione, String fotoUrl, Double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.fotoUrl = fotoUrl;
        this.prezzo = prezzo;
    }

    /* getter e setter */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public List<SpecialOffer> getSpecialOffers(){
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers){
        this.specialOffers = specialOffers;
    }

    /* toString */
    @Override
    public String toString() {
        return String.format("Pizza: %s - %.2f€", nome, prezzo);
    }

}
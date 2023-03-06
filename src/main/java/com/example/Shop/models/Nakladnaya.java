package com.example.Shop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "nakladnaya")
public class Nakladnaya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNakladnaya;
    private String nameTovar;
    private String nameSostav;
    @NotEmpty(message = "Поле не может быть пустым")
    private String date;

    public Long getIdNakladnaya() {
        return idNakladnaya;
    }

    public void setIdNakladnaya(Long idNakladnaya) {
        this.idNakladnaya = idNakladnaya;
    }

    public String getNameTovar() {
        return nameTovar;
    }

    public void setNameTovar(String nameTovar) {
        this.nameTovar = nameTovar;
    }

    public String getNameSostav() {
        return nameSostav;
    }

    public void setNameSostav(String nameSostav) {
        this.nameSostav = nameSostav;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Nakladnaya(String nameTovar, String nameSostav, String date) {
        this.nameTovar = nameTovar;
        this.nameSostav = nameSostav;
        this.date = date;
    }

    public Nakladnaya() {
    }
}

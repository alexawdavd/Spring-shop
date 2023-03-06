package com.example.Shop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vozvrat")
public class Vozvrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTovar;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 5, max = 30, message = "Размер данного поля должен быть в диапозоне от 5 до 30")
    private String nameTovar;
    @Positive(message = "Число не должно быть отрицательным")
    @NotNull(message = "Поле не может быть пустым")
    private Integer costTovar;
    @Positive(message = "Число не должно быть отрицательным")
    @NotNull(message = "Поле не может быть пустым")
    private Integer kolvoTovar;

    public Long getIdTovar() {
        return idTovar;
    }

    public void setIdTovar(Long idTovar) {
        this.idTovar = idTovar;
    }

    public String getNameTovar() {
        return nameTovar;
    }

    public void setNameTovar(String nameTovar) {
        this.nameTovar = nameTovar;
    }

    public Integer getCostTovar() {
        return costTovar;
    }

    public void setCostTovar(Integer costTovar) {
        this.costTovar = costTovar;
    }

    public Integer getKolvoTovar() {
        return kolvoTovar;
    }

    public void setKolvoTovar(Integer kolvoTovar) {
        this.kolvoTovar = kolvoTovar;
    }

    public Vozvrat(String nameTovar, Integer costTovar, Integer kolvoTovar) {
        this.nameTovar = nameTovar;
        this.costTovar = costTovar;
        this.kolvoTovar = kolvoTovar;
    }

    public Vozvrat() {
    }
}

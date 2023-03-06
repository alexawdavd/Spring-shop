package com.example.Shop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.beans.IntrospectionException;
import java.util.Collection;

@Entity
@Table(name = "tovar")
public class Tovar {
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
    private Boolean prodano;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Poctsvchik poctsvchik;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Licenziya licenziya;

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

    public Poctsvchik getPoctsvchik() {
        return poctsvchik;
    }

    public void setPoctsvchik(Poctsvchik poctsvchik) {
        this.poctsvchik = poctsvchik;
    }

    public Licenziya getLicenziya() {
        return licenziya;
    }

    public void setLicenziya(Licenziya licenziya) {
        this.licenziya = licenziya;
    }

    public Boolean getProdano() {
        return prodano;
    }

    public void setProdano(Boolean prodano) {
        this.prodano = prodano;
    }

    public Tovar(String nameTovar, Integer costTovar, Integer kolvoTovar, Boolean prodano, Poctsvchik poctsvchik, Licenziya licenziya) {
        this.nameTovar = nameTovar;
        this.costTovar = costTovar;
        this.kolvoTovar = kolvoTovar;
        this.prodano = prodano;
        this.poctsvchik = poctsvchik;
        this.licenziya = licenziya;
    }

    public Tovar() {
    }
}

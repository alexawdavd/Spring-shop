package com.example.Shop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "postavchik")
public class Poctsvchik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoctsvchik;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 5, max = 30, message = "Размер данного поля должен быть в диапозоне от 5 до 30")
    private String namePoctsvchik;
    @NotEmpty(message = "Поле не может быть пустым")
    private String datePoctsvchik;
    @Positive(message = "Число не должно быть отрицательным")
    @NotNull(message = "Поле не может быть пустым")
    private Integer costPoctsvchik;
    @Positive(message = "Число не должно быть отрицательным")
    @NotNull(message = "Поле не может быть пустым")
    private Integer kolvoPoctsvchik;
    @OneToMany(mappedBy = "poctsvchik", fetch = FetchType.EAGER)
    private Collection<Tovar> owner;

    public Long getIdPoctsvchik() {
        return idPoctsvchik;
    }

    public void setIdPoctsvchik(Long idPoctsvchik) {
        this.idPoctsvchik = idPoctsvchik;
    }

    public String getNamePoctsvchik() {
        return namePoctsvchik;
    }

    public void setNamePoctsvchik(String namePoctsvchik) {
        this.namePoctsvchik = namePoctsvchik;
    }

    public String getDatePoctsvchik() {
        return datePoctsvchik;
    }

    public void setDatePoctsvchik(String datePoctsvchik) {
        this.datePoctsvchik = datePoctsvchik;
    }

    public Integer getCostPoctsvchik() {
        return costPoctsvchik;
    }

    public void setCostPoctsvchik(Integer costPoctsvchik) {
        this.costPoctsvchik = costPoctsvchik;
    }

    public Integer getKolvoPoctsvchik() {
        return kolvoPoctsvchik;
    }

    public void setKolvoPoctsvchik(Integer kolvoPoctsvchik) {
        this.kolvoPoctsvchik = kolvoPoctsvchik;
    }

    public Collection<Tovar> getOwner() {
        return owner;
    }

    public void setOwner(Collection<Tovar> owner) {
        this.owner = owner;
    }
}

package com.example.Shop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "licenziya")
public class Licenziya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLicenziya;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 5, max = 30, message = "Размер данного поля должен быть в диапозоне от 5 до 30")
    private String nameTov;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 5, max = 30, message = "Размер данного поля должен быть в диапозоне от 5 до 30")
    private String nameOrganizaciya;
    @NotEmpty(message = "Поле не может быть пустым")
    private String dataSostav;
    @OneToMany(mappedBy = "licenziya", fetch = FetchType.EAGER)
    private Collection<Tovar> owner;

    public Long getIdLicenziya() {
        return idLicenziya;
    }

    public void setIdLicenziya(Long idLicenziya) {
        this.idLicenziya = idLicenziya;
    }

    public String getNameTov() {
        return nameTov;
    }

    public void setNameTov(String nameTov) {
        this.nameTov = nameTov;
    }

    public String getNameOrganizaciya() {
        return nameOrganizaciya;
    }

    public void setNameOrganizaciya(String nameOrganizaciya) {
        this.nameOrganizaciya = nameOrganizaciya;
    }

    public String getDataSostav() {
        return dataSostav;
    }

    public void setDataSostav(String dataSostav) {
        this.dataSostav = dataSostav;
    }

    public Collection<Tovar> getOwner() {
        return owner;
    }

    public void setOwner(Collection<Tovar> owner) {
        this.owner = owner;
    }
}

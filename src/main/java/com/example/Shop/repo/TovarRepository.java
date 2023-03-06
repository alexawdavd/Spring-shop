package com.example.Shop.repo;

import com.example.Shop.models.Tovar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TovarRepository extends CrudRepository<Tovar, Long> {
    Tovar findByNameTovar(String nameTovar);
    List<Tovar> findByNameTovarContaining(String nameTovar);
}

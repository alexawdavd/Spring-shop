package com.example.Shop.repo;

import com.example.Shop.models.Poctsvchik;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PoctsvchikRepository extends CrudRepository<Poctsvchik, Long> {
    Poctsvchik findByNamePoctsvchik(String namePoctsvchik);
    List<Poctsvchik> findByNamePoctsvchikContaining(String namePoctsvchik);
}

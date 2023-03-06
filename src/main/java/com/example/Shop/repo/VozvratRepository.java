package com.example.Shop.repo;

import com.example.Shop.models.Vozvrat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VozvratRepository extends CrudRepository<Vozvrat, Long> {
    List<Vozvrat> findByNameTovarContaining(String nameTovar);
}

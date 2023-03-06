package com.example.Shop.repo;

import com.example.Shop.models.Nakladnaya;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NakladnayaRepository extends CrudRepository<Nakladnaya, Long> {
    List<Nakladnaya> findByNameTovar(String nameTovar);
    List<Nakladnaya> findByNameTovarContaining(String nameTovar);
}

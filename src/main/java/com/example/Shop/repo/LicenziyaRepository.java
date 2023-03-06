package com.example.Shop.repo;

import com.example.Shop.models.Licenziya;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicenziyaRepository extends CrudRepository<Licenziya, Long> {
    Licenziya findByNameTov(String nameTov);
    List<Licenziya> findByNameTovContaining(String nameTov);
}

package com.biscuit.demo.Repoitory;

import com.biscuit.demo.entitiy.City;
import com.biscuit.demo.entitiy.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepoitory extends JpaRepository<City, Integer> {
    @Override
    Optional<City> findById(Integer City);

    @Override
    List<City> findAll();
    List<City> findByCity (String City);
}

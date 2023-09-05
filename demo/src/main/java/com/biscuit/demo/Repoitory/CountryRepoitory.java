package com.biscuit.demo.Repoitory;

import com.biscuit.demo.entitiy.Country;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepoitory extends JpaRepository<Country, Integer> {
    @Override
    Optional<Country> findById(Integer Country);

    @Override
    List<Country> findAll();
    List<Country> findByCountry (String Country);
}

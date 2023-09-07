package com.biscuit.demo.CountryController;

import com.biscuit.demo.Repoitory.CityRepoitory;
import com.biscuit.demo.Repoitory.CountryRepoitory;
import com.biscuit.demo.entitiy.City;
import com.biscuit.demo.entitiy.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {
    @Autowired
    private CountryRepoitory crepo;
    @Autowired
    private CityRepoitory repo;
    @GetMapping("/city")
    public List<City> getCity () {
        return repo.findAll();
    }
    @GetMapping("/city2C")
    public Optional<Country> city(@RequestParam String name) {
        List<City> array = repo.findByCity(name);
        if (array.size() > 0) {
            return crepo.findById(array.get(0).getCountryId());
        }

        return null;
    }
}

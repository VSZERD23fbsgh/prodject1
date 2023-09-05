package com.biscuit.demo.CountryController;

import com.biscuit.demo.APIReturn;
import com.biscuit.demo.Repoitory.CountryRepoitory;
import com.biscuit.demo.entitiy.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    private CountryRepoitory repo;
    @GetMapping("/AllCountries")
    public List<Country> getCountry () {
        return repo.findAll();
    }
    @GetMapping("/AllCountriesDif")
    public String getDCountry () {
        List<Country> array = repo.findAll();
        String re = " ";
        for(Country I : array) {
            if(I.getCountryId() % 3 == 0) {
                re += " " + I.getCountry();
            }
        }
        return re;
    }
    @GetMapping("/ModifyCountry")
    public String ModifyCountry (@RequestParam String name) {
        List<Country> array = repo.findByCountry(name);
        int location = 0;
        Country swt = array.get(location);
        swt.setCountry(name + "Updated");
        repo.save(swt);
        return "SAVED " + name + " TO> " + location;
    }
    @GetMapping("/TimeUpdate")
    public String ModifyTime (@RequestParam String name) {
        List<Country> array = repo.findByCountry(name);
        if (array.size() > 0) {
            Country mod = array.get(0);
            mod.setLastUpdate( new Timestamp(System.currentTimeMillis()) );
            repo.save(mod);
            return "worked" + mod.getLastUpdate();
        }
        return "didnt worked";
    }
    @DeleteMapping("/deletCountry")
    public String deletCountry (@RequestParam String name) {

            List<Country> array = repo.findByCountry(name);
            if(array.size() > 0 && array != null) {
                Country temp = array.get(0);
                repo.delete(temp);
                return "REMOVED: " + name;
            }

        return "CAN NOT FIND: " + name;
    }
    @DeleteMapping("/RefreshUpdate")
    public String deletupdate (@RequestParam String name) {
        List<Country> array = repo.findByCountry(name);
        if(array.size() > 0) {
            Country re = array.get(0);
            if (re.getCountry().endsWith("Updated")) {
                re.setCountry(re.getCountry().substring(0, re.getCountry().length() - 7));
                repo.save(re);
                return "worked" + re.getCountry();
            }
        }
        return "didd not work";
    }
    @PostMapping(value = "/AddCountry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIReturn> AddCountry (@RequestParam String name) {
           // if(name != null && "".equals(name)) {
                List<Country> countries = repo.findByCountry(name);
                if(countries != null && countries.size() > 0) {
                    return ResponseEntity.unprocessableEntity().body(new APIReturn("55","NOT_SAVED","COUNTRY ALREADY ESTIST"));
                } else {
                    Country newCountry = new Country();
                    newCountry.setCountry(name);
                    newCountry.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    newCountry = repo.save(newCountry);
                    return ResponseEntity.status(201).body(new APIReturn("0","SAVED","COUNTRY SAVED WIT ID:" +newCountry.getCountryId()));
                }
           // }
       // return ResponseEntity.badRequest().body(new APIReturn("56","error","could not save name is empty"));

    }
}

package com.example.tpobdCarRentalService.repository;

import com.example.tpobdCarRentalService.modele.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {


    Optional<Auto>findAutoByModele(String modele);

    @Query("select a from Auto a where a.inmatriculation=?1")
    Optional<Auto> findByInmatriculation(String inmatriculation);


}

package com.example.tpobdCarRentalService.service;

import com.example.tpobdCarRentalService.modele.Auto;
import com.example.tpobdCarRentalService.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AutoService {
    @Autowired
    private AutoRepository autoRepository;

   //@return la liste de tous lesautos
    public List<Auto> listAll(){
        return autoRepository.findAll();
    }

   // @param auto, sauvegarder l<auto
    public void save (Auto auto){
        autoRepository.save(auto);
    }

    //@param id du auto cherche
    //@return l'auto cherche par ID
    public Auto get (Integer id){
        return autoRepository.findById(id).get();
    }

    // @param inmatriculation de l'auto cherche
    // @return l'auto cherche par inmatriculation
    public Auto get (String inmatriculation){
        return autoRepository.findByInmatriculation(inmatriculation).get();
    }

    //@param id du produit a supprimer
    public void delete(Integer id){
        autoRepository.deleteById(id);
    }

    //@param auto rajouter auto dans la BD en verifiant si auto avec inmatriculation existe
    public void addNewAuto(Auto auto) {
        Optional<Auto> autoOptional=autoRepository.findByInmatriculation(auto.getInmatriculation());
        if (autoOptional.isPresent()){
            throw new IllegalStateException("ce numero d'inmatriculation existe");
        }
        autoRepository.save(auto);
        System.out.println(auto);
    }


    //louer une auto
    public boolean louerAuto(Integer autoId) {
        Auto auto = autoRepository.findById(autoId)
                .orElseThrow(() -> new IllegalStateException(
                        "L'auto avec l'ID " + autoId + " n'existe pas."
                ));
        if (auto.getLoue() == false) {
            auto.setLoue(true);
            return true;
        } else {
            System.out.println( "L'auto avec l'ID " + autoId + " est deja louee.");
            return false;
        }
    }

    //retourner une auto
    public boolean retournerAuto(Integer autoId, boolean loue) {
        Auto auto = autoRepository.findById(autoId)
                .orElseThrow(() -> new IllegalStateException(
                        "L'auto avec l'ID " + autoId + " n'existe pas."
                ));
        if (auto.getLoue()) {
            auto.setLoue(false);
            return true;
        } else {
            System.out.println( "L'auto avec l'ID " + autoId + " n'est pas louee.");
            return false;
        }
    }

}

package com.example.tpobdCarRentalService.controller;

import com.example.tpobdCarRentalService.modele.Auto;
import com.example.tpobdCarRentalService.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/")
public class AutoController {

    @Autowired
    private AutoService autoService;

    public List<Auto> list(){

        return autoService.listAll();
    }

    //affichage de la page avec telechargement des infos de la BD
    @GetMapping("/")
    public ModelAndView showAllListCars() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        modelAndView.addObject("listAuto", autoService.listAll());
        List<Auto> listAuto = autoService.listAll();
        return modelAndView;
        }

//obtenir la liste de tous les autos loues
    @GetMapping("/listlouer")
    public ModelAndView showAllRentedCars() {
        ModelAndView modelAndView = new ModelAndView();
        List<Auto> rented = autoService.listAll();
        CollectionUtils.filter(rented, a -> ((Auto) a).getLoue() == true);
        modelAndView.setViewName("index.html");
        modelAndView.addObject("listAuto", rented);
        return modelAndView;
    }

//avoir une auto par ID
    @GetMapping("/autos/id/{id}")
    public ResponseEntity<Auto> get(@PathVariable Integer id) {

        try {
            Auto auto = autoService.get(id);

            return new ResponseEntity<Auto>(auto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Auto>(HttpStatus.NOT_FOUND);
        }
    }

//avoir la liste de tous les autos
    @GetMapping("/autos/list")
    public ResponseEntity< List<Auto>> get() {

        try {
            List<Auto> listAuto = autoService.listAll();

            return new ResponseEntity<>(listAuto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //retrouver une auto en indicant l<inmatriculation
    @GetMapping("/autos/inmatriculation/{inmatriculation}")
    public ResponseEntity<Auto> get(@PathVariable String inmatriculation) {
        try {
            Auto auto = autoService.get(inmatriculation);
            return new ResponseEntity<Auto>(auto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Auto>(HttpStatus.NOT_FOUND);
        }
    }

    //a utiliser si on implantera le formulaire de rajout d'une auto dans la BD via un formulaire
    @PostMapping("/autos/add")
    public void registerNewAuto(@RequestBody Auto auto){
        autoService.addNewAuto(auto);
    }

    //louer une auto
    @PutMapping(path="/autos/louer/{autoId}")
    public ResponseEntity louerAuto(
            @PathVariable("autoId") Integer autoId) {

        boolean result = autoService.louerAuto(autoId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);//HttpStatus.ok c'est le code 200, on s'en servira dans la methode javascript
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);//HttpStatus.conflict c'est le code 409, on s'en servira dans la methode javascript
        }
    }

//retourner une auto
    @PutMapping(path="/autos/retourner/{autoId}")
    public ResponseEntity retournerAuto(
            @PathVariable("autoId") Integer autoId,
            @RequestParam(required = false) boolean loue) {
        boolean result = autoService.retournerAuto(autoId, loue);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK); //HttpStatus.ok c'est le code 200
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT); //HttpStatus.conflict c'est le code 409, on s'en servira dans la methode javascript
        }
    }


}

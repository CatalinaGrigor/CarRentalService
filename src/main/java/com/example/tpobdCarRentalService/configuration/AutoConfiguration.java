package com.example.tpobdCarRentalService.configuration;

import com.example.tpobdCarRentalService.modele.Auto;
import com.example.tpobdCarRentalService.repository.AutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@Component
public class AutoConfiguration {

    //creation des autos
    @Bean
    CommandLineRunner commandLineRunner (AutoRepository autoRepository){
        return args -> {
            Auto auto1= new Auto(2006, 150000, "Honda Pilot", "G256MKS", false, 220);
            Auto auto2= new Auto(2008, 190000, "Buick Terraza", "A256LKD", false, 150);
            Auto auto3= new Auto(2013, 100000, "Volvo XC90", "B456FGH", false, 250);
            Auto auto4= new Auto( 2020, 30000, "Hyundai Ioniq", "F456SDF", false, 400);
            Auto auto5= new Auto( 2022, 5000, "Mercecdez Benz", "D123LKJ", false, 650);
            Auto auto6= new Auto( 1980, 200000, "BMW", "R123456", false, 350);
            Auto auto7= new Auto( 1956, 90000, "Cadillac", "T123SDF", false, 600);

           autoRepository.saveAll(List.of(auto1, auto2, auto3, auto4, auto5, auto6, auto7));
           autoRepository.findAll().forEach(System.out::println);;

        };
    }
}

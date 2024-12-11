package com.farmlogistic.farmlogistic.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/farm")
//@SecurityRequirement(name = "Authorization")
//@Tag(name = "Farm", description = "Farm resource endpoints.")
public class FarmController {

    @GetMapping
    public String hello() {
        return "Hello Farm";
    }

     @GetMapping("/animal")
    public String getAnimalInfo(
        @RequestParam("peso") double peso,
        @RequestParam("raca") String raca,
        @RequestParam("alimentaTipo") String alimentaTipo,
        @RequestParam("idade") int idade,
        @RequestParam("saudeStatus") String saudeStatus
    ) {
        return String.format(
            "Animal Info: peso: %.2f kg, raca: %s, alimentaTipo: %s, idade: %d anos, saudeStatus: %s",
            peso, raca, alimentaTipo, idade, saudeStatus);
    }

    // Informações sobre o tipo de alimentação
    @GetMapping("/alimentacao")
    public String getFeedingInfo(
        @RequestParam("tipoAlimentacao") String tipoAlimentacao,
        @RequestParam("quantidade") double quantidade,
        @RequestParam("periodo") String periodo
    ) {
        return String.format(
            "Alimentacao Info: Tipo alimentacao: %s, Quantidade: %.2f kg, Periodo: %s",
            tipoAlimentacao, quantidade, periodo
        );
    }


}



package com.farmlogistic.farmlogistic.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(description = "Modelo que representa um animal na fazenda")
public class Animal extends BasicEntity {
    @Schema(description = "Peso do animal", example = "20.0")
    private double peso; 
    
    @Schema(description = "Raça do animal", example = "Labrador")
    private String raca;
    
    @Schema(description = "Tipo de alimentação do animal", example = "Seca")
    private String tipoAlimentacao;
    
    @Schema(description = "Idade do animal em anos", example = "5")
    private int idade;
    
    @Schema(description = "Estado de saúde do animal", example = "Saudável")
    private String estadoSaude;

    public Animal(double peso, String raca, String tipoAlimentacao, int idade, String estadoSaude) {
        this.peso = peso;
        this.raca = raca;
        this.tipoAlimentacao = tipoAlimentacao;
        this.idade = idade;
        this.estadoSaude = estadoSaude;
    }
}

package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Category {

    COMBATE_A_FOME("Combate a Fome",0),
    CRIANÇAS("Crianças",1),
    ENFERMOS("Enfermos",2),
    COMBATE_A_COVID_19("Combate a COVID-19",2),
    CAUSAS_AMBIENTAIS("Causas Ambientais",3),
    SOBREVIVENTES_DE_GUERRA("Sobreviventes de Guerra",4),
    ANIMAIS("Animais",5),
    SONHOS("Sonhos",6),
    POBREZA("Pobreza",7),
    OUTROS("Outros",8);

    private String description;
    private Integer type;

    Category(Integer type) {
        this.type = type;
    }
    public Integer getType() {
        return type;
    }

    Category(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public static Category ofType(Integer type) {
        return Arrays.stream(Category.values())
                .filter(tp -> tp.getType().equals(type))
                .findFirst()
                .get();
    }

}

package com.alkimin.simplesdental.domain.profissional;


import com.alkimin.simplesdental.domain.contato.Contato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Entity(name = "profissionais")
@NoArgsConstructor
@AllArgsConstructor
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @OneToMany(mappedBy = "profissional")
    private List<Contato> contatos;
    private LocalDate nascimento;
    private LocalDate created_date;
}

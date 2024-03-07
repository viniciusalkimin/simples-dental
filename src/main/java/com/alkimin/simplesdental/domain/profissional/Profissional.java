package com.alkimin.simplesdental.domain.profissional;


import com.alkimin.simplesdental.domain.contato.Contato;
import jakarta.persistence.*;
import lombok.*;


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
    @Setter
    private String nome;
    @Enumerated(EnumType.STRING)
    @Setter
    private Cargo cargo;
    @OneToMany(mappedBy = "profissional")
    private List<Contato> contatos;
    @Setter
    private LocalDate nascimento;
    private LocalDate created_date;
    @Setter
    private boolean isAtivo;
}

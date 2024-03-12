package com.alkimin.simplesdental.domain.profissional;


import com.alkimin.simplesdental.domain.contato.Contato;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
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
    @OneToMany(mappedBy = "profissional", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    private List<Contato> contatos;
    @Setter
    private LocalDate nascimento;
    private LocalDate created_date;
    @Setter
    private boolean isAtivo;
}

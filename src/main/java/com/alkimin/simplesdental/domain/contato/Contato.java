package com.alkimin.simplesdental.domain.contato;

import com.alkimin.simplesdental.domain.profissional.Profissional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity(name = "contatos")
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private Profissional profissional;
    private String nome;
    private String contato;
    private LocalDate created_date;
}

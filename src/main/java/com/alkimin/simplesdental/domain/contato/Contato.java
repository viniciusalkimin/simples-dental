package com.alkimin.simplesdental.domain.contato;

import com.alkimin.simplesdental.domain.profissional.Profissional;
import com.alkimin.simplesdental.infrastructure.profissional.dto.ProfissionalRecord;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Entity(name = "contatos")
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;
    @Setter
    private String nome;
    @Setter
    private String contato;
    private LocalDate created_date;
}

package com.alkimin.simplesdental.infrastructure.contato.repository;

import com.alkimin.simplesdental.domain.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID> {

    @Query( value = "SELECT * FROM CONTATOS " +
            "WHERE concat_ws(contato, created_date, nome, profissional_id) LIKE %?1%",
            nativeQuery = true)
    List<Contato> findByParam(String q);
}

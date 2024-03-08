package com.alkimin.simplesdental.infrastructure.profissional.repository;

import com.alkimin.simplesdental.domain.profissional.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {

    @Query( value = "SELECT * FROM PROFISSIONAIS " +
            "WHERE concat_ws(cargo, created_date, is_ativo, nascimento, nome) LIKE %?1%",
    nativeQuery = true)
    List<Profissional> findByParam(String param);
}

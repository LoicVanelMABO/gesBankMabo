package com.gesBankMabo.repositories;

import com.gesBankMabo.entities.CompteBancaire;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,Long> {
    Optional<CompteBancaire> findByNumCompte(@Param("numCompte") String numCompte);
}

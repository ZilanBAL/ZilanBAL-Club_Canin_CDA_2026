package edu.ban7.club_canin_cda_2026.dao;

import edu.ban7.club_canin_cda_2026.model.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexeDao extends JpaRepository<Sexe,Integer> {
}

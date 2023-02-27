package com.example.marketplace.repository;

import com.example.marketplace.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IinterventionRepo extends JpaRepository<Intervention,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE   Intervention  i SET  i.dureeinter=:dureeinter where i.id=:id")
    void updateintervention(@Param("id") Long id, @Param("dureeinter") Integer dureeinter);
}

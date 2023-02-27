package com.example.marketplace.repository;

import com.example.marketplace.entities.Reclamation;
import com.example.marketplace.enumerations.Statuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IRecrepo extends JpaRepository<Reclamation,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE   Reclamation  i SET  i.ticketstatus=:ticketstatus where i.idrec=:idrec")
    void updaterecc(@Param("idrec") Long idrec, @Param("ticketstatus") Statuss ticketstatus);
    @Query("Select u from Reclamation  u order by u.priorite DESC ")
    List<Reclamation> orderbypriorite();}

package com.harmonycloud.repository;

import com.harmonycloud.entity.Encounter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EncounterRepository extends JpaRepository<Encounter,Integer> {


    Encounter findByAppointmentId(Integer appointmengtId);

}

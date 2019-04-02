package com.harmonycloud.service;

import com.harmonycloud.entity.Encounter;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.EncounterException;
import com.harmonycloud.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EncounterService {

    @Autowired
    private EncounterRepository encounterRepository;


    public void save(Encounter encounter) throws Exception {
        if (encounterRepository.save(encounter).getEncounterId() == null) {
            throw new EncounterException(ErrorMsgEnum.SAVE_ERROR.getMessage());
        }
    }

    public Encounter saveEncounterCancel(Encounter encounter) throws Exception {
        Encounter oldEncounter = encounterRepository.findByAppointmentId(encounter.getAppinmentId());
        if (oldEncounter == null) {
            return null;
        }
        encounterRepository.delete(oldEncounter);
        return oldEncounter;
    }


    public Encounter getEncounter(Integer appointmentId) throws Exception {
        return encounterRepository.findByAppointmentId(appointmentId);
    }
}

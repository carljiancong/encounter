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

    public void saveEncounterCancel(Encounter encounter) throws Exception{
        encounterRepository.delete(encounterRepository.findByAppointmentId(encounter.getAppinmentId()));
    }


    public Encounter getEncounter(Integer appointmentId) throws Exception {
        return encounterRepository.findByAppointmentId(appointmentId);
    }
}

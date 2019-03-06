package com.harmonycloud.service;

import com.harmonycloud.entity.Encounter;
import com.harmonycloud.repository.EncounterRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;


@Service
public class EncounterService {

    @Autowired
    private EncounterRepository encounterRepository;

    @Transactional(rollbackFor = Exception.class)
    @Compensable(compensationMethod = "saveEncounterCancel", timeout = 10)
    public Result save(Encounter encounter) throws Exception {
        try {
            encounterRepository.save(encounter);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("save error");
        }
        return Result.buildSuccess(encounter);
    }


    public void saveEncounterCancel(Encounter encounter) {
        Encounter encounter1 = null;
        try {
            encounter1 = encounterRepository.findByAppointmentId(encounter.getAppinmentId());
            encounterRepository.delete(encounter1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

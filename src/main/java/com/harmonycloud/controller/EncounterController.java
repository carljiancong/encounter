package com.harmonycloud.controller;

import com.harmonycloud.entity.Encounter;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.EncounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EncounterController {

    @Autowired
    EncounterService encounterService;

    @RequestMapping(path = "/encounter", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Result save(@RequestBody Encounter encounter) throws Exception {
        return encounterService.save(encounter);
    }


}

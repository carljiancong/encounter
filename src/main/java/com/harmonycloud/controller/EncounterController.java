package com.harmonycloud.controller;

import com.harmonycloud.entity.Encounter;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.EncounterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Encounter")
public class EncounterController {

    @Autowired
    EncounterService encounterService;

    @RequestMapping(path = "/encounter", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Result save(@RequestBody Encounter encounter) throws Exception {
        return encounterService.save(encounter);
    }

    @ApiOperation(value = "get encounter by appointment id", httpMethod = "GET")
    @ApiImplicitParam(name = "appointmentId", value = "appointmentId", paramType = "query", dataType = "Integer")
    @GetMapping("/encounterEntity")
    public Result getEncounterList(@RequestParam("appointmentId") Integer appointmentId) {
        return encounterService.getEncounterList(appointmentId);
    }


}

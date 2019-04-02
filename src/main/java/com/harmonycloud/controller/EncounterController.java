package com.harmonycloud.controller;

import com.harmonycloud.entity.Encounter;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.EncounterException;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.service.EncounterService;
import com.harmonycloud.util.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EncounterController {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    EncounterService encounterService;

    @Autowired
    HttpServletRequest request;

    /**
     * save encounter when attend
     *
     * @param encounter encounter
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @PostMapping(path = "/encounter", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(rollbackFor = Throwable.class)
    @Compensable(compensationMethod = "saveEncounterCancel", timeout = 10)
    public CimsResponseWrapper<String> save(@RequestBody Encounter encounter) throws Exception {

        if (encounter == null || encounter.getAppinmentId() == null || encounter.getAppinmentId() <= 0) {
            throw new EncounterException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }
        String msg = LogUtil.getRequest(request) + ", information='";

        encounterService.save(encounter);
        Thread.sleep(7000);
        logger.info(msg + "Save encounter success");
        return new CimsResponseWrapper<>(true, null, "Save encounter success");
    }

    /**
     * saga: save encounter cancel
     *
     * @param encounter model
     */
    @PostMapping(path = "/encounter")
    public void saveEncounterCancel(Encounter encounter) throws Exception {
        encounterService.saveEncounterCancel(encounter);
    }

    /**
     * get encounter by appointmentId
     *
     * @param appointmentId appointmentId
     * @return
     */
    @ApiOperation(value = "get encounter by appointment id", httpMethod = "GET")
    @ApiImplicitParam(name = "appointmentId", value = "appointmentId", paramType = "query", dataType = "Integer")
    @GetMapping("/getEncounter")
    public CimsResponseWrapper<Encounter> getEncounter(@RequestParam("appointmentId") Integer appointmentId) throws Exception {
        if (appointmentId == null || appointmentId <= 0) {
            throw new EncounterException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }
        Encounter encounter = encounterService.getEncounter(appointmentId);
        return new CimsResponseWrapper<>(true, null, encounter);
    }

}

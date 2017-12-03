package com.test.sampleapp.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.test.sampleapp.web.dto.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import com.test.sampleapp.service.ApplicationService;
import com.test.sampleapp.domain.ApplicationItem;
/**
 * Created by MRomeh on 08/08/2017.
 */
@RestController
@RequestMapping("/application")
@Api(value = "Applciation demo")
public class ApplicationController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApplicationService applicationService;


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "view the list of ALL current active created stored appllication items", response = ApplicationEntry.class)
    public List<ApplicationEntry> getAllAlerts() {
        log.debug("Trying to retrieve all alerts");
        return applicationService.getApplicationItems().stream()
                .map(applicationItem -> modelMapper.map(applicationItem, ApplicationEntry.class)).collect(Collectors.toList());

    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create an application entry into the application manager")
    public void createAlert(@Valid @RequestBody ApplicationEntry request) {
        log.debug("Trying to create an alert: {}", request.toString());
        applicationService.createApplicationItem(modelMapper.map(request, ApplicationItem.class));
    }

    @RequestMapping(method={RequestMethod.GET},value={"/version"})
    public String getVersion() {
        return "1.0";
    }



}

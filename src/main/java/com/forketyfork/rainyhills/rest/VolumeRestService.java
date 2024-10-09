package com.forketyfork.rainyhills.rest;

import com.forketyfork.rainyhills.model.CalculationDataBean;
import com.forketyfork.rainyhills.services.VolumeCalculator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@ApplicationScoped
@Path("/calculation")
@Produces("application/json")
@Consumes("application/json")
public class VolumeRestService {

    @Inject
    private VolumeCalculator calculator;

    @POST
    public CalculationDataBean createCalculation(@NotNull CalculationDataBean bean) {
        bean.setResult(calculator.calculate(bean.getInput()));
        return bean;
    }

}
package com.forketyfork.rainyhills.rest;

import com.forketyfork.rainyhills.model.CalculationDataBean;
import com.forketyfork.rainyhills.services.VolumeCalculator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

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
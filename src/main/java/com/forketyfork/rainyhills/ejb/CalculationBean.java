package com.forketyfork.rainyhills.ejb;

import com.forketyfork.rainyhills.model.CalculationDataBean;
import com.forketyfork.rainyhills.services.VolumeCalculator;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * A session-scoped bean representing a calculation form controller.
 * <p>
 * Created by Forketyfork on 26.03.17.
 */
@Named("calculation")
@SessionScoped
public class CalculationBean implements Serializable {

    /**
     * The outcome of a successful computation, signalling the presentation framework
     * to move on to displaying the result.
     */
    private static final String OUTCOME_SUCCESS = "success";

    /**
     * A POJO containing the input and output data of the computation.
     */
    @Inject
    private CalculationDataBean inputData;

    /**
     * The volume calculator implementation.
     */
    @Inject
    private VolumeCalculator calculator;

    /**
     * The action of calculating the result according to the submitted input.
     * Currently always returns "success", as no exceptional situations supposed to occur.
     *
     * @return {@link CalculationBean#OUTCOME_SUCCESS}, if the result is successful.
     */
    public String calculateAction() {
        inputData.setResult(calculator.calculate(inputData.getInput()));
        return OUTCOME_SUCCESS;
    }

}

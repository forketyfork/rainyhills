package com.forketyfork.rainyhills.ejb;

import com.forketyfork.rainyhills.model.CalculationDataBean;
import com.forketyfork.rainyhills.services.LinearVolumeCalculator;
import jakarta.enterprise.context.SessionScoped;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for checking the interaction between the {@link CalculationBean},
 * {@link CalculationDataBean} and {@link LinearVolumeCalculator}.
 * <p>
 * The {@link CalculationDataBean} contains the input data and the output value of the algorithm.
 * The {@link LinearVolumeCalculator} provides the algorithm for calculating the output value
 * out of the input data. The {@link CalculationBean} provides business logic of the application.
 * <p>
 * Created by Forketyfork on 26.03.17.
 */
public class CalculationBeanIT {

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(CalculationBean.class, CalculationDataBean.class, LinearVolumeCalculator.class)
            .activate(SessionScoped.class)
            .build();

    @Test
    public void whenCalculationActionIsInvoked_thenCalculationDataBeanOutputIsFilled() {
        CalculationBean calculationBean = weld.select(CalculationBean.class).get();
        CalculationDataBean calculationDataBean = weld.select(CalculationDataBean.class).get();

        calculationDataBean.setInput(Arrays.asList(1, 0, 1));

        String result = calculationBean.calculateAction();

        assertEquals("success", result);
        assertEquals(1, calculationDataBean.getResult());
    }

}

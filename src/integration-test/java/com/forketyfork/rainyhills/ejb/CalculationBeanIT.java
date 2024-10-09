package com.forketyfork.rainyhills.ejb;

import com.forketyfork.rainyhills.model.CalculationDataBean;
import com.forketyfork.rainyhills.services.LinearVolumeCalculator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
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
 * Created by Sergey Petunin on 26.03.17.
 */
@RunWith(Arquillian.class)
public class CalculationBeanIT {

    @Inject
    private CalculationBean calculationBean;

    @Inject
    private CalculationDataBean calculationDataBean;

    @Test
    public void whenCalculationActionIsInvoked_thenCalculationDataBeanOutputIsFilled() {
        calculationDataBean.setInput(Arrays.asList(1, 0, 1));

        String result = calculationBean.calculateAction();

        assertEquals("success", result);
        assertEquals(1, calculationDataBean.getResult());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(CalculationBean.class, CalculationDataBean.class, LinearVolumeCalculator.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}

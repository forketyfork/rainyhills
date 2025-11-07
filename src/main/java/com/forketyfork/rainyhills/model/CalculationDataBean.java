package com.forketyfork.rainyhills.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * A POJO containing the user-submitted input and the calculated result.
 * <p>
 * Created by Sergey Petunin on 26.03.17.
 */
@Named("calculationData")
@SessionScoped
@XmlRootElement
public class CalculationDataBean implements Serializable {

    /**
     * The user-submitted list of integer values.
     */
    @XmlElement
    private List<Integer> input;

    /**
     * The result of a computation.
     */
    @XmlElement
    private int result;

    public List<Integer> getInput() {
        return input;
    }

    public void setInput(List<Integer> input) {
        this.input = input;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

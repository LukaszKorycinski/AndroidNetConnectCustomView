package com.kalkulator.lukasz.indexwig;

import java.util.List;

/**
 * Created by Lukasz on 2016-12-29.
 */

public class Statistic {
    static Float  calMinimum(List<Float> input)//if list is empty return 0.0f
    {
        Float minimum=0.0f;
        if(!input.isEmpty())
        {
            minimum=input.get(0);
            for(Float f:input)
                if(minimum>f)minimum=f;
        }
        return minimum;
    }

    static Float calMaximum(List<Float> input)//if list is empty return 0.0f
    {
        Float maximum=0.0f;
        if(!input.isEmpty())
        {
            maximum=input.get(0);
            for(Float f:input)
                if(maximum<f)maximum=f;
        }
        return maximum;
    }

    static Float calAverage(List<Float> input)//if list is empty return 0.0f
    {
        Float average=0.0f;
        if(!input.isEmpty())
        {
            for(Float f:input)
                average+=f;
            average=average/input.size();
        }
        return average;
    }
}

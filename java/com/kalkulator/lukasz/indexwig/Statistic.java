package com.kalkulator.lukasz.indexwig;

import java.util.List;

/**
 * Created by Lukasz on 2016-12-29.
 */

public class Statistic {

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

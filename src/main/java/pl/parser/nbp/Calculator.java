package pl.parser.nbp;

import java.util.ArrayList;

public class Calculator {
    public Double calculateAvg(ArrayList<Double> values)
    {
        Double sum = 0.0;
        for(Double value:values)
        {
            sum+=value;
        }
        return sum/values.size();
    }
    public Double calculateStdDev(ArrayList<Double> values)
    {
        Double avg = calculateAvg(values);
        double sum = 0.0;
        for(Double value:values)
        {
            sum+=Math.pow(value - avg,2);
        }
        sum /= values.size();
        return Math.sqrt(sum);
    }
}

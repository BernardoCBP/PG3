package streams.demos;

import java.io.Serializable;

public class Species implements Serializable {
    private String name;
    private int population;
    private double growthRate;
    public Species(String initialNane,
                   int initialPopulation,
                   double initialgrowthRate){
        name = initialNane;
        if (initialPopulation <0 )
            throw new IllegalArgumentException(
                    "ERROR: Negative population");
        population=initialPopulation;
        growthRate = initialgrowthRate;
    }
    public String toString() {
        return "Name = " + name + '\n' +
                "Population = " + population + '\n' +
                "Growth rate = " + growthRate;
    }

}

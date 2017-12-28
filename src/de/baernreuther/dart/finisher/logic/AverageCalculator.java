package de.baernreuther.dart.finisher.logic;

public class AverageCalculator  implements IAverageCalculator{


    private static double fullScore = 0;
    private static double dartsThrown= 0;
    /* Every throw equals three darts */
    public double calculateAverage(int dartsThrown, int lastScore){
        AverageCalculator.dartsThrown += dartsThrown;
        double darts = (AverageCalculator.dartsThrown/3);
        fullScore += lastScore;
        return  (fullScore/darts);
    }
}

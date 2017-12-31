package de.baernreuther.darts.finisher.finishcalculator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DoubleFinishCalculator implements FinishCalculation {

    private Set<Integer> notThrowable = new HashSet<>(Arrays.asList(169,168,166,165,163,162,159));

   public int scoreLeft(int scoreShot, int scoreLeft){
        int tempScore;
        if(scoreShot < 0 || scoreShot > 180){
            return scoreLeft;
        }

        if(notThrowable.contains(scoreShot)){
            return scoreLeft;
        }

        tempScore = scoreLeft - scoreShot;

        if(tempScore == 1 ||tempScore < 0){
            return scoreLeft;
        }
        return tempScore;
    }
}

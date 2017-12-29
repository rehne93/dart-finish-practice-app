package de.baernreuther.darts.finisher.doublecounter;

public class DoubleCounter implements FinishPercentageCounter{

    private static int doublesHit = 0;
    private static int doublesMissed = 0;


    public int getDoublesMissed(int doublesMissed){
        DoubleCounter.doublesMissed += doublesMissed;
       return DoubleCounter.doublesMissed;
    }

    public int getDoublesHit(boolean isFinished){
        if(isFinished){
            doublesHit+=1;
        }
        return doublesHit;
    }

}

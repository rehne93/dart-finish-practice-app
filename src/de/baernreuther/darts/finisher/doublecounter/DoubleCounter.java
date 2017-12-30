package de.baernreuther.darts.finisher.doublecounter;

public class DoubleCounter implements FinishPercentageCounter{

    private static int doublesHit = 0;
    private static int doubleShot = 0;


    public int getDoubleShot(int doublesShot){
        DoubleCounter.doubleShot += doublesShot;
       return DoubleCounter.doubleShot;
    }

    public int getDoublesHit(boolean isFinished){
        if(isFinished){
            doublesHit+=1;

        }
        return doublesHit;
    }

}

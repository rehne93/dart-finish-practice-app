package de.baernreuther.darts.finisher.doublecounter;

public interface FinishPercentageCounter {

    int getDoubleShot(int doublesMissed);

    int getDoublesHit(boolean isFinished);

}

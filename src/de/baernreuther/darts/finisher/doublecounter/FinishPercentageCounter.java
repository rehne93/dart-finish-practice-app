package de.baernreuther.darts.finisher.doublecounter;

public interface FinishPercentageCounter {

    int getDoublesMissed(int doublesMissed);

    int getDoublesHit(boolean isFinished);

}

package de.baernreuther.darts.finisher.doublecounter;

public class DoubleCounter implements FinishPercentageCounter {

    private static int doublesHit = 0;
    private static int doubleShot = 0;
    private int currentRound = 1;


    public int getDoubleShot(int doublesShot) {
        DoubleCounter.doubleShot += doublesShot;
        return DoubleCounter.doubleShot;
    }

    public int getCurrentRound() {
        currentRound++;
        return currentRound;
    }

    public void resetRound() {
        currentRound = 0;
    }

    public int getDoublesHit(boolean isFinished) {
        if (isFinished) {
            doublesHit += 1;
        }
        return doublesHit;
    }

    public double getAverage() {
        double dHit = Double.valueOf(doublesHit);
        double dShot = Double.valueOf(doubleShot);

        if (dShot == 0 || dHit == 0) {
            return 0;
        }
        return (dHit / dShot * 100);
    }

}

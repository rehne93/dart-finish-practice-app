package de.baernreuther.darts.finisher.numbergenerator;

public class X01NumberGenerator implements NumberGenerator {

    private int numberToFinish = 301;

    public X01NumberGenerator(int numberToFinish) {
        this.numberToFinish = numberToFinish;
    }

    @Override
    public int generateNumberToFinish() {
        return 301;
    }
}

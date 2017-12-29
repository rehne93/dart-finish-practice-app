package de.baernreuther.darts.finisher.numbergenerator;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleFinishNumberGenerator implements NumberGenerator {

    public int generateNumberToFinish(){
        return ThreadLocalRandom.current().nextInt(2, 170 + 1);
    }
}

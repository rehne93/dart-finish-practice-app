package de.baernreuther.darts.finisher.numbergenerator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author  René Bärnreuther
 * Returns a fully random number between 2 and 170.
 */
public class DoubleFinishNumberGenerator implements NumberGenerator {

    public int generateNumberToFinish(){
        return ThreadLocalRandom.current().nextInt(2, 170 + 1);
    }
}

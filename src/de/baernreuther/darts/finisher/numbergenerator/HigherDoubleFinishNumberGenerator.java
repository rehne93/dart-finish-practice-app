package de.baernreuther.darts.finisher.numbergenerator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author René Bärnreuther
 * returns a Balanced double number which is above 60 in 4 out of 5 cases.
 */
public class HigherDoubleFinishNumberGenerator implements NumberGenerator {

    @Override
    public int generateNumberToFinish() {
        return ThreadLocalRandom.current().nextInt(61, 170 + 1);

    }


}

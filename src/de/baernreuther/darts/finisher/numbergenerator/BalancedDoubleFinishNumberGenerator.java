package de.baernreuther.darts.finisher.numbergenerator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author René Bärnreuther
 * returns a Balanced double number which is mostly above 50.
 */
public class BalancedDoubleFinishNumberGenerator implements NumberGenerator{

    @Override
    public int generateNumberToFinish() {
        int ranDecider = ThreadLocalRandom.current().nextInt(2, 170 + 1);


        if(ranDecider < 81){
            return ThreadLocalRandom.current().nextInt(50, 170 + 1);
        }else{
            return ThreadLocalRandom.current().nextInt(2, 60);
        }
    }




}

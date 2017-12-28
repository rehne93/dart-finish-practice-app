package de.baernreuther.dart.finisher.logic;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleFinishNumberGenerator implements INumberGenerator {


    public int generateFinish(){
        return ThreadLocalRandom.current().nextInt(2, 170 + 1);

    }
}

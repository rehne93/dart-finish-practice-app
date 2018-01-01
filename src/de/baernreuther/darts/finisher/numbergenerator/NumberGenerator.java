package de.baernreuther.darts.finisher.numbergenerator;

public interface NumberGenerator {

    default int generateNumberToFinish(){
        return 170;
    }
}

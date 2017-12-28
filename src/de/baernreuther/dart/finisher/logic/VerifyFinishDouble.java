package de.baernreuther.dart.finisher.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VerifyFinishDouble implements IVerifyFinish {

    Set impossibleScores = new HashSet<Integer>(Arrays.asList(163,166,169,172,173,175,176,178,179));

    public int verifyFinish(int thrownScore, int scoreLeft){
        if(thrownScore > 180 || thrownScore < 0){
            return scoreLeft;
        }

        if(impossibleScores.contains(thrownScore)){
            return scoreLeft;
        }

        int tmpScoreLeft = scoreLeft-thrownScore;
        if(tmpScoreLeft == 1 || tmpScoreLeft < 0){
            return scoreLeft;
        }
        return tmpScoreLeft;
    }

}

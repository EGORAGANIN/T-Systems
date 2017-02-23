package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsequence {



    public boolean find(List x, List y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }
        if (x.size()==0){
            return true;
        }

        int countEqualsObj = 0;
        int k = 0;
        boolean result = false;

        for (int i = 0; i < x.size(); i++) {

            if (y.contains(x.get(i))) {

                for (int j = k; j < y.size(); j++) {

                    if (x.get(i).equals(y.get(j))) {
                        countEqualsObj++;
                        k = j+1;
                        if (x.size() == countEqualsObj) {
                            return true;
                        }
                        break;
                    }



                }

            } else {
                result = false;
            }


        }
        return result;
    }
}

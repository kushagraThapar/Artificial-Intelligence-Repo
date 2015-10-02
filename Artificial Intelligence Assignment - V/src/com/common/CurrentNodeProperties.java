package com.common;

/**
 * Created by kushagrathapar on 10/2/15.
 */
public class CurrentNodeProperties {

    public int heuristicValue;
    public String currentState;

    public CurrentNodeProperties(int heuristicValue, String str) {
        this.heuristicValue = heuristicValue;
        this.currentState = str;
    }

}

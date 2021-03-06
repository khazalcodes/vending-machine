package com.khazalcodes.enums;

import com.khazalcodes.exceptions.NonExistantActionException;
import com.khazalcodes.interfaces.base.Action;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A coin action must not only represent the a numbered choice but also the value of the coin in choosing. Therefore,
 * this class contains a Value field which can be accessed to return the enum's monetary value
 * */
public enum CoinAction implements Action{
    TWO_POUNDS(1, 2.00),
    ONE_POUND(2, 1.00),
    FIFTY_P(3, 0.50),
    TWENTY_P(4, 0.20),
    TEN_P(5, 0.10),
    FIVE_P(6, 0.05),
    TWO_P(7, 0.02),
    ONE_P(8, 0.01),
    FINISH(9, -0.00);

    public final int Id;
    public final double Value;

    CoinAction(int id, double worth) {
        this.Id = id;
        this.Value = worth;
    }

    /**
     * This design pattern was sourced from here: http://dan.clarke.name/2011/07/enum-in-java-with-int-conversion/
     * To convert integers to enums on the fly, we need to have some sort of mapping between the integer and the Enums
     * Value field. This can be done with a switch case which is brittle. Instead, we can instantiate a hashmap and then
     * write a static code block which runs at the time of loading a class, even before instantiation.
     * Thus, we code will get all of the possible enum values and for each of those, we use their IntValues as the key
     * with the enum value as the value for the HashMap entry. Then we can create a simple method that takes the int
     * we want to convert and uses the parameter as the key to retrieve the enum we want to return.
     * */
    private static final Map<Integer, CoinAction> map = new HashMap<>();
    static { Arrays.stream(CoinAction.values()).forEach(e -> map.put(e.Id, e)); }

    public static CoinAction fromInt(int num) throws NonExistantActionException {
        if (!map.containsKey(num)) {
            throw new NonExistantActionException("\nThe action you chose does not exist");
        }

        return map.get(num);
    }
}

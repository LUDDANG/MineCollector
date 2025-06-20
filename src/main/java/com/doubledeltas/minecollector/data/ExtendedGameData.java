package com.doubledeltas.minecollector.data;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class ExtendedGameData {
    private static final String KEY_SCORE_ADJUSTMENT = "score_bonus";
    private BigInteger scoreBonus;

    public ExtendedGameData(Map<String, Object> map) {
        this.scoreBonus = map.containsKey(KEY_SCORE_ADJUSTMENT)
                ? new BigInteger((String) map.get(KEY_SCORE_ADJUSTMENT))
                : BigInteger.ZERO;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(KEY_SCORE_ADJUSTMENT, this.scoreBonus.toString());
        return map;
    }
}

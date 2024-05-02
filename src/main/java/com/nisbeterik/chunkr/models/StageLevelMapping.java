package com.nisbeterik.chunkr.models;

import java.util.*;

public class StageLevelMapping {

    private static final Map<Integer, List<Integer>> stageToLevelsMap = initializeStageToLevelsMap();

    private static Map<Integer, List<Integer>> initializeStageToLevelsMap() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, Arrays.asList(2, 1));
        map.put(2, Arrays.asList(3, 1));
        map.put(3, Arrays.asList(2, 1));
        map.put(4, Arrays.asList(4, 1));
        map.put(5, Arrays.asList(2, 1));
        map.put(6, Arrays.asList(3, 1));
        map.put(7, Arrays.asList(2, 1));
        map.put(8, List.of(1));
        map.put(9, Arrays.asList(2, 1));
        map.put(10, Arrays.asList(3, 1));
        map.put(11, Arrays.asList(2, 1));
        map.put(12, Arrays.asList(5, 1));
        map.put(13, Arrays.asList(4, 2, 1));
        map.put(14, Arrays.asList(3, 1));
        map.put(15, Arrays.asList(2, 1));
        map.put(16, Arrays.asList(2, 1));
        map.put(17, Arrays.asList(2, 1));
        map.put(18, Arrays.asList(3, 1));
        map.put(19, Arrays.asList(2, 1));
        map.put(20, Arrays.asList(4, 1));
        map.put(21, Arrays.asList(2, 1));
        map.put(22, Arrays.asList(3, 1));
        map.put(23, Arrays.asList(6, 1));
        map.put(24, Arrays.asList(2, 1));
        map.put(25, Arrays.asList(3, 1));
        map.put(26, Arrays.asList(2, 1));
        map.put(27, Arrays.asList(5, 1));
        map.put(28, Arrays.asList(4, 2, 1));
        map.put(29, Arrays.asList(3, 1));
        map.put(30, Arrays.asList(2, 1));
        map.put(31, List.of(1));
        map.put(32, Arrays.asList(2, 1));
        map.put(33, Arrays.asList(3, 1));
        map.put(34, Arrays.asList(2, 1));
        map.put(35, Arrays.asList(4, 1));
        map.put(36, Arrays.asList(2, 1));
        map.put(37, Arrays.asList(3, 1));
        map.put(38, Arrays.asList(2, 1));
        map.put(39, List.of(1));
        map.put(40, Arrays.asList(2, 1));
        map.put(41, Arrays.asList(3, 1));
        map.put(42, Arrays.asList(2, 1));
        map.put(43, Arrays.asList(5, 1));
        map.put(44, Arrays.asList(4, 2, 1));
        map.put(45, Arrays.asList(3, 1));
        map.put(46, Arrays.asList(2, 1));
        map.put(47, List.of(1));
        map.put(48, Arrays.asList(2, 1));
        map.put(49, Arrays.asList(3, 1));
        map.put(50, Arrays.asList(2, 1));
        map.put(51, Arrays.asList(4, 1));
        map.put(52, Arrays.asList(2, 1));
        map.put(53, Arrays.asList(3, 1));
        map.put(54, Arrays.asList(2, 1));
        map.put(55, Arrays.asList(7, 1));
        map.put(56, Arrays.asList(2, 1));
        map.put(57, Arrays.asList(3, 1));
        map.put(58, Arrays.asList(6, 2, 1));
        map.put(59, Arrays.asList(5, 1));
        map.put(60, Arrays.asList(4, 2, 1));
        map.put(61, Arrays.asList(3, 1));
        map.put(62, Arrays.asList(2, 1));
        map.put(63, List.of(1));
        map.put(64, Arrays.asList(2, 1));


        return map;
    }

    public static List<Integer> getTargetLevelsForStage(int stage) {
        return stageToLevelsMap.getOrDefault(stage, Collections.emptyList());
    }
}

package com.parkinglot.strategies;

import com.parkinglot.models.SlotAllotmentStrategyType;

public class SlotAssignmentStrategyFactory {
    public static SlotAssignmentStrategy getSlotForType(
            SlotAllotmentStrategyType slotAllotmentStrategyType){
        return new RandomSlotAssignmentStrategy();
    }
}
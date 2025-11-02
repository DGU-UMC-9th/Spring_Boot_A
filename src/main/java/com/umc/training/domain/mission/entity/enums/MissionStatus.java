package com.umc.training.domain.mission.entity.enums;

import java.util.EnumSet;
import java.util.Set;

public enum MissionStatus {
    COMPLETED, IN_PROGRESS, IN_THE_WORKS;

    public static final Set<MissionStatus> ACTIVE_STATUSES =
            EnumSet.of(IN_PROGRESS, IN_THE_WORKS);
}

package com.example.spring_boot_a.domain.entity.etc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.spring_boot_a.domain.entity.mission.Mission;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMission is a Querydsl query type for Mission
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMission extends EntityPathBase<Mission> {

    private static final long serialVersionUID = -558912296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMission mission = new QMission("mission");

    public final StringPath conditional = createString("conditional");

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    public final DatePath<java.time.LocalDate> deadline = createDate("deadline", java.time.LocalDate.class);

    public final NumberPath<Long> missionId = createNumber("missionId", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final com.example.spring_boot_a.domain.entity.QStore store;

    public final SetPath<com.example.spring_boot_a.domain.entity.user.UserMission, com.example.spring_boot_a.domain.entity.user.QUserMission> userMissions = this.<com.example.spring_boot_a.domain.entity.user.UserMission, com.example.spring_boot_a.domain.entity.user.QUserMission>createSet("userMissions", com.example.spring_boot_a.domain.entity.user.UserMission.class, com.example.spring_boot_a.domain.entity.user.QUserMission.class, PathInits.DIRECT2);

    public QMission(String variable) {
        this(Mission.class, forVariable(variable), INITS);
    }

    public QMission(Path<? extends Mission> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMission(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMission(PathMetadata metadata, PathInits inits) {
        this(Mission.class, metadata, inits);
    }

    public QMission(Class<? extends Mission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new com.example.spring_boot_a.domain.entity.QStore(forProperty("store"), inits.get("store")) : null;
    }

}


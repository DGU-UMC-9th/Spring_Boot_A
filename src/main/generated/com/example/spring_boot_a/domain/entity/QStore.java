package com.example.spring_boot_a.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.spring_boot_a.domain.entity.mission.Mission;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 1205412615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final StringPath detailAddress = createString("detailAddress");

    public final com.example.spring_boot_a.domain.entity.etc.QLocation location;

    public final NumberPath<Long> managerNumber = createNumber("managerNumber", Long.class);

    public final SetPath<Mission, com.example.spring_boot_a.domain.entity.etc.QMission> missions = this.<Mission, com.example.spring_boot_a.domain.entity.etc.QMission>createSet("missions", Mission.class, com.example.spring_boot_a.domain.entity.etc.QMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final SetPath<com.example.spring_boot_a.domain.entity.review.Review, com.example.spring_boot_a.domain.entity.review.QReview> reviews = this.<com.example.spring_boot_a.domain.entity.review.Review, com.example.spring_boot_a.domain.entity.review.QReview>createSet("reviews", com.example.spring_boot_a.domain.entity.review.Review.class, com.example.spring_boot_a.domain.entity.review.QReview.class, PathInits.DIRECT2);

    public final NumberPath<Long> storeId = createNumber("storeId", Long.class);

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new com.example.spring_boot_a.domain.entity.etc.QLocation(forProperty("location")) : null;
    }

}


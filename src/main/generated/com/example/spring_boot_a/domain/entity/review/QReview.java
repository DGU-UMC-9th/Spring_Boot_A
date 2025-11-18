package com.example.spring_boot_a.domain.entity.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 247763732L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    public final SetPath<com.example.spring_boot_a.domain.entity.etc.ReviewPhoto, com.example.spring_boot_a.domain.entity.etc.QReviewPhoto> photos = this.<com.example.spring_boot_a.domain.entity.etc.ReviewPhoto, com.example.spring_boot_a.domain.entity.etc.QReviewPhoto>createSet("photos", com.example.spring_boot_a.domain.entity.etc.ReviewPhoto.class, com.example.spring_boot_a.domain.entity.etc.QReviewPhoto.class, PathInits.DIRECT2);

    public final SetPath<com.example.spring_boot_a.domain.entity.etc.Reply, com.example.spring_boot_a.domain.entity.etc.QReply> replies = this.<com.example.spring_boot_a.domain.entity.etc.Reply, com.example.spring_boot_a.domain.entity.etc.QReply>createSet("replies", com.example.spring_boot_a.domain.entity.etc.Reply.class, com.example.spring_boot_a.domain.entity.etc.QReply.class, PathInits.DIRECT2);

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public final NumberPath<Float> star = createNumber("star", Float.class);

    public final com.example.spring_boot_a.domain.entity.QStore store;

    public final com.example.spring_boot_a.domain.entity.user.QUser user;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new com.example.spring_boot_a.domain.entity.QStore(forProperty("store"), inits.get("store")) : null;
        this.user = inits.isInitialized("user") ? new com.example.spring_boot_a.domain.entity.user.QUser(forProperty("user")) : null;
    }

}


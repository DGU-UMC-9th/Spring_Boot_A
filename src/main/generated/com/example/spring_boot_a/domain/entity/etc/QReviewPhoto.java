package com.example.spring_boot_a.domain.entity.etc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewPhoto is a Querydsl query type for ReviewPhoto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewPhoto extends EntityPathBase<ReviewPhoto> {

    private static final long serialVersionUID = 1892209414L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewPhoto reviewPhoto = new QReviewPhoto("reviewPhoto");

    public final StringPath photoUrl = createString("photoUrl");

    public final com.example.spring_boot_a.domain.entity.review.QReview review;

    public final NumberPath<Long> reviewPhotoId = createNumber("reviewPhotoId", Long.class);

    public QReviewPhoto(String variable) {
        this(ReviewPhoto.class, forVariable(variable), INITS);
    }

    public QReviewPhoto(Path<? extends ReviewPhoto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewPhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewPhoto(PathMetadata metadata, PathInits inits) {
        this(ReviewPhoto.class, metadata, inits);
    }

    public QReviewPhoto(Class<? extends ReviewPhoto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new com.example.spring_boot_a.domain.entity.review.QReview(forProperty("review"), inits.get("review")) : null;
    }

}


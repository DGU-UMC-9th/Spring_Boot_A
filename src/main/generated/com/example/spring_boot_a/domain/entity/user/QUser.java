package com.example.spring_boot_a.domain.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -699737228L;

    public static final QUser user = new QUser("user");

    public final EnumPath<com.example.spring_boot_a.domain.entity.enums.AddressGu> address = createEnum("address", com.example.spring_boot_a.domain.entity.enums.AddressGu.class);

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final DateTimePath<java.time.Instant> deletedAt = createDateTime("deletedAt", java.time.Instant.class);

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath email = createString("email");

    public final EnumPath<com.example.spring_boot_a.domain.entity.enums.Gender> gender = createEnum("gender", com.example.spring_boot_a.domain.entity.enums.Gender.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final SetPath<com.example.spring_boot_a.domain.entity.review.Review, com.example.spring_boot_a.domain.entity.review.QReview> reviews = this.<com.example.spring_boot_a.domain.entity.review.Review, com.example.spring_boot_a.domain.entity.review.QReview>createSet("reviews", com.example.spring_boot_a.domain.entity.review.Review.class, com.example.spring_boot_a.domain.entity.review.QReview.class, PathInits.DIRECT2);

    public final EnumPath<com.example.spring_boot_a.domain.entity.enums.SocialLoginType> socialType = createEnum("socialType", com.example.spring_boot_a.domain.entity.enums.SocialLoginType.class);

    public final StringPath socialUid = createString("socialUid");

    public final DateTimePath<java.time.Instant> updatedAt = createDateTime("updatedAt", java.time.Instant.class);

    public final SetPath<UserFood, QUserFood> userFoods = this.<UserFood, QUserFood>createSet("userFoods", UserFood.class, QUserFood.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final SetPath<UserMission, QUserMission> userMissions = this.<UserMission, QUserMission>createSet("userMissions", UserMission.class, QUserMission.class, PathInits.DIRECT2);

    public final SetPath<UserTerm, QUserTerm> userTerms = this.<UserTerm, QUserTerm>createSet("userTerms", UserTerm.class, QUserTerm.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}


package com.example.spring_boot_a.domain.entity.etc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = 1124304018L;

    public static final QFood food = new QFood("food");

    public final NumberPath<Long> foodId = createNumber("foodId", Long.class);

    public final EnumPath<com.example.spring_boot_a.domain.entity.enums.FoodType> name = createEnum("name", com.example.spring_boot_a.domain.entity.enums.FoodType.class);

    public final SetPath<com.example.spring_boot_a.domain.entity.user.UserFood, com.example.spring_boot_a.domain.entity.user.QUserFood> userFoods = this.<com.example.spring_boot_a.domain.entity.user.UserFood, com.example.spring_boot_a.domain.entity.user.QUserFood>createSet("userFoods", com.example.spring_boot_a.domain.entity.user.UserFood.class, com.example.spring_boot_a.domain.entity.user.QUserFood.class, PathInits.DIRECT2);

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}


package com.example.spring_boot_a.domain.entity.etc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLocation is a Querydsl query type for Location
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocation extends EntityPathBase<Location> {

    private static final long serialVersionUID = -1333535255L;

    public static final QLocation location = new QLocation("location");

    public final NumberPath<Long> locationId = createNumber("locationId", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<com.example.spring_boot_a.domain.entity.Store, com.example.spring_boot_a.domain.entity.QStore> stores = this.<com.example.spring_boot_a.domain.entity.Store, com.example.spring_boot_a.domain.entity.QStore>createSet("stores", com.example.spring_boot_a.domain.entity.Store.class, com.example.spring_boot_a.domain.entity.QStore.class, PathInits.DIRECT2);

    public QLocation(String variable) {
        super(Location.class, forVariable(variable));
    }

    public QLocation(Path<? extends Location> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocation(PathMetadata metadata) {
        super(Location.class, metadata);
    }

}


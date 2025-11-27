package com.example.spring_boot_a.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTerm is a Querydsl query type for Term
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTerm extends EntityPathBase<Term> {

    private static final long serialVersionUID = -653836922L;

    public static final QTerm term = new QTerm("term");

    public final EnumPath<com.example.spring_boot_a.domain.entity.enums.TermType> name = createEnum("name", com.example.spring_boot_a.domain.entity.enums.TermType.class);

    public final NumberPath<Long> termId = createNumber("termId", Long.class);

    public final SetPath<com.example.spring_boot_a.domain.entity.user.UserTerm, com.example.spring_boot_a.domain.entity.user.QUserTerm> userTerms = this.<com.example.spring_boot_a.domain.entity.user.UserTerm, com.example.spring_boot_a.domain.entity.user.QUserTerm>createSet("userTerms", com.example.spring_boot_a.domain.entity.user.UserTerm.class, com.example.spring_boot_a.domain.entity.user.QUserTerm.class, PathInits.DIRECT2);

    public QTerm(String variable) {
        super(Term.class, forVariable(variable));
    }

    public QTerm(Path<? extends Term> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTerm(PathMetadata metadata) {
        super(Term.class, metadata);
    }

}


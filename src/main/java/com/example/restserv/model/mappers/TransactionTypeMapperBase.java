// This file was automatically generated; do not edit manually
package com.example.restserv.model.mappers;

import com.example.restserv.model.TransactionType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * MyBatis mapper for {@code TRANSACTION_TYPE}
 */
@Mapper
@Repository
@Primary
public interface TransactionTypeMapperBase {
    List<TransactionType> selectAll();

    @Nullable
    TransactionType selectByPk(@Param("enumeration") String enumeration);

    void insert(TransactionType item);

    int delete(TransactionType item);
}

package com.example.restserv.model.mappers;

import com.example.restserv.model.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
@Primary
public interface TransactionMapper {
    List<Transaction> selectAll();

    @Nullable
    Transaction selectByPk(@Param("transactionId") String transactionId);

    void insert(Transaction item);

    int update(Transaction item);
}

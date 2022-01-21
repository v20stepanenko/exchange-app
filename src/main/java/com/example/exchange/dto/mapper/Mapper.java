package com.example.exchange.dto.mapper;

public interface Mapper<D,T> {
    D mapToDto(T t);
}

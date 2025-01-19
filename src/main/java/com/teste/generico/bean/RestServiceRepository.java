package com.teste.generico.bean;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestServiceRepository<T, ID>  {

    T save(T dto);

    Page<T> findAll(Pageable pageable);

    Optional<List<T>> findAll();

    Optional<T> findOne(ID id);

    void delete(ID id);

    void deleteEntity(T dto);
}

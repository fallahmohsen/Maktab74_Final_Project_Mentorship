package com.example.online_quiz2.base.service;


import com.example.online_quiz2.base.domian.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseEntityService<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E e) throws FileNotFoundException;

    long count();

    Optional<E> findById(ID id);

    List<E> findAll();

    void deleteById(ID id);

    Page<E> findAll(Pageable pageable);

    boolean existsById(ID id);


}

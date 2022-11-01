package com.example.online_quiz2.base.repository;


import com.example.online_quiz2.base.domian.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseEntityRepository<E extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

}

package com.gharib.banking.Abstract;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID, Request, Response> {

    Optional<Response> findById(ID id);

    List<Response> findAll();

    Response create(Request entity);

    Response update(ID id, Request entity);

    void delete(ID id);
}
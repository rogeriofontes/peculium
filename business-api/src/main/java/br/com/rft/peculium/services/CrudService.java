package br.com.rft.peculium.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<E, I extends Serializable> {
    public E save(E e);

    public Boolean delete(I id);

    public E edit(E e, I id);

    public E find(I id);

    public List<E> getAll();
    
    public Page<E> findAllPageable(Pageable pageable);
}

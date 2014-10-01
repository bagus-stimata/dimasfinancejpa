/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dimas.finance.jpa.dao.generic;

import java.io.Serializable;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author bagus
 */
public interface GenericJpaService<T, ID extends Serializable> {
     public List<T> findAll() throws DataAccessException;
     public T findById(Serializable ID) throws DataAccessException;
     //Gak bisa jalan
     public List<T> findAllById(Serializable ID) throws DataAccessException;
     public void createObject(T domain) throws DataAccessException;
     public void updateObject(T domain) throws DataAccessException;
     public void removeObject(T domain) throws DataAccessException;
     public long count() throws DataAccessException;
}

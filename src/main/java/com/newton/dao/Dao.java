package com.newton.dao;

import java.util.List;

public interface Dao<T> {

//  void add(int no_of_slots);

//  void remove(int no_of_slots);

  void save(T object);

  T get(Object id);

  List<T> search(Object request);

  void delete(Object id);
}

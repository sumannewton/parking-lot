package com.newton.dao;

import com.newton.model.Slot;
import com.newton.model.SlotSearch;
import java.util.List;

public class InMemorySlotDao implements Dao<Slot> {

  /*
  Acts like a client to interact with in-memory database.
   */
  InMemoryDB database;

  public InMemorySlotDao(InMemoryDB database) {
    this.database = database;
    this.database.initialize();
  }

  public void add(int no_of_slots) {
    while ((no_of_slots-- > 0)) {
      Slot slot = new Slot();
      slot.setId(database.SLOTS_TABLE.size() + 1);
      database.SLOTS_TABLE.add(slot);
    }
  }

  public void save(Slot slot) {
    database.SLOTS_TABLE.set(slot.getId() - 1, slot);
  }

  public Slot get(Object slot_id) {
    return database.SLOTS_TABLE.get((int) slot_id - 1);
  }

  public List<Slot> search(Object searchRequest) {
    SlotSearch slotSearchRequest = (SlotSearch) searchRequest;
    List<Slot> result = database.SLOTS_TABLE;
    if (slotSearchRequest.getStatus() != null) {
      result = database.filterByStatus(result, slotSearchRequest.getStatus());
    }
    return result;
  }

  public void delete(Object slot_id) {
    database.SLOTS_TABLE.remove((int)slot_id - 1);
  }
}

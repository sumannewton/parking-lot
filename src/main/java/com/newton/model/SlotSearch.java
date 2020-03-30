package com.newton.model;

public class SlotSearch {

  private Status status = null;

  public Status getStatus() {
    return status;
  }

  public SlotSearch setStatus(Status status) {
    this.status = status;
    return this;
  }

  public static SlotSearchBuilder builder() {
    return new SlotSearchBuilder();
  }

  public static class SlotSearchBuilder {

    SlotSearch slotSearch;

    public SlotSearchBuilder() {
      slotSearch = new SlotSearch();
    }

    public SlotSearchBuilder status(Status status) {
      slotSearch.setStatus(status);
      return this;
    }

    public SlotSearch build() {
      return this.slotSearch;
    }
  }
}

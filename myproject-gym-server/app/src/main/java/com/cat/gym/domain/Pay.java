package com.cat.gym.domain;

import java.sql.Date;

public class Pay {
  private String id;
  private int select;
  private String join;
  private String rental;
  private String locker;
  private Date startDate;

  public Pay() {}

  public Pay(String csv) {
    String[] fields = csv.split(",");
    this.setId(fields[0]);
    this.setSelect(Integer.parseInt(fields[1]));
    this.setJoin(fields[2]);
    this.setRental(fields[3]);
    this.setLocker(fields[4]);
    this.setStartDate(Date.valueOf(fields[5]));
  }

  @Override
  public String toString() {
    return "Pay [id=" + id + ", select=" + select + ", join=" + join + ", rental=" + rental
        + ", locker=" + locker + ", startDate=" + startDate + "]";
  }

  public String toCsvString() {
    return String.format("%s,%d,%s,%s,%s,%s", 
        this.getId(), 
        this.getSelect(), 
        this.getJoin(), 
        this.getRental(), 
        this.getLocker(), 
        this.getStartDate());
  }

  public static Pay valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Pay p = new Pay();
    p.setId(fields[0]);
    p.setSelect(Integer.parseInt(fields[1]));
    p.setJoin(fields[2]);
    p.setRental(fields[3]);
    p.setLocker(fields[4]);
    p.setStartDate(Date.valueOf(fields[5]));
    return p;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((join == null) ? 0 : join.hashCode());
    result = prime * result + ((locker == null) ? 0 : locker.hashCode());
    result = prime * result + ((rental == null) ? 0 : rental.hashCode());
    result = prime * result + select;
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pay other = (Pay) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (join == null) {
      if (other.join != null)
        return false;
    } else if (!join.equals(other.join))
      return false;
    if (locker == null) {
      if (other.locker != null)
        return false;
    } else if (!locker.equals(other.locker))
      return false;
    if (rental == null) {
      if (other.rental != null)
        return false;
    } else if (!rental.equals(other.rental))
      return false;
    if (select != other.select)
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getSelect() {
    return select;
  }
  public void setSelect(int select) {
    this.select = select;
  }
  public String getJoin() {
    return join;
  }
  public void setJoin(String join) {
    this.join = join;
  }
  public String getRental() {
    return rental;
  }
  public void setRental(String rental) {
    this.rental = rental;
  }
  public String getLocker() {
    return locker;
  }
  public void setLocker(String locker) {
    this.locker = locker;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
}

package com.cat.gym.domain;

import java.sql.Date;

public class Pay {
  private int no;
  private String memberName;
  private int membership;
  private int newMember;
  private int rental;
  private int locker;
  private int pt;
  private Date startDate;
  private Date endDate;

  public Pay() {}

  public Pay(String csv) {
    String[] fields = csv.split(",");
    this.setNo(Integer.parseInt(fields[0]));
    this.setMemberName(fields[1]);
    this.setMembership(Integer.parseInt(fields[2]));
    this.setNewMember(Integer.parseInt(fields[3]));
    this.setRental(Integer.parseInt(fields[4]));
    this.setLocker(Integer.parseInt(fields[5]));
    this.setPt(Integer.parseInt(fields[6]));
    this.setStartDate(Date.valueOf(fields[7]));
    this.setEndDate(Date.valueOf(fields[8]));
  }

  @Override
  public String toString() {
    return "Pay [no=" + no + ", memberName=" + memberName + ", membership=" + membership
        + ", newMember=" + newMember + ", rental=" + rental + ", locker=" + locker + ", pt=" + pt
        + ", startDate=" + startDate + ", endDate=" + endDate + "]";
  }

  public String toCsvString() {
    return String.format("%d,%s,%d,%d,%d,%d,%d,%s,%s", 
        this.getNo(), 
        this.getMemberName(), 
        this.getMembership(),
        this.getNewMember(),
        this.getRental(), 
        this.getLocker(), 
        this.getPt(),
        this.getStartDate(),
        this.getEndDate());
  }

  public static Pay valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Pay p = new Pay();
    p.setNo(Integer.parseInt(fields[0]));
    p.setMemberName(fields[1]);
    p.setMembership(Integer.parseInt(fields[2]));
    p.setNewMember(Integer.parseInt(fields[3]));
    p.setRental(Integer.parseInt(fields[4]));
    p.setLocker(Integer.parseInt(fields[5]));
    p.setPt(Integer.parseInt(fields[6]));
    p.setStartDate(Date.valueOf(fields[7]));
    p.setEndDate(Date.valueOf(fields[8]));
    return p;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
    result = prime * result + locker;
    result = prime * result + ((memberName == null) ? 0 : memberName.hashCode());
    result = prime * result + membership;
    result = prime * result + newMember;
    result = prime * result + no;
    result = prime * result + pt;
    result = prime * result + rental;
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
    if (endDate == null) {
      if (other.endDate != null)
        return false;
    } else if (!endDate.equals(other.endDate))
      return false;
    if (locker != other.locker)
      return false;
    if (memberName == null) {
      if (other.memberName != null)
        return false;
    } else if (!memberName.equals(other.memberName))
      return false;
    if (membership != other.membership)
      return false;
    if (newMember != other.newMember)
      return false;
    if (no != other.no)
      return false;
    if (pt != other.pt)
      return false;
    if (rental != other.rental)
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getMemberName() {
    return memberName;
  }
  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }
  public int getMembership() {
    return membership;
  }
  public void setMembership(int membership) {
    this.membership = membership;
  }
  public int getNewMember() {
    return newMember;
  }
  public void setNewMember(int newMember) {
    this.newMember = newMember;
  }
  public int getRental() {
    return rental;
  }
  public void setRental(int rental) {
    this.rental = rental;
  }
  public int getLocker() {
    return locker;
  }
  public void setLocker(int locker) {
    this.locker = locker;
  }
  public int getPt() {
    return pt;
  }
  public void setPt(int pt) {
    this.pt = pt;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public static String getMembershipLabel(int select) {
    switch (select) {
      case 1:
        return "3개월(90,000원)";
      case 2:
        return "6개월(150,000원)";
      case 3:
        return "1년(240,000원)";
      default:
        return "1개월(80,000원)";
    }
  }

  public static String getNewMemberLabel(int select) {
    switch (select) {
      case 1:
        return "Y";
      default:
        return "N";
    }
  }

  public static String getRentalLabel(int select) {
    switch (select) {
      case 1:
        return "Y";
      default:
        return "N";
    }
  }

  public static String getLockerLabel(int select) {
    switch (select) {
      case 1:
        return "Y";
      default:
        return "N";
    }
  }

  public static String getPtLabel(int select) {
    switch (select) {
      case 1:
        return "Y";
      default:
        return "N";
    }
  }
}













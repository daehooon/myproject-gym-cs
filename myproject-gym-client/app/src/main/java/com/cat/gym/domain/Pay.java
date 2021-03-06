package com.cat.gym.domain;

import java.sql.Date;

public class Pay {
  private int no;
  private Member owner;
  private int membership;
  private int newMember;
  private int rental;
  private int locker;
  private int pt;
  private Date startDate;
  private Date endDate;
  private int trainerNo;
  private String trainerName;

  @Override
  public String toString() {
    return "Pay [no=" + no + ", owner=" + owner + ", membership=" + membership + ", newMember="
        + newMember + ", rental=" + rental + ", locker=" + locker + ", pt=" + pt + ", startDate="
        + startDate + ", endDate=" + endDate + ", trainerNo=" + trainerNo + ", trainerName="
        + trainerName + "]";
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public Member getOwner() {
    return owner;
  }
  public void setOwner(Member owner) {
    this.owner = owner;
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
  public int getTrainerNo() {
    return trainerNo;
  }
  public void setTrainerNo(int trainerNo) {
    this.trainerNo = trainerNo;
  }
  public String getTrainerName() {
    return trainerName;
  }
  public void setTrainerName(String trainerName) {
    this.trainerName = trainerName;
  }

  public static String getMembershipLabel(int select) {
    switch (select) {
      case 1:
        return "3??????(90,000???)";
      case 2:
        return "6??????(150,000???)";
      case 3:
        return "1???(240,000???)";
      default:
        return "1??????(80,000???)";
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













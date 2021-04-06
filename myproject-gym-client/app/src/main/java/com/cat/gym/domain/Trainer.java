package com.cat.gym.domain;

import java.sql.Date;
import java.util.List;

public class Trainer {
  private int no;
  private String bag;
  private String photo;
  private String name;
  private String phoneNumber;
  private Date contractS;
  private Date contractE;
  private List<Member> members;

  @Override
  public String toString() {
    return "Trainer [no=" + no + ", bag=" + bag + ", photo=" + photo + ", name=" + name
        + ", phoneNumber=" + phoneNumber + ", contractS=" + contractS + ", contractE=" + contractE
        + ", members=" + members + "]";
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getBag() {
    return bag;
  }
  public void setBag(String bag) {
    this.bag = bag;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public Date getContractS() {
    return contractS;
  }
  public void setContractS(Date contractS) {
    this.contractS = contractS;
  }
  public Date getContractE() {
    return contractE;
  }
  public void setContractE(Date contractE) {
    this.contractE = contractE;
  }
  public List<Member> getMembers() {
    return members;
  }
  public void setMembers(List<Member> members) {
    this.members = members;
  }
}

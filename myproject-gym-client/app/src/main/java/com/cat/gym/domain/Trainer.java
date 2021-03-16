package com.cat.gym.domain;

import java.sql.Date;

public class Trainer {
  private int no;
  private String bag;
  private String photo;
  private String name;
  private String phoneNumber;
  private Date contractS;
  private Date contractE;
  private String members;

  public Trainer() {}

  public Trainer(String csv) {
    String[] fields = csv.split(",");
    this.setNo(Integer.parseInt(fields[0]));
    this.setBag(fields[1]);
    this.setPhoto(fields[2]);
    this.setName(fields[3]);
    this.setPhoneNumber(fields[4]);
    this.setContractS(Date.valueOf(fields[5]));
    this.setContractE(Date.valueOf(fields[6]));
    this.setMembers(fields[7]);
  }

  @Override
  public String toString() {
    return "Trainer [no=" + no + ", bag=" + bag + ", photo=" + photo + ", name=" + name
        + ", phoneNumber=" + phoneNumber + ", contractS=" + contractS + ", contractE=" + contractE
        + ", members=" + members + "]";
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%s", 
        this.getNo(), 
        this.getBag(), 
        this.getPhoto(), 
        this.getName(), 
        this.getPhoneNumber(), 
        this.getContractS(),
        this.getContractE(),
        this.getMembers());
  }

  public static Trainer valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Trainer t = new Trainer();
    t.setNo(Integer.parseInt(fields[0]));
    t.setBag(fields[1]);
    t.setPhoto(fields[2]);
    t.setName(fields[3]);
    t.setPhoneNumber(fields[4]);
    t.setContractS(Date.valueOf(fields[5]));
    t.setContractE(Date.valueOf(fields[6]));
    t.setMembers(fields[7]);
    return t;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bag == null) ? 0 : bag.hashCode());
    result = prime * result + ((contractE == null) ? 0 : contractE.hashCode());
    result = prime * result + ((contractS == null) ? 0 : contractS.hashCode());
    result = prime * result + ((members == null) ? 0 : members.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((photo == null) ? 0 : photo.hashCode());
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
    Trainer other = (Trainer) obj;
    if (bag == null) {
      if (other.bag != null)
        return false;
    } else if (!bag.equals(other.bag))
      return false;
    if (contractE == null) {
      if (other.contractE != null)
        return false;
    } else if (!contractE.equals(other.contractE))
      return false;
    if (contractS == null) {
      if (other.contractS != null)
        return false;
    } else if (!contractS.equals(other.contractS))
      return false;
    if (members == null) {
      if (other.members != null)
        return false;
    } else if (!members.equals(other.members))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (photo == null) {
      if (other.photo != null)
        return false;
    } else if (!photo.equals(other.photo))
      return false;
    return true;
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
  public String getMembers() {
    return members;
  }
  public void setMembers(String members) {
    this.members = members;
  }
}

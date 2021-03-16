package com.cat.gym.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String title;
  private String id;
  private Date registeredDate;
  private int viewCount;
  private String content;
  private int like;

  public Board() {}

  public Board(String csv) {
    String[] fields = csv.split(",");
    this.setNo(Integer.parseInt(fields[0]));
    this.setTitle(fields[1]);
    this.setId(fields[2]);
    this.setRegisteredDate(Date.valueOf(fields[3]));
    this.setViewCount(Integer.parseInt(fields[4]));
    this.setContent(fields[5]);
  }

  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", id=" + id + ", registeredDate="
        + registeredDate + ", viewCount=" + viewCount + ", content=" + content + ", like=" + like
        + "]";
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%d,%s", 
        this.getNo(), 
        this.getTitle(), 
        this.getId(), 
        this.getRegisteredDate().toString(), 
        this.getViewCount(), 
        this.getContent());
  }

  public static Board valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Board b = new Board();
    b.setNo(Integer.parseInt(fields[0]));
    b.setTitle(fields[1]);
    b.setId(fields[2]);
    b.setRegisteredDate(Date.valueOf(fields[3]));
    b.setViewCount(Integer.parseInt(fields[4]));
    b.setContent(fields[5]);
    return b;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + like;
    result = prime * result + no;
    result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + viewCount;
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
    Board other = (Board) obj;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (like != other.like)
      return false;
    if (no != other.no)
      return false;
    if (registeredDate == null) {
      if (other.registeredDate != null)
        return false;
    } else if (!registeredDate.equals(other.registeredDate))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (viewCount != other.viewCount)
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getLike() {
    return like;
  }
  public void setLike(int like) {
    this.like = like;
  }
}

package it.plansoft.rubrica.model;

import java.util.Date;

/**
 * classe di base per i modelli di dati.
 */
public class BaseModel {

  private Long id;

  private Date tsInsert;
  private String userInsert;
  private Date tsUpdate;
  private String userUpdate;

  public Long getId() {
    return id;
  }

  public String getUserUpdate() {
    return userUpdate;
  }

  public void setUserUpdate(String userUpdate) {
    this.userUpdate = userUpdate;
  }

  public Date getTsUpdate() {
    return tsUpdate;
  }

  public void setTsUpdate(Date tsUpdate) {
    this.tsUpdate = tsUpdate;
  }

  public Date getTsInsert() {
    return tsInsert;
  }

  public void setTsInsert(Date tsInsert) {
    this.tsInsert = tsInsert;
  }

  public String getUserInsert() {
    return userInsert;
  }

  public void setUserInsert(String userInsert) {
    this.userInsert = userInsert;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
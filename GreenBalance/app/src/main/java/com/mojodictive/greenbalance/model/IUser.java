package com.mojodictive.greenbalance.model;

public interface IUser {

    public long getId();
    public void setId(long id);

    public String getName();
    public void setName(String name);

    public String getEmail();
    public void setEmail(String email);

    public long getDate();
    public void setDate(long date);
}

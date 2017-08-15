package com.mojodictive.greenbalance.crud;

import com.mojodictive.greenbalance.model.IUser;

public interface ICRUDOperations {

    public IUser readUser();

    public IUser createUser(IUser user);

    public IUser updateUser(IUser user);

    public Boolean deleteUser(IUser user);
}

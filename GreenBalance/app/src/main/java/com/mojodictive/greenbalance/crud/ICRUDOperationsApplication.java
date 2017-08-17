package com.mojodictive.greenbalance.crud;

import com.mojodictive.greenbalance.model.IUser;

public interface ICRUDOperationsApplication {

    public static interface CallbackFunction<T> {

        public void process(T result);
    }

    public ICRUDOperationsApplication getCrudOperations();

    public void readUser(CallbackFunction<IUser> callbackFunction);

    public void createUser(IUser user, CallbackFunction<IUser> callbackFunction);

    public void updateUser(IUser user, CallbackFunction<IUser> callbackFunction);

    public void deleteUser(IUser user, CallbackFunction<Boolean> callbackFunction);
}

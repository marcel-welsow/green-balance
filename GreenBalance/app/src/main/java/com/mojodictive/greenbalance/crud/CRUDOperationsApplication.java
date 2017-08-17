package com.mojodictive.greenbalance.crud;

import android.app.Application;
import android.os.AsyncTask;

import com.mojodictive.greenbalance.model.IUser;

public class CRUDOperationsApplication extends Application implements ICRUDOperationsApplication {

    private ICRUDOperations crudOperations;

    @Override
    public void onCreate() {

        super.onCreate();

        crudOperations = new CRUDOperations(this);
    }

    public ICRUDOperationsApplication getCrudOperations() {
        return this;
    }

    @Override
    public void readUser(final CallbackFunction<IUser> callbackFunction) {

        new AsyncTask<Void, Void, IUser>() {

            @Override
            protected IUser doInBackground(Void... params) {
                return crudOperations.readUser();
            }

            @Override
            protected void onPostExecute(IUser user) {
                callbackFunction.process(user);
            }
        }.execute();
    }

    @Override
    public void createUser(IUser user, final CallbackFunction<IUser> callbackFunction) {

        new AsyncTask<IUser, Void, IUser>() {

            @Override
            protected IUser doInBackground(IUser... params) {
                return crudOperations.createUser(params[0]);
            }

            @Override
            protected void onPostExecute(IUser user) {
                callbackFunction.process(user);
            }
        }.execute(user);
    }

    @Override
    public void updateUser(IUser user, final CallbackFunction<IUser> callbackFunction) {

        new AsyncTask<IUser, Void, IUser>() {

            @Override
            protected IUser doInBackground(IUser... params) {
                return crudOperations.updateUser(params[0]);
            }

            @Override
            protected void onPostExecute(IUser user) {
                callbackFunction.process(user);
            }
        }.execute(user);
    }

    @Override
    public void deleteUser(IUser user, final CallbackFunction<Boolean> callbackFunction) {

        new AsyncTask<IUser, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(IUser... params) {
                return crudOperations.deleteUser(params[0]);
            }

            @Override
            protected void onPostExecute(Boolean deleted) {
                callbackFunction.process(deleted);
            }
        }.execute(user);
    }
}

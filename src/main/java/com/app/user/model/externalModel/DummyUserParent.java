package com.app.user.model.externalModel;

import java.util.List;

public class DummyUserParent {
    List<DummyUser> users;

    public DummyUserParent() {
    }

    public List<DummyUser> getUsers() {
        return users;
    }

    public void setUsers(List<DummyUser> users) {
        this.users = users;
    }
}

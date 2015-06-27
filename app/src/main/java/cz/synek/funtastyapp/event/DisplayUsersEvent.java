package cz.synek.funtastyapp.event;

import java.util.List;

import cz.synek.funtastyapp.users.User;

public class DisplayUsersEvent {

    private List<User> users;

    public DisplayUsersEvent(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}

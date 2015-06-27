package cz.synek.funtastyapp.users;

import com.google.gson.annotations.SerializedName;

public final class User {

    private long id;

    private String login;

    @SerializedName("avatar_url")
    private String avatarURL;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}

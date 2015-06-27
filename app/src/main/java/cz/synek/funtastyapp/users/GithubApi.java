package cz.synek.funtastyapp.users;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface GithubApi {

    @GET("/repos/torvalds/linux/contributors")
    void getContributors(Callback<List<User>> users);
}

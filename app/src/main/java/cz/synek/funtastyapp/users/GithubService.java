package cz.synek.funtastyapp.users;

import com.squareup.otto.Bus;

import java.util.List;

import cz.synek.funtastyapp.event.DisplayUsersEvent;
import cz.synek.funtastyapp.event.NetworkErrorEvent;
import cz.synek.funtastyapp.utils.BusProvider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GithubService {

    private Bus bus = BusProvider.getInstance();

    public void getContributors() {
        GithubApiProvider.getGithubApi().getContributors(new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                bus.post(new DisplayUsersEvent(users));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new NetworkErrorEvent());
            }
        });
    }
}

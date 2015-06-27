package cz.synek.funtastyapp.users;

import retrofit.RestAdapter;

public class GithubApiProvider {

    private static final String BASE_URL = "https://api.github.com";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    private static final GithubApi GITHUB_API = REST_ADAPTER.create(GithubApi.class);

    public static GithubApi getGithubApi() {
        return GITHUB_API;
    }
}

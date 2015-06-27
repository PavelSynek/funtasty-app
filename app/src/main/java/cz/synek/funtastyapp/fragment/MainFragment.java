package cz.synek.funtastyapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import butterknife.OnClick;
import cz.synek.funtastyapp.BaseFragment;
import cz.synek.funtastyapp.R;
import cz.synek.funtastyapp.event.DisplayUsersEvent;
import cz.synek.funtastyapp.event.NetworkErrorEvent;
import cz.synek.funtastyapp.users.GithubService;
import cz.synek.funtastyapp.users.UserAdapter;


public class MainFragment extends BaseFragment {

    @InjectView(R.id.userList)
    RecyclerView userList;

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @InjectView(R.id.error_text)
    TextView errorText;

    private GithubService githubService;

    private UserAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        githubService = new GithubService();

        getActivity().setTitle(R.string.linux_contributors);

        // configure RecyclerView
        userList.setLayoutManager(new LinearLayoutManager(getActivity()));
        userList.setHasFixedSize(true);

        // configure adapter
        adapter = new UserAdapter(getActivity());
        userList.setAdapter(adapter);

        loadUsers();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main;
    }

    @Subscribe
    public void displayUsers(DisplayUsersEvent event) {
        adapter.updateUsers(event.getUsers());
        progressWheel.setVisibility(View.GONE);
        userList.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.GONE);
    }

    @Subscribe
    public void displayError(NetworkErrorEvent event) {
        progressWheel.setVisibility(View.GONE);
        userList.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.error_text)
    void loadUsers() {
        githubService.getContributors();
        progressWheel.setVisibility(View.VISIBLE);
        userList.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }
}

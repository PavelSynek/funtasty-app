package cz.synek.funtastyapp.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.synek.funtastyapp.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;
    private Context context;

    public UserAdapter(Context context) {
        users = new ArrayList<>();
        setHasStableIds(true);
        this.context = context.getApplicationContext();
    }

    public void updateUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.getLogin());
        Picasso.with(context).load(user.getAvatarURL()).fit().centerCrop().into(holder.profileImage);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.list_item)
        TextView name;

        @InjectView(R.id.profile_image)
        CircleImageView profileImage;

        public UserViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}

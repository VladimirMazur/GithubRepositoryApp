package com.githubrepositoryapp.view.main.repositories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.githubrepositoryapp.R;
import com.githubrepositoryapp.pojo.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vladimir on 02.04.2017.
 */

public class RepositoryViewAdapter extends RecyclerView.Adapter<RepositoryViewAdapter.RepositoryViewHolders> {

    private OnRepositoryClickListener listener;
    private Context context;
    private List<Repository> repositories;

    public RepositoryViewAdapter(Context context, List<Repository> repositories,
                                 OnRepositoryClickListener listener) {
        this.repositories = repositories;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RepositoryViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_repository_view, null);
        return new RepositoryViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(final RepositoryViewHolders holder, int position) {
        Repository repository = repositories.get(position);
        String description = repository.getDescription();

        holder.txtRepositoryName.setText(repository.getFullName());
        holder.txtRepositoryDescription.setText(description != null ? description : "");
        holder.txtStar.setText(context.getString(R.string.txt_stars) + " " +
                String.valueOf(repository.getStargazersCount()));
        holder.txtFork.setText(context.getString(R.string.txt_forks) + " " +
                String.valueOf(repository.getForksCount()));
        holder.txtIssue.setText(context.getString(R.string.txt_issues) + " " +
                String.valueOf(repository.getOpenIssuesCount()));
    }

    @Override
    public int getItemCount() {
        return this.repositories.size();
    }

    class RepositoryViewHolders extends RecyclerView.ViewHolder {

        @BindView(R.id.txtRepositoryName)
        TextView txtRepositoryName;
        @BindView(R.id.txtRepositoryDescription)
        TextView txtRepositoryDescription;
        @BindView(R.id.txtStar)
        TextView txtStar;
        @BindView(R.id.txtFork)
        TextView txtFork;
        @BindView(R.id.txtIssue)
        TextView txtIssue;

        RepositoryViewHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        void onClickRepositoryItem() {
            listener.onRepositoriesItemClicked(repositories.get(getAdapterPosition()));
        }
    }

    public interface OnRepositoryClickListener {
        void onRepositoriesItemClicked(Repository repository);
    }
}

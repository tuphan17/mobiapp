package edu.tacoma.uw.projecttcss450;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.tacoma.uw.projecttcss450.databinding.FragmentQuaterBinding;

public class QuaterRecyclerViewAdapter extends RecyclerView.Adapter<QuaterRecyclerViewAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public FragmentQuaterBinding binding;
        public Quater mItem;

    public ViewHolder(View view) {
        super(view);
        mView = view;
        binding = FragmentQuaterBinding.bind(view);
    }

    public void setItem(final Quater item) {
        mItem = item;
        binding.quaterYear.setText(item.getYear());
        binding.quaterYear.setOnClickListener(view ->
            {
                QuaterListFragmentDirections.ActionQuaterListFragmentToQuaterDetailFragment directions =
                        QuaterListFragmentDirections.actionQuaterListFragmentToQuaterDetailFragment(item);

                Navigation.findNavController(mView)
                        .navigate(directions);
            });
        }
    }

    private final List<Quater> mValues;

    public QuaterRecyclerViewAdapter(List<Quater> quaterList) {
    mValues = quaterList;
}
    @NonNull
    @Override
    public QuaterRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_quater, parent, false ));
    }

    @Override
    public void onBindViewHolder(@NonNull QuaterRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setItem(mValues.get(position));
    }

    @Override
    public int getItemCount() {
    return mValues.size();
}


}

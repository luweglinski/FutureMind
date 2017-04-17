package pl.lukaszw.futuremind.ui.main.list.master.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pl.lukaszw.futuremind.R;
import pl.lukaszw.futuremind.data.viewmodel.DataViewModel;

/**
 * Created by Lukasz on 17.04.2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<DataViewModel> mDataResponse = new ArrayList<>();
    private ItemClickListener<DataViewModel> mItemClickListener;

    public ListAdapter(Context context, ItemClickListener<DataViewModel> itemClickListener) {
        mLayoutInflater = LayoutInflater.from(context);
        mItemClickListener = itemClickListener;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private DataViewModel getItem(int position) {
        return mDataResponse.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataResponse.size();
    }

    public void setData(List<DataViewModel> data) {
        mDataResponse = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mDate;
        private final ImageView mImageView;
        private final TextView mTitle, mDesc;
        private DataViewModel mDataViewModel;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
            mTitle = (TextView) itemView.findViewById(R.id.item_title);
            mDesc = (TextView) itemView.findViewById(R.id.item_desc);
            mDate = (TextView) itemView.findViewById(R.id.item_date);
            itemView.setOnClickListener(this);
        }

        private void bind(DataViewModel dataViewModel) {
            mDataViewModel = dataViewModel;
            Glide.with(mImageView.getContext())
                    .load(dataViewModel.getImageUrl())
                    .placeholder(R.drawable.image_placeholder)
                    .fitCenter()
                    .crossFade()
                    .into(mImageView);
            mTitle.setText(dataViewModel.getTitle());
            mDesc.setText(dataViewModel.getDescription());
            mDate.setText(dataViewModel.getModificationDateString());
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(mDataViewModel);
        }
    }
}

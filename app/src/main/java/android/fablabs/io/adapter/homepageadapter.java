package android.fablabs.io.adapter;

import android.content.Context;
import android.fablabs.io.R;
import android.fablabs.io.databinding.MyhistoryfragmentsBinding;
import android.fablabs.io.listener.HistoryItemClickListener;
import android.fablabs.io.model.Myhistorymodel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class homepageadapter extends  RecyclerView.Adapter<homepageadapter.ViewHolder>{
    
    Context mContext;
    private List<Myhistorymodel> mData;
    private HistoryItemClickListener historyitemclicklistner;

    public homepageadapter(Context mContext, List<Myhistorymodel> mData, HistoryItemClickListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.historyitemclicklistner = listener;
    }

    @NonNull
    @Override
    public homepageadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card,viewGroup,false);
//        return new _ViewHolder(view);

        LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
        MyhistoryfragmentsBinding itemCardBinding = MyhistoryfragmentsBinding.inflate(layoutInflater,viewGroup,false);
        return new ViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final homepageadapter.ViewHolder viewHolder, final int i) {
        final int pos = viewHolder.getAdapterPosition();

         viewHolder.itemView.setTag(pos);
         viewHolder.setPostImage(mData.get(i));

        if (i%2==1){

            //int dimenTopPixeles = getDimensionValuePixels(R.dimen.staggedmarginbottom);
           // int dimenleftPixeles = getDimensionValuePixels(R.dimen.horizontal_card);
            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) viewHolder.itemCardBinding.myfragment.getLayoutParams();
             //cardViewMarginParams.setMargins(dimenleftPixeles, dimenTopPixeles, 0, 0);
            viewHolder.itemCardBinding.myfragment.requestLayout();
        }

       /* viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyitemclicklistner.onItemClicked(mData.get(i));

            }
        });*/
        viewHolder.itemCardBinding.myfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyitemclicklistner.onItemClicked(mData.get(i));

            }
        });
        viewHolder.itemCardBinding.btnuplcomoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyitemclicklistner.onItemClicked(mData.get(i));

            }
        });
        viewHolder.itemCardBinding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyitemclicklistner.onItemClicked(mData.get(i));

            }
        });
        viewHolder.itemCardBinding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyitemclicklistner.onItemClicked(mData.get(i));

            }
        });



    }

    public int getDimensionValuePixels(int dimension)
    {

        return (int) mContext.getResources().getDimension(dimension);
    }


    public int dpToPx(int dp)
    {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @Override
    public long getItemId(int position) {
       Myhistorymodel courseCard = mData.get(position);
        return courseCard.getId();
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        MyhistoryfragmentsBinding itemCardBinding;
        public ViewHolder(@NonNull MyhistoryfragmentsBinding cardBinding) {
            super(cardBinding.getRoot());
            this.itemCardBinding = cardBinding;

            //this.itemRecyclerMealBinding.
        }

        void setPostImage(Myhistorymodel courseCard){
            this.itemCardBinding.textView2.setText(courseCard.getDate());
            this.itemCardBinding.textView.setText(courseCard.getChildnode());
            this.itemCardBinding.btnuplcomoing.setText(courseCard.getStatus());
        }

    }
}

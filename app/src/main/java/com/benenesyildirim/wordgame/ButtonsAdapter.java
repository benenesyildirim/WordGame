package com.benenesyildirim.wordgame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

public class ButtonsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> randomLetterList;
    private ButtonsClickListener clickListener;

    public ButtonsAdapter(List<String>  randomLetterList,ButtonsClickListener clickListener) {
        this.randomLetterList = randomLetterList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.buttons_design,null);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ListHolder listHolder = (ListHolder) holder;
        listHolder.button.setText(randomLetterList.get(position));

        listHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null){
                    clickListener.buttonsClickListener(listHolder.button.getText().toString());

                }
            }
        });
    }

    public class ListHolder extends RecyclerView.ViewHolder{
        Button button;

        public ListHolder(View view) {
            super(view);
            button = view.findViewById(R.id.lettersButton);
        }
    }

    @Override
    public int getItemCount() {
        return randomLetterList.size();
    }

    public interface ButtonsClickListener {
        void buttonsClickListener(String letter);
    }
}

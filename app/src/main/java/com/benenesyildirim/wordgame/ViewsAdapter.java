package com.benenesyildirim.wordgame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ViewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ViewObject> viewObjectList;

    public ViewsAdapter(List<ViewObject>  viewObjectList) {
        this.viewObjectList = viewObjectList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_design, null);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ListHolder listHolder = (ListHolder) holder;
        listHolder.lettersText.setText(viewObjectList.get(position).getLetters());
    }

    public class ListHolder extends RecyclerView.ViewHolder {

        TextView lettersText;

        ListHolder(@NonNull View v) {
            super(v);
            lettersText = v.findViewById(R.id.letters);
        }
    }

    @Override
    public int getItemCount() {
        return viewObjectList.size();
    }
}

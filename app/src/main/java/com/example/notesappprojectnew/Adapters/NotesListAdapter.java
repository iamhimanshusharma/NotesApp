package com.example.notesappprojectnew.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappprojectnew.Models.Notes;
import com.example.notesappprojectnew.NotesClickListener;
import com.example.notesappprojectnew.R;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>{

    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.notes_title.setText(list.get(position).getTitle());
        holder.notes_title.setSelected(true);

        holder.notes_note.setText(list.get(position).getNotes());


        holder.notes_datetime.setText(list.get(position).getDate());
        holder.notes_datetime.setSelected(true);

        holder.notes_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
                System.out.println(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_cardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_cardview);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView notes_title, notes_note, notes_datetime;
        CardView notes_cardview;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            notes_cardview = itemView.findViewById(R.id.cardView_item);
            notes_title = itemView.findViewById(R.id.notes_title);
            notes_note = itemView.findViewById(R.id.notes_note);
            notes_datetime = itemView.findViewById(R.id.notes_datetime);
        }
    }
}

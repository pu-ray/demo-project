package com.gamecodeschool.demo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gamecodeschool.demo.R;
import com.gamecodeschool.demo.database.Notes;

import java.util.List;

public class NotesAdapter extends  ArrayAdapter{
    public NotesAdapter(Context context, int resource, List<Notes.Note> notes) {
            super(context, resource, notes);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Note note=getItem(position);
            if (convertView==null){
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.note_list_item,parent,false);
            }

            TextView tvTitle=convertView.findViewById(R.id.tvTitle);
            TextView tvNote=convertView.findViewById(R.id.tvNoteText);
            tvTitle.setText(note.getTitle());
            tvNote.setText(note.getNoteText());

            return convertView;
        }
    }

}

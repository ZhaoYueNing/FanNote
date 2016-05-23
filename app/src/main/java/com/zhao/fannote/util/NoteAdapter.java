package com.zhao.fannote.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhao.fannote.R;
import com.zhao.fannote.domain.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by Zhao on 2016/5/24.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    private Context context;
    private int resource;
    private List<Note> noteList;
    private SimpleDateFormat dateFormat;

    public NoteAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
        this.context = context;
        this.noteList = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(resource, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);

        Note note = noteList.get(position);
        tv_title.setText(note.getTitle());
        tv_content.setText(note.getContent());
        dateFormat = new SimpleDateFormat("MM月dd日");
        tv_date.setText(dateFormat.format(note.getDate().getTime()));

        return view;
    }
}

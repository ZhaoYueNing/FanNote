package com.zhao.fannote.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.zhao.fannote.domain.Note;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Zhao on 2016/5/24.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    private Context context;
    private int resource;
    private List<Note> noteList;

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

        return super.getView(position, convertView, parent);
    }
}

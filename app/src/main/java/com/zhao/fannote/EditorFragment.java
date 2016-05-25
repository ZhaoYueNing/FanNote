package com.zhao.fannote;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zhao.fannote.domain.Note;
import com.zhao.fannote.model.NoteContainer;

/**
 * Created by Zhao on 2016/5/24.
 */
public class EditorFragment extends Fragment {
    private Note note;
    private EditText et_title;
    private EditText et_content;

    public EditorFragment(Note note) {
        this.note = note;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frmage_additem, null);
        et_title = (EditText) view.findViewById(R.id.et_title);
        et_content = (EditText) view.findViewById(R.id.et_content);

        view.findViewById(R.id.bt_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor();
            }
        });
        return view;
    }

    /**
     * 编辑更改note
     */
    private void editor() {
        String title = et_title.getText().toString();
        String content = et_content.getText().toString();

        note.setTitle(title);
        note.setContent(content);

        NoteContainer.changNote(note);
    }
}

package com.zhao.fannote;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhao.fannote.domain.Note;
import com.zhao.fannote.model.NoteContainer;

import java.util.Calendar;

/**
 * Created by Zhao on 2016/5/24.
 * 显示note到页面
 */
public class DisplayContentFragment extends Fragment {
    private TextView tv_title;
    private TextView tv_content;
    private Context context;
    private Note note;
    private Handler handle = new Handler(Looper.getMainLooper());
    public DisplayContentFragment(Context context,Note note) {
        this.context = context;
        this.note = note;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frmage_display_content, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_content = (TextView) view.findViewById(R.id.tv_content);

        tv_title.setText(note.getTitle());
        tv_content.setText(note.getContent());

        return view;
    }

    /**
     * 编辑
     * 跳转到EditorFragment
     */
    private void editor() {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .add(R.id.frame_main,new Fragment())
                .addToBackStack(null)
                .commit();
    }
}

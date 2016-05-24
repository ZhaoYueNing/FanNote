package com.zhao.fannote;

import android.app.Fragment;
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
import android.widget.Toast;

import com.zhao.fannote.domain.Note;
import com.zhao.fannote.model.NoteContainer;

import java.util.Calendar;

/**
 * Created by Zhao on 2016/5/24.
 * 添加Note页面
 */
public class AddNoteFragment extends Fragment {
    private EditText et_title;
    private EditText et_content;
    private Context context;
    private Handler handle = new Handler(Looper.getMainLooper());
    public AddNoteFragment(Context context) {
        this.context = context;
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
                save();
            }
        });
        return view;
    }

    /**
     * 将数据保存到NoteContainer类中的容器
     */
    private void save() {
        String title = et_title.getText().toString();
        String content = et_content.getText().toString();
        if (TextUtils.isEmpty(title)){
            Toast.makeText(this.context, "标题不得为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setDate(Calendar.getInstance());
        NoteContainer.add(note);
        Message msg = new Message();
        msg.what = MainActivity.UPDATE_DATA;
        //发送一则消息 使MainActivity 更新listview
        handle.sendMessage(msg);
        onDestroy();
    }
}

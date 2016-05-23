package com.zhao.fannote;

import android.app.FragmentManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.zhao.fannote.model.NoteContainer;
import com.zhao.fannote.util.NoteAdapter;

public class MainActivity extends AppCompatActivity {
    public final static int UPDATE_DATA = 1;
    private ListView lv_note;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_DATA:
                    updateData();
            }
        }
    };
    private NoteAdapter adapter;

    /**
     * 升级数据
     * 刷新listview
     */
    private void updateData() {
        adapter.notifyDataSetChanged();
        lv_note.setAdapter(adapter);
    }

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        manager = getFragmentManager();
        lv_note = (ListView) findViewById(R.id.lv_noteList);
        adapter = new NoteAdapter(MainActivity.this, R.layout.note_item, NoteContainer.getList());
        lv_note.setAdapter(adapter);
        findViewById(R.id.iv_addItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddNote();
            }
        });
    }

    /**
     * 跳转到添加笔记的
     * AddNoteFragment进行添加Note操作
     */
    private void toAddNote(){
        manager.beginTransaction()
                .add(R.id.frame_main,new AddNoteFragment(MainActivity.this))
                .addToBackStack(null)
                .commit();
    }
}

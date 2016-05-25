package com.zhao.fannote;

import android.app.FragmentManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhao.fannote.domain.Note;
import com.zhao.fannote.model.NoteContainer;
import com.zhao.fannote.model.NoteSQLiteOpenHelper;
import com.zhao.fannote.util.NoteAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static int flag = 0;
    public final static int UPDATE_DATA = 1;
    private final static int SQL_VERSION = 3;
    private ListView lv_note;
    private FragmentManager manager;
    private NoteAdapter adapter;
    private static SQLiteOpenHelper openHelper;


    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_DATA:
                    updateData();
            }
        }
    };

    /**
     * 升级数据
     * 刷新listview
     */
    private void updateData() {
        adapter.notifyDataSetChanged();
        lv_note.setAdapter(adapter);
    }



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
        lv_note.setOnItemClickListener(this);
        openHelper = new NoteSQLiteOpenHelper(MainActivity.this,null,SQL_VERSION);
        if (flag==0) {
            NoteContainer.init();
            flag++;
        }
    }

    /**
     * 跳转到添加笔记的
     * AddNoteFragment进行添加Note操作
     */
    private void toAddNote(){
        manager.beginTransaction()
                .add(R.id.frame_main,new AddNoteFragment())
                .addToBackStack(null)
                .commit();
    }

    /**
     * 点击listview上的条目跳转到对应的内容显示
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = NoteContainer.getList().get(position);
        manager.beginTransaction()
                .add(R.id.frame_main,new DisplayContentFragment(MainActivity.this,note))
                .addToBackStack(null)
                .commit();
    }

    public static SQLiteOpenHelper getOpenHelper() {
        return openHelper;
    }
}

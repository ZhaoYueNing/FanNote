package com.zhao.fannote.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.zhao.fannote.MainActivity;
import com.zhao.fannote.domain.Note;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zhao on 2016/5/23.
 * 笔记容器
 * 用来存放笔记
 * 封装所有对数据库的实时操作以及对ui显示数据的更新
 */
public class NoteContainer  {
    private static List<Note> noteList = new LinkedList<>();
    private static Handler handle = new Handler(Looper.getMainLooper());
    private static SQLiteDatabase db;

    /**
     * 对数据初始化操作
     */
    public static void init() {
        SQLiteOpenHelper openHelper = MainActivity.getOpenHelper();
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.query(NoteSQLiteOpenHelper.TABLE_NAME,new String[]{"_id","title","content","date"},null,null,null,null,null);
        //将数据库数据导入到noteList
        if (cursor.moveToFirst()){
            do{
                Note note = new Note();
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                long time = cursor.getLong(cursor.getColumnIndex("date"));
                Date date = new Date();
                date.setTime(time);
                note.setId(id);
                note.setTitle(title);
                note.setContent(content);
                note.setDate(date);
                noteList.add(note);
            }while (cursor.moveToNext());
        }
        //将noteList的数据显示在listview上
        updateData();
    }

    /**
     * 添加note
     * @param note 被添加的note
     */
    public static void add(Note note){
        noteList.add(note);
        //插入到数据库
        ContentValues cv = new ContentValues();
        cv.put("_id",note.getId());
        cv.put("title",note.getTitle());
        cv.put("content",note.getContent());
        cv.put("date",note.getDate().getTime());
        db.insert(NoteSQLiteOpenHelper.TABLE_NAME,null,cv);
        //添加完成后更新UI数据
        updateData();
    }

    /**
     * 移除一条笔记
     * @param id 笔记的id
     * @return 成功返回true 失败返回false
     */
    public static boolean remove(int id){
        Iterator<Note> iterator = noteList.iterator();
        while (iterator.hasNext()){
            Note note = iterator.next();
            if (note.getId()==id){
                noteList.remove(note);
                //在数据库删除
                db.delete(NoteSQLiteOpenHelper.TABLE_NAME,"id=?",new String[]{note.getId()+""});
                updateData();
                return true;
            }
        }
        return false;
    }

    /**
     * 获取储存的note数量
     * @return note数量
     */
    public static int size(){
        return noteList.size();
    }

    public static List<Note> getList() {
        if (noteList==null)
            noteList = new LinkedList<>();
        return noteList;
    }

    /**
     * 更改Note
     * @param note
     */
    public static void changNote(Note note) {
        //在数据库更新
        ContentValues cv = new ContentValues();
        cv.put("title",note.getTitle());
        cv.put("content",note.getContent());
        db.update(NoteSQLiteOpenHelper.TABLE_NAME,cv,"_id=?",new String[]{note.getId()+""});

        updateData();
    }

    /**
     * 通过消息机制来更新UI上显示的数据
     */
    private static void updateData(){
        Message msg = new Message();
        msg.what = MainActivity.UPDATE_DATA;
        //发送一则消息 使MainActivity 更新listview
        handle.sendMessage(msg);
    }
}

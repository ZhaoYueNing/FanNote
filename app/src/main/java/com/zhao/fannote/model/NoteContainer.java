package com.zhao.fannote.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.zhao.fannote.MainActivity;
import com.zhao.fannote.domain.Note;

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


    /**
     * 添加note
     * @param note 被添加的note
     */
    public static void add(Note note){
        noteList.add(note);
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
        return noteList;
    }

    /**
     * 更改Note
     * @param note
     */
    public static void changNote(Note note) {

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

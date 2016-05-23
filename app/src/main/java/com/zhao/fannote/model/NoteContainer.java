package com.zhao.fannote.model;

import com.zhao.fannote.domain.Note;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zhao on 2016/5/23.
 * 笔记容器
 * 用来存放笔记
 */
public class NoteContainer  {
    private static List<Note> noteList = new LinkedList<>();

    /**
     * 添加note
     * @param note 被添加的note
     */
    public static void add(Note note){
        noteList.add(note);
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

}

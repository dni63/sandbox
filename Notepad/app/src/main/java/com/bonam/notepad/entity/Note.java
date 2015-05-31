package com.bonam.notepad.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Daniel on 31/05/2015.
 */
public class Note {
    private int id;
    private String title;
    private GregorianCalendar lastModified;
    private String note;
    private String lastModifiedStr;

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLastModifiedStr() {
        if(this.lastModifiedStr == null) {
            if(getLastModified() != null) {
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
                this.lastModifiedStr = fmt.format(getLastModified().getTime());
            } else {
                this.lastModifiedStr = "-";
            }
        }
        return this.lastModifiedStr;
    }

    public GregorianCalendar getLastModified() {
        return this.lastModified;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setLastModified(GregorianCalendar date) {
        this.lastModified = date;
    }
}

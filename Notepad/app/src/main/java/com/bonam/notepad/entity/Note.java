package com.bonam.notepad.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                this.lastModifiedStr = fmt.format(getLastModified().getTime());
            } else {
                this.lastModifiedStr = "-";
            }
        }
        return this.lastModifiedStr;
    }

    public static String getDefaultLastModified() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Calendar now = new GregorianCalendar();
        return fmt.format(now.getTime());
    }

    public void setLastModified(String dateStr) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date parsed = null;
        try {
            parsed = df.parse(dateStr);
            this.lastModified = new GregorianCalendar();
            this.lastModified.setTime(parsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public String getNote() {
        return this.note;
    }
}

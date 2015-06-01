package com.bonam.notepad.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Daniel on 31/05/2015.
 */
public class Note implements Parcelable {
    private int id;
    private String title;
    private GregorianCalendar lastModified;
    private String note;
    private String lastModifiedStr;

    // Parcelling part
    public Note(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        this.setId(Integer.valueOf(data[0]));
        this.setTitle(data[1]);
        this.setNote(data[2]);
        this.setLastModified(data[3]);
    }

    public Note(){
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                String.valueOf(this.id),
                this.title,
                this.note,
                this.getLastModifiedStr()});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}

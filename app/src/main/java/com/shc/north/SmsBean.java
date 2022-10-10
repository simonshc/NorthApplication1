package com.shc.north;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class SmsBean implements Parcelable {
    private String number;
    private String content;
    private long date;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.number);
        dest.writeString(this.content);
        dest.writeLong(this.date);
    }

    public SmsBean() {
    }

    protected SmsBean(Parcel in) {
        this.number = in.readString();
        this.content = in.readString();
        this.date = in.readLong();
    }

    public static final Parcelable.Creator<SmsBean> CREATOR = new Parcelable.Creator<SmsBean>() {
        @Override
        public SmsBean createFromParcel(Parcel source) {
            return new SmsBean(source);
        }

        @Override
        public SmsBean[] newArray(int size) {
            return new SmsBean[size];
        }
    };
}

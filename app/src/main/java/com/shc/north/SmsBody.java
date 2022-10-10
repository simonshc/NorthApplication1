package com.shc.north;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class SmsBody implements Parcelable {
    /**
     * id   消息类型 来源手机  目标手机  发送时间  内容
     */
    private String code;
    private String message;
    private String messageId;
    private String messageType;
    private String sourcePhone;
    private String targetPhone;
    private String sendTime;
    private String content;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSourcePhone() {
        return sourcePhone;
    }

    public void setSourcePhone(String sourcePhone) {
        this.sourcePhone = sourcePhone;
    }

    public String getTargetPhone() {
        return targetPhone;
    }

    public void setTargetPhone(String targetPhone) {
        this.targetPhone = targetPhone;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.messageId);
        dest.writeString(this.messageType);
        dest.writeString(this.sourcePhone);
        dest.writeString(this.targetPhone);
        dest.writeString(this.sendTime);
        dest.writeString(this.content);
    }

    public SmsBody() {
    }

    protected SmsBody(Parcel in) {
        this.messageId = in.readString();
        this.messageType = in.readString();
        this.sourcePhone = in.readString();
        this.targetPhone = in.readString();
        this.sendTime = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<SmsBody> CREATOR = new Parcelable.Creator<SmsBody>() {
        @Override
        public SmsBody createFromParcel(Parcel source) {
            return new SmsBody(source);
        }

        @Override
        public SmsBody[] newArray(int size) {
            return new SmsBody[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

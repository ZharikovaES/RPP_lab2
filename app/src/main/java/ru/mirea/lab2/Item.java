package ru.mirea.lab2;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String ImageUrl;
    private String Creator;
    private String Description;

    public Item(String ImageUrl, String Creator){
        this.ImageUrl = ImageUrl;
        this.Creator = Creator;
        Description = "Описание отсутствует";
    }

    public Item(String ImageUrl, String Creator, String Description){
        this(ImageUrl, Creator);
        this.Description = Description;
    }

    public String getImageUrl() {
        return "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b" +
                "31658d92e64a20d99f816/src/images/tech/" + ImageUrl;
    }

    public String getCreator() {
        return Creator;
    }

    public String getDescription() {
        return Description;
    }

    protected Item(Parcel in) {
        this.ImageUrl = in.readString();
        this.Creator = in.readString();
        this.Description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ImageUrl);
        dest.writeString(this.Creator);
        dest.writeString(this.Description);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}

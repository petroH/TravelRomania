package com.example.petro.travelromania.regiuni;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Petro on 04-Feb-17.
 */

public class Regions implements Parcelable {

    private String name;
    private String description;
    private String thumbnail;
    private String image;
    private String longDescription;

    public Regions() {
    }

    public Regions(String name, String description, String thumbnail, String image, String longDescription) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.image = image;
        this.longDescription= longDescription;
    }

    public Regions(Parcel source) {
        name = source.readString();
        description = source.readString();
        thumbnail = source.readString();
        image = source.readString();
        longDescription=source.readString();
    }

    public String getLongDescription() { return longDescription; }

    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        applyDefaultValues();
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(thumbnail);
        dest.writeString(image);
        dest.writeString(longDescription);
    }

    private void applyDefaultValues() {
        if (name == null) {
            name = "";
        }
        if (description == null) {
            description = "";
        }
        if (thumbnail == null) {
            thumbnail = "";
        }
        if (image == null) {
            image = "";
        }
        if (longDescription == null) {
            longDescription = "";
        }
    }

    public static Creator<Regions> CREATOR = new Creator<Regions>() {
        @Override
        public Regions createFromParcel(Parcel source) {
            return new Regions(source);
        }

        @Override
        public Regions[] newArray(int size) {
            return new Regions[size];
        }
        };
}

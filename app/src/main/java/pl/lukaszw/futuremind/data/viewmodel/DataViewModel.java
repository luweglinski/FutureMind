package pl.lukaszw.futuremind.data.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

import pl.lukaszw.futuremind.data.model.DataModel;

/**
 * Created by Lukasz on 17.04.2017.
 */

public class DataViewModel implements Parcelable {

    public static final String KEY = DataViewModel.class.getSimpleName();

    private String mTitle;
    private String mDescription;
    private int mOrderId;
    private Date mModificationDate;
    private String mImageUrl;
    private String mWebUrl;

    public DataViewModel(DataModel dataResponse) {
        mTitle = dataResponse.getTitle();
        mDescription = dataResponse.getDescription().substring(0, dataResponse.getDescription().indexOf("http"));
        mOrderId = dataResponse.getOrderId();
        mModificationDate = dataResponse.getModificationDate();
        mImageUrl = dataResponse.getImageUrl();
        mWebUrl = dataResponse.getDescription().substring(dataResponse.getDescription().indexOf("http"), dataResponse.getDescription().length());
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getOrderId() {
        return mOrderId;
    }

    public String getModificationDateString() {
        return SimpleDateFormat.getDateInstance().format(mModificationDate);
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mDescription);
        dest.writeInt(this.mOrderId);
        dest.writeLong(this.mModificationDate != null ? this.mModificationDate.getTime() : -1);
        dest.writeString(this.mImageUrl);
        dest.writeString(this.mWebUrl);
    }

    protected DataViewModel(Parcel in) {
        this.mTitle = in.readString();
        this.mDescription = in.readString();
        this.mOrderId = in.readInt();
        long tmpMModificationDate = in.readLong();
        this.mModificationDate = tmpMModificationDate == -1 ? null : new Date(tmpMModificationDate);
        this.mImageUrl = in.readString();
        this.mWebUrl = in.readString();
    }

    public static final Creator<DataViewModel> CREATOR = new Creator<DataViewModel>() {
        @Override
        public DataViewModel createFromParcel(Parcel source) {
            return new DataViewModel(source);
        }

        @Override
        public DataViewModel[] newArray(int size) {
            return new DataViewModel[size];
        }
    };
}
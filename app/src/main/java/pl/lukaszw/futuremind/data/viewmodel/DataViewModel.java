package pl.lukaszw.futuremind.data.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import pl.lukaszw.futuremind.data.model.DataModel;

/**
 * Created by Lukasz on 17.04.2017.
 */

public class DataViewModel extends DataModel implements Parcelable {

    public static final String KEY = DataViewModel.class.getSimpleName();

    private String mWebUrl;

    public DataViewModel(DataModel dataResponse) {
        mTitle = dataResponse.getTitle();
        mDescription = dataResponse.getDescription().substring(0, dataResponse.getDescription().indexOf("http"));
        mOrderId = dataResponse.getOrderId();
        mModificationDate = dataResponse.getModificationDate();
        mImageUrl = dataResponse.getImageUrl();
        mWebUrl = dataResponse.getDescription().substring(dataResponse.getDescription().indexOf("http"), dataResponse.getDescription().length());
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public void setWebUrl(String webUrl) {
        mWebUrl = webUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mWebUrl);
        dest.writeString(this.mTitle);
        dest.writeString(this.mDescription);
        dest.writeInt(this.mOrderId);
        dest.writeString(this.mModificationDate);
        dest.writeString(this.mImageUrl);
    }

    protected DataViewModel(Parcel in) {
        this.mWebUrl = in.readString();
        this.mTitle = in.readString();
        this.mDescription = in.readString();
        this.mOrderId = in.readInt();
        this.mModificationDate = in.readString();
        this.mImageUrl = in.readString();
    }

    public static final Parcelable.Creator<DataViewModel> CREATOR = new Parcelable.Creator<DataViewModel>() {
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
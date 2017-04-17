package pl.lukaszw.futuremind.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lukasz on 14.04.2017.
 */

public class DataModel {

    @SerializedName("title")
    protected String mTitle;

    @SerializedName("description")
    protected String mDescription;

    @SerializedName("orderId")
    protected int mOrderId;

    @SerializedName("modificationDate")
    protected String mModificationDate;

    @SerializedName("image_url")
    protected String mImageUrl;

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getOrderId() {
        return mOrderId;
    }

    public String getModificationDate() {
        return mModificationDate;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}

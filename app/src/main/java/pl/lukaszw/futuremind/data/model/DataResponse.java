package pl.lukaszw.futuremind.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lukasz on 14.04.2017.
 */

public class DataResponse {

    @SerializedName("data")
    private List<DataModel> mData;

    public List<DataModel> getDataModelList() {
        return mData;
    }
}

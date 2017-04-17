package pl.lukaszw.futuremind.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Lukasz on 14.04.2017.
 */

public class DataResponse extends RealmObject {

    @SerializedName("data")
    private RealmList<DataModel> mData;

    public RealmList<DataModel> getDataModelList() {
        return mData;
    }
}

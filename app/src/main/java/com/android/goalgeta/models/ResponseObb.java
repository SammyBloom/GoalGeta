package com.android.goalgeta.models;

import com.google.gson.annotations.SerializedName;

public class ResponseObb {
    @SerializedName("data")
    Data DataObject;


    // Getter Methods

    public Data getData() {
        return DataObject;
    }

    // Setter Methods

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }
}


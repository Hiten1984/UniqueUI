package au.com.sports.mate.test.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hiten Bahri on 28/8/18.
 */
public class ConnectionsItem implements Serializable {

    @SerializedName("fromElementID")
    private String fromElementID;

    @SerializedName("toElementIDs")
    private List<String> toElementIDs;

    public ConnectionsItem() {
    }

    public String getFromElementID() {
        return fromElementID;
    }

    public void setFromElementID(String fromElementID) {
        this.fromElementID = fromElementID;
    }

    public List<String> getToElementIDs() {
        return toElementIDs;
    }

    public void setToElementIDs(List<String> toElementIDs) {
        this.toElementIDs = toElementIDs;
    }
}

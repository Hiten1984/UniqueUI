package au.com.sports.mate.test.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hiten Bahri on 28/8/18.
 */
public class FinalsGridRows implements Serializable {

    @SerializedName("elementID")
    private String elementID;

    @SerializedName("items")
    List<List<FinalsGridRowItemsss>> items;

    public String getElementID() {
        return elementID;
    }

    public void setElementID(String elementID) {
        this.elementID = elementID;
    }

    public List<List<FinalsGridRowItemsss>> getItems() {
        return items;
    }

    public void setItems(List<List<FinalsGridRowItemsss>> items) {
        this.items = items;
    }

    public FinalsGridRows() {
    }

    public static class FinalsGridRowItemsss implements Serializable {

        @SerializedName("elementID")
        private String elementID;

        @SerializedName("style")
        private String style;

        @SerializedName("trophyRemoteImageURL")
        private String trophyRemoteImageURL;

        @SerializedName("matchIDs")
        private List<String> matchIDs;

        @SerializedName("leftTeamID")
        private String leftTeamID;

        @SerializedName("rightTeamID")
        private String rightTeamID;

        @SerializedName("type")
        private String type;

        @SerializedName("name")
        private String name;

        public FinalsGridRowItemsss() {
        }

        public String getElementID() {
            return elementID;
        }

        public void setElementID(String elementID) {
            this.elementID = elementID;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTrophyRemoteImageURL() {
            return trophyRemoteImageURL;
        }

        public void setTrophyRemoteImageURL(String trophyRemoteImageURL) {
            this.trophyRemoteImageURL = trophyRemoteImageURL;
        }

        public List<String> getMatchIDs() {
            return matchIDs;
        }

        public void setMatchIDs(List<String> matchIDs) {
            this.matchIDs = matchIDs;
        }

        public String getLeftTeamID() {
            return leftTeamID;
        }

        public void setLeftTeamID(String leftTeamID) {
            this.leftTeamID = leftTeamID;
        }

        public String getRightTeamID() {
            return rightTeamID;
        }

        public void setRightTeamID(String rightTeamID) {
            this.rightTeamID = rightTeamID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

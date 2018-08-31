package au.com.sports.mate.test.response;

import com.google.gson.annotations.SerializedName;


import java.util.List;

import au.com.sports.mate.test.model.AnnotationsItem;
import au.com.sports.mate.test.model.ConnectionsItem;
import au.com.sports.mate.test.model.FinalsGridRows;

/**
 * Created by Hiten Bahri on 28/8/18.
 */
public class FinalsGridResponse {

    @SerializedName("c")
    public FinalsGridFeedContent content;


    public static class FinalsGridFeedContent {

        @SerializedName("finalsGrids")
        List<FinalsGridItems> finalsGrid;

        public List<FinalsGridItems> getFinalsGrid() {
            return finalsGrid;
        }

        public void setFinalsGrid(List<FinalsGridItems> finalsGrid) {
            this.finalsGrid = finalsGrid;
        }
    }

    public static class FinalsGridItems {
        @SerializedName("competitionID")
        Integer competitionID;

        @SerializedName("rows")
        List<FinalsGridRows> rows;

        @SerializedName("annotations")
        List<AnnotationsItem> annotations;

        @SerializedName("connections")
        List<ConnectionsItem> connections;

        @SerializedName("competitionName")
        String competitionName;

        public Integer getCompetitionID() {
            return competitionID;
        }

        public void setCompetitionID(Integer competitionID) {
            this.competitionID = competitionID;
        }

        public List<FinalsGridRows> getRows() {
            return rows;
        }

        public void setRows(List<FinalsGridRows> rows) {
            this.rows = rows;
        }

        public List<AnnotationsItem> getAnnotations() {
            return annotations;
        }

        public void setAnnotations(List<AnnotationsItem> annotations) {
            this.annotations = annotations;
        }

        public List<ConnectionsItem> getConnections() {
            return connections;
        }

        public void setConnections(List<ConnectionsItem> connections) {
            this.connections = connections;
        }

        public String getCompetitionName() {
            return competitionName;
        }

        public void setCompetitionName(String competitionName) {
            this.competitionName = competitionName;
        }
    }

    public FinalsGridFeedContent getContent() {
        return content;
    }

    public void setContent(FinalsGridFeedContent content) {
        this.content = content;
    }
}

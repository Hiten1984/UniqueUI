package au.com.sports.mate.test.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hiten Bahri on 28/8/18.
 */

public class AnnotationsItem implements Serializable {

    @SerializedName("text")
    private String text;

    @SerializedName("elementID")
    private String elementID;

    @SerializedName("alignment")
    private String alignment;

    @SerializedName("edge")
    private String edge;

    public AnnotationsItem() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getElementID() {
        return elementID;
    }

    public void setElementID(String elementID) {
        this.elementID = elementID;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getEdge() {
        return edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }
}

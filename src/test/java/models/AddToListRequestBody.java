package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddToListRequestBody {
    @JsonProperty("media_id")
    private String mediaID;

    public AddToListRequestBody(String mediaID) {
        this.mediaID = mediaID;
    }

    public String getMediaID() {
        return mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }
}

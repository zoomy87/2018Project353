/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ejzumba
 */
public class Image {

    private StreamedContent image;
    private int imageID;
    private String vidUrl;

    public Image() {
        image = new DefaultStreamedContent();
    }

    public StreamedContent getImage() {
        return image;
    }

    public String getvidUrl() {
        return vidUrl;
    }

    public void setVideoURL(String vidUrl) {
        this.vidUrl = vidUrl;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public int getImageId() {
        return imageID;
    }

    public void setImageId(int imageID) {
        this.imageID = imageID;
    }

}

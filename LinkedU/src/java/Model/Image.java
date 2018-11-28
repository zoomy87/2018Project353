/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.primefaces.model.StreamedContent;

/**
 *
 * @author ejzumba
 */
public class Image {
    private StreamedContent image;
    private int imageID;

    public StreamedContent getImage() {
        return image;
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

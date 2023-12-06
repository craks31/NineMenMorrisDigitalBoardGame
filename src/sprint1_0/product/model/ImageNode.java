package sprint1_0.product.model;

import javafx.scene.image.Image;

public class ImageNode {
    Image image;
    ImageNode next;
    ImageNode prev;

    public ImageNode(Image image) {
        this.image = image;
        this.next = null;
        this.prev = null;
    }
}
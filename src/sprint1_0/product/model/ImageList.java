package sprint1_0.product.model;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class ImageList {
    private List<Image> images;
    private int currentIndex;

    public ImageList() {
        this.images = new ArrayList<>();
        this.currentIndex = 0;
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public Image getCurrentImage() {
        if (currentIndex >= 0 && currentIndex < images.size()) {
            return images.get(currentIndex);
        }
        return null;
    }

    public Image getFirstImage() {
        if (!images.isEmpty()) {
        	currentIndex = 0;
            return images.get(currentIndex);
        }
        return null;
    }

    public Image getLastImage() {
        if (!images.isEmpty()) {
        	currentIndex = images.size() - 1;
            return images.get(currentIndex);
        }
        return null;
    }

    public void moveForward() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
        }
    }

    public void moveBackward() {
        if (currentIndex > 0) {
            currentIndex--;
        }
    }
}

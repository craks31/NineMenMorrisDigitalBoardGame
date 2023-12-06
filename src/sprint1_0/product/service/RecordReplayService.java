package sprint1_0.product.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.ImageList;

public class RecordReplayService {
  private int screenshotCount = 1;
  private ImageList screenshots = new ImageList();
  private double popupWidth = 420;
  private double popupHeight = 550;
  private Stage popupStage;
  private String folder;

  public RecordReplayService() {
    createFolder();
  }

  public void createFolder() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    folder = dateFormat.format(new Date());
  }

  public void captureScreen(Stage stage, Board board) {
    DisplayUtil displayUtil = new DisplayUtil();
    displayUtil.displayOperation(board);
    WritableImage snapshot =
        new WritableImage(
            (int) stage.getScene().getWidth(), (int) stage.getScene().getHeight() - 10);
    stage.getScene().snapshot(snapshot);

    File timestampFolder = new File(".\\resources\\images\\output\\" + folder);

    if (!timestampFolder.exists()) {
      timestampFolder.mkdirs();
    }

    // Save the snapshot as an image file within the timestamp folder
    File outputFile = new File(timestampFolder, screenshotCount + ".png");

    screenshotCount++;

    try {
      ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", outputFile);
      System.out.println("Screenshot captured: " + outputFile.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void replayGame(Board board, Stage stage) {
    board.getReplayGameButton().setDisable(false);
    EventHandler<javafx.scene.input.MouseEvent> replayButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {
          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            board.getForwardButton().setDisable(false);
            board.getBackwardButton().setDisable(false);
            board.getFrontButton().setDisable(false);
            board.getEndButton().setDisable(false);
            loadScreenshots(folder);
            slideshowButtons(board, stage);
            showReplayPopup();
            board.getReplayGameButton().setDisable(true);
          }
        };
    board.getReplayGameButton().setOnMouseClicked(replayButtonEventHandler);
  }

  public void slideshowButtons(Board board, Stage stage) {

    EventHandler<javafx.scene.input.MouseEvent> forwardButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {
          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            showNextImage();
          }
        };
    board.getForwardButton().setOnMouseClicked(forwardButtonEventHandler);
    EventHandler<javafx.scene.input.MouseEvent> backwardButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {
          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            showPreviousImage();
          }
        };
    board.getBackwardButton().setOnMouseClicked(backwardButtonEventHandler);
    EventHandler<javafx.scene.input.MouseEvent> frontButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {
          @Override
          public void handle(javafx.scene.input.MouseEvent e) {

            Image currentImage = screenshots.getFirstImage();

            if (currentImage != null) {
              ImageView imageView = new ImageView(currentImage);
              StackPane popupRoot = new StackPane(imageView);
              Scene popupScene = new Scene(popupRoot, popupWidth, popupHeight);
              popupStage.setScene(popupScene);

              // Optionally show the stage if not already visible
              if (!popupStage.isShowing()) {
                popupStage.show();
              }
            }
          }
        };
    board.getFrontButton().setOnMouseClicked(frontButtonEventHandler);

    EventHandler<javafx.scene.input.MouseEvent> endButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {
          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            Image currentImage = screenshots.getLastImage();

            if (currentImage != null) {
              ImageView imageView = new ImageView(currentImage);
              StackPane popupRoot = new StackPane(imageView);
              Scene popupScene = new Scene(popupRoot, popupWidth, popupHeight);
              popupStage.setScene(popupScene);

              // Optionally show the stage if not already visible
              if (!popupStage.isShowing()) {
                popupStage.show();
              }
            }
          }
        };
    board.getEndButton().setOnMouseClicked(endButtonEventHandler);
  }

  private void loadScreenshots(String folderName) {
    // Load captured screenshots from the directory
    File screenshotDirectory = new File(".\\resources\\images\\output\\" + folderName);
    File[] screenshotFiles = screenshotDirectory.listFiles();

    if (screenshotFiles != null) {
      Arrays.sort(screenshotFiles, Comparator.comparingInt(this::extractIndex));

      for (File file : screenshotFiles) {
        screenshots.addImage(new Image(file.toURI().toString()));
      }
    }
  }

  private void showNextImage() {
    screenshots.moveForward();
    Image currentImage = screenshots.getCurrentImage();

    if (currentImage != null) {
      ImageView imageView = new ImageView(currentImage);
      StackPane popupRoot = new StackPane(imageView);
      Scene popupScene = new Scene(popupRoot, popupWidth, popupHeight);
      popupStage.setScene(popupScene);

      // Optionally show the stage if not already visible
      if (!popupStage.isShowing()) {
        popupStage.show();
      }
    }
  }

  private void showReplayPopup() {
    popupStage = new Stage();
    popupStage.setTitle("Replay Popup");

    // Initial image
    Image initialImage = screenshots.getFirstImage();
    if (initialImage != null) {
      ImageView imageView = new ImageView(initialImage);
      StackPane popupRoot = new StackPane(imageView);
      Scene popupScene = new Scene(popupRoot, popupWidth, popupHeight);

      popupStage.setScene(popupScene);
      popupStage.show();
    }
  }

  private void showPreviousImage() {
    screenshots.moveBackward();
    Image currentImage = screenshots.getCurrentImage();

    if (currentImage != null) {
      ImageView imageView = new ImageView(currentImage);
      StackPane popupRoot = new StackPane(imageView);
      Scene popupScene = new Scene(popupRoot, popupWidth, popupHeight);
      popupStage.setScene(popupScene);

      // Optionally show the stage if not already visible
      if (!popupStage.isShowing()) {
        popupStage.show();
      }
    }
  }

  private int extractIndex(File file) {
    // Extract the numeric index from the file name
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(file.getName());

    if (matcher.find()) {
      return Integer.parseInt(matcher.group());
    } else {
      return Integer.MAX_VALUE; // Default value if no numeric part found
    }
  }
}

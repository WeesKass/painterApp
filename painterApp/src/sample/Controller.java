package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Controller {

    private enum PenSize {
        SMALL(2),
        MEDIUM(4),
        LARGE(6);

        private final int radius;

        PenSize(int radius) {
            this.radius = radius;
        }

        public int getRadius() {
            return radius;
        }
    }

    @FXML
    private RadioButton blackRadioButton;

    @FXML
    private ToggleGroup colorToggleGroup;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField redTextField;

    @FXML
    private TextField greenTextField;

    @FXML
    private TextField blueTextField;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Rectangle colorRectangle;


    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    private PenSize radius = PenSize.MEDIUM;
    private Paint brushColor = Color.BLACK;

    public void initialize() {

        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);

        blackRadioButton.setUserData(Color.BLACK);
        redRadioButton.setUserData(Color.RED);
        greenRadioButton.setUserData(Color.GREEN);
        blueRadioButton.setUserData(Color.BLUE);


        redTextField.textProperty().bind(redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(alphaSlider.valueProperty().asString("%.2f"));

        redSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    red = newValue.intValue();
                    colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = colorRectangle.getFill();
                }
        );


        greenSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    green = newValue.intValue();
                    colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = colorRectangle.getFill();
                }
        );


        blueSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    blue = newValue.intValue();
                    colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = colorRectangle.getFill();
                }
        );


        alphaSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    alpha = newValue.doubleValue();
                    colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = colorRectangle.getFill();
                }
        );

        redTextField.textProperty().bind(redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(alphaSlider.valueProperty().asString("%.2f"));
    }


    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void colorRadioButtonSelected(ActionEvent event) {
        brushColor = (Color) colorToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    void colorRectangleChoosing(MouseEvent event) {
        brushColor = colorRectangle.getFill();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        if ((event.getX() > 0) && (event.getY() > 0 )) {
            Circle newCircle = new Circle(event.getX(), event.getY(), radius.getRadius(), brushColor);
            drawingAreaPane.getChildren().add(newCircle);
        }
    }
    @FXML
    void sizeRadioButtonSelected(ActionEvent event) {
        radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();
        if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }

}
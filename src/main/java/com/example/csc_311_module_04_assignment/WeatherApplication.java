package com.example.csc_311_module_04_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Nathaniel Rivera
 * @since 3/6/2025
 */

public class WeatherApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Double> temperatures = new ArrayList<>();
        ArrayList<Integer> humidity = new ArrayList<>();
        ArrayList<Double> precipitation = new ArrayList<>();

        try {
            String file = "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\weatherdata.csv";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                dates.add(row[0]);
                temperatures.add(Double.parseDouble(row[1]));
                humidity.add(Integer.parseInt(row[2]));
                precipitation.add(Double.parseDouble(row[3]));

            }


        } catch(IOException _) { }

        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("weather-pane-view.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().add(fxmlLoader.load());

        for(int i = 0; i < temperatures.size(); i++) {
            Rectangle rect = initializeRectangleColored(determineWeatherCategory(temperatures.get(i)), i);
            root.getChildren().add(rect);

            ImageView image = initializeIcon(precipitation.get(i), temperatures.get(i), i);
            root.getChildren().add(image);

            Label temp = initializeTemp(temperatures.get(i), i);
            root.getChildren().add(temp);

            Label humTitle = initializeHumidityTitle(i);
            root.getChildren().add(humTitle);

            Label hum = initializeHumidity(humidity.get(i), i);
            root.getChildren().add(hum);

            Label precTitle = initializePrecipitationTitle(i);
            root.getChildren().add(precTitle);

            Label prec = initializePrecipitation(precipitation.get(i), i);
            root.getChildren().add(prec);
        }

        Label avgTemp = placeAverageTemperature(temperatures);
        root.getChildren().add(avgTemp);

        Label totalRain = placeTotalRainyDays(precipitation);
        root.getChildren().add(totalRain);

        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("CSC311: Weather App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static String determineWeatherCategory(Double temp) {
        /*switch (temp) {
            case 80 -> {
                return "Hot";
            }
            case 70 -> {
                return "Warm";
            }
            case 50 -> {
                return "Cool";
            }
            case 33 -> {
                return "Cold";
            }
            case 32 -> {
                return "Freezing";
            }
        }*/
        return "";
    }


    /**
     * This method calculates the average temperature for the month by iterating through the given list and added up all the values. It then divides the total by the length of the array.) It possesses a runtime of O(n).
     * @param temps An ArrayList which contains all the values of the temperature for a given month.
     * @return Returns the average temperature for the given string.
     */
    public static double averageTemperature(ArrayList<Double> temps) {

        double average = 0;

        for (Double temp : temps) {
            average += temp;
        }

        return (average / temps.size());
    }

    /**
     * This method calculates the total amount of rainy days within a month. It uses the helper method isRaining() to determine whether or not it is raining on a specific day then adds it to the month. Runs in O(n) time.
     * @param precipitation An ArrayList of doubles which stores the amount of precipitation for a given day.
     * @return The total number of rainy days in the month in the form of an int.
     */
    public static int totalRainyDays(ArrayList<Double> precipitation) {

        int total = 0;

        for (Double pValue : precipitation) {
            if (isRaining(pValue)) {
                total++;
            }
        }
        return total;

    }

    /**
     * Helper method for totalRainyDays() and initializeIcon() methods. Takes in the precipitation value for a specific day and returns true if its raining and false if not. Runs in O(1) time.
     * @param precipitation A double which represents for the amount of precipitation for a day.
     * @return Returns true if the amount of precipitation > 0 and false otherwise.
     */
    public static boolean isRaining(double precipitation) {
        return precipitation != 0.0;
    }

    /**
     *
     * @param isRaining
     * @param temp
     * @return
     */
    public static String initializeIconType(boolean isRaining, double temp) {

        if(isRaining && temp > 32) {
            return "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\Images\\rain.png";
        } else if(isRaining && temp <= 32) {
            return "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\Images\\snow.png";
        } else {
            return "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\Images\\sun.png";
        }

    }


    /**
     * Creates a new rectangle object based on the data in the ArrayList. The rectangle changes color depending on the current weather category. Runs in O(1)
     * @param category The weather category of the current place in the List used for coloring the rectangle
     * @param dayNum The placement of the date in the List used for arranging.
     * @return A newly created rectangle object for the position of the date.
     */
    public static Rectangle initializeRectangleColored(String category, int dayNum) {
        Rectangle rect = new Rectangle();
        rect.setWidth(90);
        rect.setHeight(25.0);
        rect.setLayoutX(46 + 120 * (dayNum % 7));
        rect.setLayoutY(147 + 120 * (dayNum / 7));
        rect.setStroke(Color.BLACK);

        rect.setFill(Color.RED);
        return rect;
    }

    /**
     * Creates a ImageView with an image based on the weather conditions for the day. Runs in O(1) time.
     * @param precipitation THe precipitation of the current day of the List used for sorting cases.
     * @param temp The temperature of the current day of the List used for sorting cases.
     * @param dayNum The placement of the date in the List used for arranging.
     * @return An ImageView with either a sun, snow or rain icon.
     */
    public static ImageView initializeIcon(double precipitation, double temp, int dayNum) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(45.0);
        imageView.setFitHeight(45.0);
        imageView.setLayoutX(88.0 + 120 * (dayNum % 7));
        imageView.setLayoutY(175.0 + 120 * (dayNum / 7));

        Image image = new Image(initializeIconType(isRaining(precipitation), temp));
        imageView.setImage(image);

        return imageView;
    }

    /**
     *
     * @param date
     * @param dayNum
     * @return
     */
    public static Label initializeDate(String date, int dayNum) {
        Label dateLabel = new Label();
        dateLabel.setPrefHeight(63);
        dateLabel.setPrefWidth(18);
        dateLabel.setLayoutX(61 + 120 * (dayNum % 7));
        dateLabel.setLayoutY(46 + 120 * (dayNum / 7));

        dateLabel.setText("");
        return dateLabel;
    }

    /**
     *
     * @param temperature
     * @param dayNum
     * @return
     */
    public static Label initializeTemp(Double temperature, int dayNum) {
        Label tempLabel = new Label();
        tempLabel.setPrefHeight(45);
        tempLabel.setPrefWidth(40);
        tempLabel.setLayoutX(51 + 120 * (dayNum % 7));
        tempLabel.setLayoutY(169 + 120 * (dayNum / 7));
        tempLabel.setText(temperature + "°F");
        tempLabel.setFont(Font.font("Franklin Gothic Demi", FontWeight.LIGHT, FontPosture.REGULAR, 12));

        return tempLabel;
    }

    /**
     *
     * @param dayNum
     * @return
     */
    public static Label initializeHumidityTitle(int dayNum) {
        Label humidityTitle = new Label();
        humidityTitle.setPrefWidth(28.2);
        humidityTitle.setPrefHeight(11.2);
        humidityTitle.setLayoutX(48 + 120 * (dayNum % 7));
        humidityTitle.setLayoutY(223 + 120 * (dayNum / 7));
        humidityTitle.setFont(Font.font("System", FontWeight.LIGHT, FontPosture.REGULAR, 7));
        humidityTitle.setText("Humidity");

        return humidityTitle;
    }

    /**
     *
     * @param humidity
     * @param dayNum
     * @return
     */
    public static Label initializeHumidity(Integer humidity, int dayNum) {
        Label humidityLabel = new Label();
        humidityLabel.setPrefWidth(23.2);
        humidityLabel.setPrefHeight(17.6);
        humidityLabel.setLayoutX(48 + 120 * (dayNum % 7));
        humidityLabel.setLayoutY(230 + 120 * (dayNum / 7));
        humidityLabel.setText(Integer.toString(humidity));

        return humidityLabel;
    }

    /**
     *
     * @param dayNum
     * @return
     */
    public static Label initializePrecipitationTitle(int dayNum) {
        Label precTitle = new Label();
        precTitle.setPrefWidth(39.2);
        precTitle.setPrefHeight(11.2);
        precTitle.setLayoutX(95 + 120 * (dayNum % 7));
        precTitle.setLayoutY(223 + 120 * (dayNum / 7));
        precTitle.setFont(Font.font("System", FontWeight.LIGHT, FontPosture.REGULAR, 7));
        precTitle.setText("Precipitation");

        return precTitle;
    }

    /**
     *
     * @param precipitation
     * @param dayNum
     * @return
     */
    public static Label initializePrecipitation(Double precipitation, int dayNum) {
        Label precLabel = new Label();
        precLabel.setPrefWidth(23.2);
        precLabel.setPrefHeight(17.6);
        precLabel.setLayoutX(118 + 120 * (dayNum % 7));
        precLabel.setLayoutY(230 + 120 * (dayNum / 7));
        precLabel.setText(Double.toString(precipitation));

        return precLabel;
    }


    public static Label placeAverageTemperature(ArrayList<Double> temps) {
        Label avgTemp = new Label();
        avgTemp.setPrefHeight(20);
        avgTemp.setPrefWidth(300);
        avgTemp.setLayoutX(10);
        avgTemp.setLayoutY(775);
        avgTemp.setText("Average Temperature for this month is: " + Double.toString(averageTemperature(temps)) + "°F");
        avgTemp.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 12));

        return avgTemp;
    }

    public static Label placeTotalRainyDays(ArrayList<Double> prec) {
        Label totalRain = new Label();
        totalRain.setPrefHeight(20);
        totalRain.setPrefWidth(350);
        totalRain.setLayoutX(340);
        totalRain.setLayoutY(775);
        totalRain.setText("Total amount of days with Precipitation this month: " + Integer.toString(totalRainyDays(prec)));
        totalRain.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 12));

        return totalRain;
    }
}
package com.example.csc_311_module_04_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WeatherApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Double> temperatures = new ArrayList<>();
        ArrayList<Double> humidity = new ArrayList<>();
        ArrayList<Double> precipitation = new ArrayList<>();

        try {
            String file = "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\weatherdata.csv";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                dates.add(row[0]);
                temperatures.add(Double.parseDouble(row[1]));
                humidity.add(Double.parseDouble(row[2]));
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
        }

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
     * Helper method for totalRainyDays() method. Takes in the precipitation value for a specific day and returns true if its raining and false if not. Runs in O(1) time.
     * @param precipitation A double which represents for the amount of precipitation for a day.
     * @return Returns true if the amount of precipitation > 0 and false otherwise.
     */
    public static boolean isRaining(double precipitation) {
        return precipitation != 0.0;
    }

    public static String initializeIconType(boolean isRaining, double temp) {

        if(isRaining && temp > 32) {
            return "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\Images\\rain.png";
        } else if(isRaining && temp <= 32) {
            return "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\Images\\snow.png";
        } else {
            return "C:\\Users\\nycpu\\IdeaProjects\\CSC_311_Module_04_Assignment\\src\\main\\resources\\com\\example\\csc_311_module_04_assignment\\Images\\sun.png";
        }

    }

    public static Rectangle initializeRectangleColored(String category, int dayNum) {
        Rectangle rect = new Rectangle();
        rect.setWidth(90);
        rect.setHeight(25.0);
        rect.setLayoutX(46 + 120 * (dayNum % 7));
        rect.setLayoutY(147 + 120 * (dayNum / 7));

        rect.setFill(Color.RED);
        return rect;
    }

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
}
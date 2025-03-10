# Weather Report App

The Weather Report App is a app that takes in a CSV file and outputs a weather report in the style of a calendar. The calendar displays the date alongside the temperature, precipitation amount and humidity for said date. It also provides a small icon for the current weather and colored boxes depending on the temperature category.

## Sections

[Implementation](#implementation).
[CSV File](#csv-file).
[Limitations and Future Implementations](#limitations-and-future-implementations).
[Contributors](#contributors).

### Implementation

By using JavaFX, Scenebuilder and CSS the calendar GUI is stylizied to give a plesant but easy to read experience for the user. Displaying the date, temperature, humidity, precipitation amount, icons for the current weather conditions, the average temperature, and total rainfall in a month and colored areas for each different weather condition. The user can input their own CSV file for the month to display their data for their own weather report as long as they follow these [constraints](#csv-file).

### CSV File

The CSV file input must follow the following format (Date,Temperature,Humidity,Precipitation). The date is structored as a String reading year-day-month all in digits. The temperature is a double and is measured in degrees Farenheight. The humidity and precipitation are both integers.

Example of this would be: 2025-03-10,62.3,60,0.0

### Limitations and Future Implementations

The weather report is unable to sort the data currently by the day of the week. This means all the data always popluates from left to right making it impossible to know the day of the week in which the weather report occurs.

The calendar portion has a maxmimum capacity of 35 any more inputs then 35 and they will begin to spill off the application wihtout any room to go.

Crossing between months still keeps the title for the first day in the month. So if the first date in the weather report begins in February then the weather reports title will be "Weather Report for the Month of February" even if the other dates enter March.

### Contributors

Nathaniel Rivera (Riiel-edu)


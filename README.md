# DailyBriefing

## Project Overview

DailyBriefing is a simple app for college students and young professionals to get a handle on their day. They can run the app and get the temperature for their location, or a forecast for the week. This will eventually be expanded to include default weather locations, other weather data like wind and weather conditions, closing suggestions, and even facts about this day in history.

## Project Plan

### Iteration 1

- Find a Weather API
- Figure out how to connect to a Weather API in Java
- Get weather forecast or current temperature for a location
- Show current time and date for current weather

### Iteration 2

- Allow for default/remembered weather locations
- Show other weather data (wind, humidity, etc)
- Add clothing suggestions for given weather

### Iteration 3

- Visual presentation cleanup
- This day in history data

## Fixing Build Path

If you have an issue with libraries not linking appropriately, you will need to add them to your module path.

We have put all the libraries you should need in `./DailyBriefing/lib`. You will want to right click on the project, and go to `Build Path > Configure Build Path`. From there you want to go to the `Libraries` tab, and `Add JARs`. DO NOT `Add external JARs`, as this will not work. When you are done, make sure to `Apply and Close`.

If your `libraries` tab has module path and class path sections, then you must have the Gson jar in the class path. If the Gson jar is in the module path and not the class path, then drag it into the class path in the `Libraries` tab.

## A Note on Java Version

So there's a big issue when you try to use Java >=9 and any of the smart JSON libraries- see [this](https://stackoverflow.com/questions/41265266/how-to-solve-inaccessibleobjectexception-unable-to-make-member-accessible-m) post for details but essentially they can't smart map to an object since it isn't exported. Therefore we use JavaSE-1.8

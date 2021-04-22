# DailyBriefing

## Building and Running the App

Included is a script file, build-and-run.sh. Github will sometimes make this file non executable when you download it, so you have to either make it an executable yourself or execute it with the command `bash ./build-and-run.sh`. This will compile and run the program.

## Project Overview

DailyBriefing is a simple app for college students and young professionals to get a handle on their day. They can run the app and get the temperature for their location, or a forecast for the week. This will eventually be expanded to include default weather locations, other weather data like wind and weather conditions, closing suggestions, and even facts about this day in history. Weather data taken from the api at https://www.metaweather.com/api/.

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
- Show random fact from this day in history when providing a DailyBriefing (i.e. using your set default weather location)
- Updated readme to be accurate
- Fixed a bug where an out of data database file would crash the app
- Added credit for our API/Data sources when launching the app
- Refactored clothing suggestions to be in their own class

## Repository Structure

When coding for this project, we followed a standard paradigm of `feature` branches off of a `development` branch, which then gets merged into a `master` branch later. Bugs, objectives, and iteration goals are added to the Kanban board. One of the team members assigns themselves the issue and creates a branch off of master named appropriately with their name and whatever issue they are solving. They write code, then make a pull request to merge back into `development`. When we have a new release version of the app, we create a release PR from `development` into `master`. Please check out the `master` branch if you are looking for the completed iteration 3 version of the app, or the `development` branch for the most recent dev version of the code. Project code is found under src/project, and tests are found under src/test

## Fixing Build Path

If you have an issue with libraries not linking appropriately, you will need to add them to your module path.

We have put all the libraries you should need in `./DailyBriefing/lib`. You will want to right click on the project, and go to `Build Path > Configure Build Path`. From there you want to go to the `Libraries` tab, and `Add JARs`. DO NOT `Add external JARs`, as this will not work. When you are done, make sure to `Apply and Close`.

If your `libraries` tab has module path and class path sections, then you must have the Gson jar in the class path. If the Gson jar is in the module path and not the class path, then drag it into the class path in the `Libraries` tab.

## A Note on Java Version

So there's a big issue when you try to use Java >=9 and any of the smart JSON libraries- see [this](https://stackoverflow.com/questions/41265266/how-to-solve-inaccessibleobjectexception-unable-to-make-member-accessible-m) post for details but essentially they can't smart map to an object since it isn't exported. Therefore we use JavaSE-1.8

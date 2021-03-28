# cse237-project

## Fixing Build Path

If you have an issue with libraries not linking appropriately, you will need to add them to your module path.

We have put all the libraries you should need in `./DailyBriefing/lib`. You will want to right click on the project, and go to `Build Path > Configure Build Path`. From there you want to go to the `Libraries` tab, and `Add JARs`. DO NOT `Add external JARs`, as this will not work. When you are done, make sure to `Apply and Close`.

If your `libraries` tab has module path and class path sections, then you must have the Gson jar in the class path. If the Gson jar is in the module path and not the class path, then drag it into the class path in the `Libraries` tab. 

## A Note on Java Version

So there's a big issue when you try to use Java >=9 and any of the smart JSON libraries- see [this](https://stackoverflow.com/questions/41265266/how-to-solve-inaccessibleobjectexception-unable-to-make-member-accessible-m) post for details but essentially they can't smart map to an object since it isn't exported. Therefore we use JavaSE-1.8

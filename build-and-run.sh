# compiles the project, linking the GSON impl
javac -cp lib/* ./src/project/*.java

# tuns the project, linking the GSON impl
java -cp "lib/*:src" project.Main

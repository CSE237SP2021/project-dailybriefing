#!/bin/bash

# compiles the project, linking the GSON impl
javac -cp lib/* ./src/project/*.java &> /dev/null

if test -f "db.json"; then
	echo "Found old app data. Would you like to delete it? (recommended to delete if updating app) y/n"
	read ANSWER
	if [ "$ANSWER" = "y" ]; then
		rm db.json
		echo "Deleted old data"
	fi

	if [ "${ANSWER,,}" = "yes" ]; then
		rm db.json
		echo "Deleted old data"
	fi
fi

echo "Daily Briefing App Built"

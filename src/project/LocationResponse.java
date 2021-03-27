package project;

import java.util.ArrayList;

import project.Location;

//The wrapper data type for a request to the location/search metaweatherapi endpoint
public class LocationResponse {
	public Location[] locations;
	
	public LocationResponse() {
		this.locations = null;
	}
}

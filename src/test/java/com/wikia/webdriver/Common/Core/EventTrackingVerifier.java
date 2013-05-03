package com.wikia.webdriver.Common.Core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.stream.JsonReader;

public class EventTrackingVerifier {

	private String harFilesPath;
	private ArrayList<String> trackedEventsList;
	
	public EventTrackingVerifier(String harFilePath) {
		this.harFilesPath = harFilePath;
		trackedEventsList = getTrackedEvents("harFileName");
	}

	public void verifyEvent(String eventContent) {				
	}
	
	/**
	 * The method makes use of high-performance gson library, dedicated for json files analysis and developed by google programmers.
	 * This method analizes structure of the json file exported by netExport firebug plugin, and searches for the following token path:
	 * entries -> request -> headers -> host with value 'www.google.analytics.com' and if such token is found, it searches further for event name which is stored in 'queryString' array
	 * @param harFileName name of the file exported by netExport, eg. muppet.michalnowierski.wikia-dev.com+2013-04-29+16-50-27.har
	 * 
	 * @return list with all events reported by WikiaTracker in analized har file
	 */
	private ArrayList<String> getTrackedEvents(String harFileName) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			JsonReader reader = new JsonReader(new FileReader(harFilesPath+"muppet.michalnowierski.wikia-dev.com+2013-04-29+16-50-27.har"));
		 
			reader.beginObject();
		 		 
			String name = reader.nextName();
		      System.out.println(name);
		      reader.beginObject();
		      while (reader.hasNext()) {		
		    	switch (reader.peek()) {
				case NAME:
					name = reader.nextName();
					System.out.println("string name: " + name);
					if (name.equals("entries")) {
						reader.beginArray();
						reader.beginObject();
					}
					if (name.equals("request")) {
						reader.beginObject();
					}
					if (name.equals("headers")) {
						reader.beginArray();
						reader.beginObject();
					}
					if (name.equals("name")) {
						String isHost = reader.nextString();
						if (isHost.equals("Host")) {
							reader.skipValue();
							if (reader.nextString().equals("www.google-analytics.com")) {
								reader.endObject();
								while (!reader.peek().name()
										.equals("END_ARRAY")) {
									reader.skipValue();
								}
								reader.endArray();
							} else {
								reader.endObject();
								while (!reader.peek().name()
										.equals("END_ARRAY")) {
									reader.skipValue();
								}
								reader.endArray();
								while (!reader.peek().name()
										.equals("END_OBJECT")) {
									reader.skipValue();
								}
								reader.endObject();
							}
						}
					}
					if (name.equals("queryString")) {
						reader.beginArray();
						while (!reader.peek().name()
								.equals("END_ARRAY")) {
							reader.beginObject();
							reader.skipValue();
							if (reader.nextString().equals("utme")) {
								reader.skipValue();
								lista.add(reader.nextString());
								System.out.println(lista.get(lista.size()-1));
								reader.endObject();
							}
							else {
								reader.skipValue();								
								reader.skipValue();								
								reader.endObject();
							}
						}
						reader.endArray();
						while (!reader.peek().name().equals("END_OBJECT")) {
							reader.skipValue();
						}
						reader.endObject();
					}
					if (name.equals("timings")) {
						reader.skipValue();
						reader.endObject();
						if (!reader.peek().name().equals("END_ARRAY")) {							
							reader.beginObject();
						}
						else {
							reader.endArray();
						}
					}
					break;
				case STRING:
				if (reader.nextString().equals("utme")) {
					reader.skipValue();
					lista.add(reader.nextString());
					reader.endObject();
					reader.endArray();
				}
				break;
				default: System.out.println("default and skip because: "+reader.peek().name()); reader.skipValue();
					break;
				}  		    			    	
		      }	
		 
		     System.out.println("found events: ");
		     for (int i = 0; i < lista.size(); i++) {
				System.out.println(lista.get(i));
			}
			reader.endObject();
			reader.close();
		 
		     } catch (FileNotFoundException e) {
			e.printStackTrace();
		     } catch (IOException e) {
			e.printStackTrace();
		     }	  
		return lista;
	}


}

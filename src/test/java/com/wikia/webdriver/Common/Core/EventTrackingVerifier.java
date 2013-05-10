package com.wikia.webdriver.Common.Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.stream.JsonReader;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**This class is used for verification,
 * If wikia.tracker is logging events properly and sends them to google.analytics
 * 
 * @author Michal "justnpT" Nowierski
 *
 */
public class EventTrackingVerifier {

	private ArrayList<String> trackedEventsList;
	
	public EventTrackingVerifier(String harDirtPath) {
		trackedEventsList = getTrackedEventsFromFolder(harDirtPath);
	}

	private ArrayList<String> getTrackedEventsFromFolder(String harDirPath) {
		ArrayList<String> finalTrackedEvents = new ArrayList<String>();
		ArrayList<String> listOfHarFiles = new ArrayList<String>();
		listOfHarFiles = getAllHarFiles(harDirPath);
		for (int i = 0; i < listOfHarFiles.size(); i++) {
			finalTrackedEvents.addAll(getTrackedEventsFromFile(harDirPath+listOfHarFiles.get(i)));
		}
		return finalTrackedEvents;
	}

	/**
	 * returns list of all .har files names in specified folder
	 * 
	 * @param harDirPath
	 * @return
	 */
	private ArrayList<String> getAllHarFiles(String harDirPath) {
		ArrayList<String> fileNamesList = new ArrayList<String>();
		File Folder = new File(harDirPath);
		File[] listOfFiles = Folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				fileNamesList.add(file.getName());
			}
		}
		return fileNamesList;
	}

	/**
	 * verify if specified event was logged by Wikia.tracker
	 * 
	 * @param event
	 *            name, eg: "editor-ck*view*edit-page"
	 */
	public void verifyEvent(String eventContent) {
		for (int i = 0; i < trackedEventsList.size(); i++) {
			if (trackedEventsList.get(i).contains(eventContent)) {
				PageObjectLogging.log("verifyEvent", eventContent
						+ " event was logged by wikia tracker", true);
				break;
			} else {
				if (i == trackedEventsList.size() - 1) {
					PageObjectLogging.log("verifyEvent", eventContent
							+ " event wasn't logged by wikia tracker", false);
				}
			}
		}
	}
	
	/**
	 * The method makes use of high-performance gson library, dedicated for json
	 * files analysis and developed by google programmers. This method analizes
	 * structure of the json file exported by netExport firebug plugin, and
	 * searches for the following token path: 
	 * entries -> request -> headers -> host with value 'www.google.analytics.com' 
	 * and if such token is found, it searches further for event name 
	 * which is stored in 'queryString' array
	 * 
	 * @param harFileName
	 *            name of the file exported by netExport, eg.
	 *            muppet.michalnowierski.wikia-dev.com+2013-04-29+16-50-27.har
	 * 
	 * @return list with all events reported by WikiaTracker in analized har
	 *         file
	 */
	private ArrayList<String> getTrackedEventsFromFile(String harFileName) {
		PageObjectLogging.log("getTrackedEventsFromFile",
				"starting process of extracting events from file: "
						+ harFileName, true);
		ArrayList<String> listOfTrackedEvents = new ArrayList<String>();
		try {
			JsonReader reader = new JsonReader(new FileReader(harFileName));
			reader.beginObject();
			String name = reader.nextName();
			reader.beginObject();
			while (reader.hasNext()) {
				switch (reader.peek()) {
				case NAME:
					name = reader.nextName();
					if (name.equals("entries")) {
						reader.beginArray();
						reader.beginObject();
					}
					if (name.equals("request")) {
						reader.beginObject();
					}
					if (name.equals("headers")) {
						reader.beginArray();
						if (reader.peek().name().equals("END_ARRAY")) {
							reader.endArray();
						} else {
							reader.beginObject();
						}
					}
					if (name.equals("name")) {
						String isHost = reader.nextString();
						if (isHost.equals("Host")) {
							reader.skipValue();
							if (reader.nextString().equals(
									"www.google-analytics.com")) {
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
						while (!reader.peek().name().equals("END_ARRAY")) {
							reader.beginObject();
							reader.skipValue();
							if (reader.nextString().equals("utme")) {
								reader.skipValue();
								listOfTrackedEvents.add(reader.nextString());
								reader.endObject();
							} else {
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
						} else {
							reader.endArray();
						}
					}
					break;
				case STRING:
					if (reader.nextString().equals("utme")) {
						reader.skipValue();
						listOfTrackedEvents.add(reader.nextString());
						reader.endObject();
						reader.endArray();
					}
					break;
				default:
					reader.skipValue();
					break;
				}
			}
			reader.endObject();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfTrackedEvents;
	}
}
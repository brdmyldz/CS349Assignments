package screens;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    HashMap<String, String> settings = new HashMap<>();
    ArrayList<IObserver> observers = new ArrayList<>();

	Model() {
		for (String key : new String[]{
				"java.vendor", "java.version", "java.home", "java.class.path",
				"os.name", "os.arch", "os.version"}) {
			settings.put(key, System.getProperty(key));
		}
	}

	// manager observer list
	public void addView(IObserver scene) {
		observers.add(scene);
	}

	public void removeView(IObserver scene) {
		observers.remove(scene);
	}

	public void update() {
		for (IObserver observer : observers) {
			observer.notify();
		}
	}

	// fetch data on demand
	public String getValue(String key) {
		return settings.get(key);
	}

	public String toString() {
        StringBuilder s = new StringBuilder();
		settings.forEach((key, value) -> s.append(key).append(": ").append(value).append("\n"));
        return s.toString();
    }
}
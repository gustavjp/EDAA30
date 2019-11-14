package lab3;
import java.util.*;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> wordMap;

	public MultiWordCounter(String[] words) {
		wordMap = new TreeMap<String, Integer>();
		for(int i = 0; i < words.length; i++) {
			wordMap.put(words[i], 0);
		}
	}

	public void process(String w) {
		if(wordMap.containsKey(w)) {
			wordMap.put(w, wordMap.get(w) + 1);
		}
	}

	public void report() {
		for(String key : wordMap.keySet()) {
			System.out.println(key + ": " + wordMap.get(key));
		}
	}
}

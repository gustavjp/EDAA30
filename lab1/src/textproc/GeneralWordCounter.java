package textproc;
import java.util.*;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> wordMap;
	private Set<String> stopWords;
	
	public GeneralWordCounter(Set<String> stopWords) {
		this.stopWords = stopWords;
		wordMap = new TreeMap<String, Integer>();
	}

	public void process(String w) {
		if(!stopWords.contains(w)) {
			if(!wordMap.containsKey(w)) {
				wordMap.put(w, 1);
			} else {
				wordMap.put(w, wordMap.get(w) + 1);
			}
		}
	}

	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = wordMap.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for(int i = wordList.size() - 1; i >= wordList.size() - 5; i--) {
			System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
		}
		/*for(String key : wordMap.keySet()) {
			if(wordMap.get(key) >= 200) {
				System.out.println(key + ": " + wordMap.get(key));
			}
		}*/
		
	}
	
	public Set<Map.Entry<String, Integer>> getWords() {
		return wordMap.entrySet();
	}

}

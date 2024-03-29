package lab3;
import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if(o1.getValue() > o2.getValue()) {
			return 1;
		} else if(o1.getValue() < o2.getValue()) {
			return -1;
		} else {
			if(o1.getKey().compareTo(o2.getKey()) < 0) {
				return 1;
			} else if(o1.getKey().compareTo(o2.getKey()) > 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

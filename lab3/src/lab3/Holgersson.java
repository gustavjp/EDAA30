package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		Scanner ss = new Scanner(new File("undantagsord.txt"));
		ss.findWithinHorizon("\uFEFF", 1);
		ss.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		Set<String> stopWords = new HashSet<String>();
		while(ss.hasNext()) {
			String word = ss.next().toLowerCase();
			stopWords.add(word);
		}
		ss.close();
		
		ArrayList<TextProcessor> processorList = new ArrayList<TextProcessor>();
		processorList.add(new SingleWordCounter("nils"));
		processorList.add(new SingleWordCounter("norge"));
		TextProcessor mp = new MultiWordCounter(REGIONS);
		TextProcessor gp = new GeneralWordCounter(stopWords);
		long t0 = System.nanoTime();
		while(s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor p : processorList) {
				p.process(word);
			}
			mp.process(word);
			gp.process(word);
		}
		s.close();
		System.out.println("SingleWordCounter: ");
		for(TextProcessor p : processorList) {
			p.report();
		}
		System.out.println("MultiWordCounter: ");
		mp.report();
		System.out.println("GeneralWordCounter: ");
		gp.report();
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}
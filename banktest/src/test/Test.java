package test;
import java.util.*;

public class Test {

	public static void main(String[] args) {
		//List<Integer> nbrs = new ArrayList<Integer>();
		Set<Integer> nbrs = new HashSet<Integer>();
		for (int i = 0; i < 100; i += 10) {
		nbrs.add(i);
		nbrs.add(i); // notera: talet läggs till två gånger
		}
		for (int a : nbrs) {
		System.out.println(a);
		}
	}

}

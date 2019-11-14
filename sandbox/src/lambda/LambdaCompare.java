package lambda;

import java.util.*;

public class LambdaCompare {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Nilsson, Sten", 13));
		persons.add(new Person("Jonsson, Camilla", 23));
		persons.add(new Person("Hermansson, Lena", 38));
		persons.add(new Person("Fransson, Anneli", 15));
		persons.add(new Person("Lundström, David", 21));
		persons.add(new Person("Björk, Stefan", 20));
		persons.add(new Person("Andersson, Gun", 55));
		persons.add(new Person("Lundgren, Carina", 12));
		persons.add(new Person("Svensson, Ulf", 47));

		// sortera personerna i listan med avseende på ålder
		persons.sort((Person p1, Person p2) -> new Integer(p1.getAge()).compareTo(new Integer(p2.getAge())));

		//Collections.sort(persons, (Person p1, Person p2) -> new Integer(p1.getAge()).compareTo(new Integer(p2.getAge())));

		for (Person p : persons) {
			System.out.println(p);
		}
		
		System.out.println("CLEAR CLEAR CLEAR");
		
		List<Map.Entry<Object, Integer>> l = new ArrayList<>();
		l.sort((e1, e2) -> e1.getValue().compareTo(e2.getValue()));
		List<Integer> li = new ArrayList<>();
		li.add(27);
		li.add(1);
		li.add(4);
		li.add(3);
		li.sort((i1, i2) -> i1-i2);
		for(Integer i : li) {
			System.out.println(i);
		}
		
	}

}

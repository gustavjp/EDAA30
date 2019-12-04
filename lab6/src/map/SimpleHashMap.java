package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	private Entry<K,V>[] table;
	private int capacity;
	private int size;
	private static final double LOAD_FACTOR = 0.75;
	
	/**
	 * Constructs an empty hashmap with the default initial capacity (16)
	 * and the default load factor (0.75).
	 */
	public SimpleHashMap() {
		this(16);
	}
	
	/**
	 * Constructs an empty hashmap with the specified initial capacity
	 * and the default load factor (0.75).
	 */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
	}
	
	public String show() {
		String str = "";
		for(int i = 0; i < table.length; i++) {
			str += i + " ";
			Entry<K,V> temp = table[i];
			while(temp != null) {
				str += temp.toString() + " ";
				temp = temp.next;
			}
			str += "\n";
		}
		return str;
	}
	
	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
	private Entry<K,V> find(int index, K key) {
		Entry<K,V> temp = table[index];
		while(temp != null) {
			if(temp.getKey().equals(key)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}
	
	private void rehash() {
		capacity *= 2;
		Entry<K,V>[] oldTable = table;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
		for(Entry<K,V> e : oldTable) {
			while(e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}

	@Override
	public V get(Object arg0) {
		Entry<K,V> e = find(index((K) arg0), (K) arg0);
		if(e != null) {
			return e.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = index(arg0);
		Entry<K,V> e = find(index, arg0);
		if(e != null) {
			return e.setValue(arg1);
		}
		Entry<K,V> newEntry = new Entry<K,V>(arg0, arg1);
		if(table[index] != null) {
			newEntry.next = table[index];
		}
		table[index] = newEntry;
		size++;
		if((double) size/capacity > LOAD_FACTOR) {
			rehash();
		}
		return null;
	}

	@Override
	public V remove(Object arg0) {
		if(!isEmpty()) {
			K key = (K) arg0;
			int index = index(key);
			Entry<K,V> temp = table[index];
			if(temp.getKey().equals(key)) {
				table[index] = table[index].next;
				size--;
				return temp.getValue();
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	private static class Entry<K,V> implements Map.Entry<K, V> {
		
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}
		
		@Override
		public String toString() {
			return key + " = " + value;
		}	
	}
}

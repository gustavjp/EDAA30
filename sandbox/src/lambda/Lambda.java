package lambda;

public class Lambda {
	public static void main(String[] args) {

		// Anonymous example
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("one");
			}
		};

		// Lambda example
		Runnable r2 = () -> {
			System.out.println("two");
		};

		r1.run();
		r2.run();
	}

}

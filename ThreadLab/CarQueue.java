package threadLab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	
	Random rand;
	Queue<Integer> directions;
	
	public CarQueue() {
		directions = new LinkedList<>();
		rand = new Random();
		directions.add(0);
		directions.add(3);
		directions.add(1);
		directions.add(2);
		directions.add(3);
		directions.add(0);	
	}
	
	public void addToQueue() {
		class directionsRandom implements Runnable {
			public void run() {
				
				try {
									
				for (int i =0; i< 500; i++) {
					 int random = rand.nextInt(4);
					 directions.add(random);
					 Thread.sleep(100);
				}
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		Runnable run = new directionsRandom();
		Thread t = new Thread(run);
		t.start();
	}

	public int deleteQueue() {
		return directions.remove();
	}
}

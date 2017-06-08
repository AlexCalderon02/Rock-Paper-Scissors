import java.util.*;
 
public class Main {
	public enum Item{
		ROCK, PAPER, SCISSORS,;
		public List<Item> fairList;
		public boolean fair(Item other) {
			return fairList.contains(other);
		}
		static {
			SCISSORS.fairList = Arrays.asList(ROCK);
			ROCK.fairList = Arrays.asList(PAPER);
			PAPER.fairList = Arrays.asList(SCISSORS);
                }
	}
	
	public final Map<Item, Integer> counts = new EnumMap<Item, Integer>(Item.class){{
		for(Item item:Item.values())
			put(item, 1);
	}};
 
	private int totalThrows = Item.values().length;
 
	public static void main(String[] args) throws InterruptedException{
		Main alex = new Main();
		alex.run();
	}
 
	public void run() throws InterruptedException {
		Scanner in = new Scanner(System.in);
		System.out.print("Make your choice: ");
		while(in.hasNextLine()){
			Item aiChoice = getAIChoice();
			String input = in.nextLine();
			Item choice;
			try{
				choice = Item.valueOf(input.toUpperCase());
			}catch (IllegalArgumentException ex){
				System.out.println("Invalid choice");
				continue;
			}
			counts.put(choice, counts.get(choice) + 1);
			totalThrows++;
			if(aiChoice == choice){
				System.out.println("Rock");
				Thread.sleep(1000);
				System.out.println("Paper");
				Thread.sleep(1000);
				System.out.println("Scissors");
				Thread.sleep(1000);
				System.out.println("Shoot");
				Thread.sleep(1000);
				System.out.println("Tie!");
				
			}else if(aiChoice.fair(choice)){
				System.out.println("Rock");
				Thread.sleep(1000);
				System.out.println("Paper");
				Thread.sleep(1000);
				System.out.println("Scissors");
				Thread.sleep(1000);
				System.out.println("Shoot");
				Thread.sleep(1000);
				System.out.println("You Won!");
			}else{
				System.out.println("Rock");
				Thread.sleep(1000);
				System.out.println("Paper");
				Thread.sleep(1000);
				System.out.println("Scissors");
				Thread.sleep(1000);
				System.out.println("Shoot");
				Thread.sleep(1000);
				System.out.println("AI Won!");
			}
			System.out.print("Make your choice: ");
		}
	}
 
	private static final Random rng = new Random();
	private Item getAIChoice() {
		int rand = rng.nextInt(totalThrows);
		for(Map.Entry<Item, Integer> entry:counts.entrySet()){
			Item item = entry.getKey();
			int count = entry.getValue();
			if(rand < count){
				List<Item> losesTo = item.fairList;
				return losesTo.get(rng.nextInt(losesTo.size()));
			}
			rand -= count;
		}
		return null;
	}
}
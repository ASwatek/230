import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author <<TODO>>
 */
public class Doublets {
	private LinksInterface links;
	private HashSet<String> seenWords;

	public Doublets(LinksInterface links) {
		this.links = links;
		this.seenWords = new HashSet<>();
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to Doublets! A game of verbal torture!");
		System.out.println("loading");
		LinksInterface links = new Links("../DoubletsData/english.cleaned.all.35.txt");
		Doublets doublet = new Doublets(links);

		System.out.println("Please enter a starting word: ");
		String start = input.nextLine();
		int count = 0;

		while (!start.equalsIgnoreCase("x")) {
			if (count != 0) {
				System.out.println("Please enter a starting word: ");
				start = input.nextLine();
			}
			count++;

			System.out.println("Please enter an end word: ");

			String end = input.nextLine();

			System.out.println(
					"Please enter a Chain Manager (S - Stack, Q - Queue, P - Priority Queue, X - Exit) to use: ");

			String chainManagerType = input.nextLine();

			ChainManager chosenManager = new StackChainManager();

			if (chainManagerType.equalsIgnoreCase("s")) {
				chosenManager = new StackChainManager();
			} else if (chainManagerType.equalsIgnoreCase("q")) {
				chosenManager = new QueueChainManager();
			} else if (chainManagerType.equalsIgnoreCase("p")) {
				chosenManager = new PriorityQueueChainManager(end);
			} else if (chainManagerType.equalsIgnoreCase("x")) {
				System.out.println("Goodbye!");
				System.exit(0);
			} else {
				System.out.println("That's not a valid Chain Manager type!");
			}
			Chain resultChain = doublet.findChain(start, end, chosenManager);
			if (resultChain == null) {
				System.out.println("No Doublet chain exists from " + start + " to " + end);
			} else {
				System.out.println("Result: ");

				String resultString = resultChain.toString();

				System.out.println("Chain: " + resultChain.toString());
				System.out.println("Length: " + resultChain.length());
				System.out.println("Max Size: " + chosenManager.maxSize());
				System.out.println("Candidates: " + chosenManager.getNumberOfNexts());

			}
			doublet.seenWords.clear();
		}
		System.exit(0);
		System.out.println("The end.");
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		if (start.length() != end.length()) {
			return null;
		}

		if (this.links.getCandidates(start) == null) {
			return null;
		}

		Chain startChain = new Chain(start);
		manager.add(startChain);

		while (!manager.isEmpty()) {
			Chain currentChain = manager.next();
			if (currentChain.last.equals(end)) {
				seenWords.clear();
				return currentChain;
			}
			if (!alreadySeen(currentChain.last)) {
				Set<String> candidates = this.links.getCandidates(currentChain.last);

				if (candidates == null) {
					continue;
				}
				for (String candidate : candidates) {
					manager.add(new Chain(currentChain.set, candidate));
				}
				seenWords.add(currentChain.last);
			}
		}
		seenWords.clear();
		return null;
	}

	public boolean alreadySeen(String lastWord) {
		return (seenWords.contains(lastWord));
	}

}
/**
 * Created by ethan on 2016-11-15. This class represents a Trie specifically
 * built to handle queries on Strings. There is intentionally no remove methods,
 * though in other applications there should be. The purpose of this class is to
 * represent words and word prefixes using an efficient organization. Some of
 * the basic functionality is implemented for you, but it will be up to you to
 * think about how to implement the other methods. Recursion is a must for this
 * assignment.
 */
public class StringTrie {

	private TrieNode root; // The root of the StringTrie
	private int numWords; // A variable to store the precomputed vocabulary size

	// Constructor
	public StringTrie() {
		root = new TrieNode();
	}

	public int getNumWords() {
		return numWords;
	}

	// Exposes the private search method starting from the root
	public void insert(String s) {
		insert(root, s);
	}

	// Given a String, recursively add its Characters to the Trie
	// Characters correspond to edges in the tree. The addChild method
	// in TrieNode already makes sure that duplicate edges are not added.
	// Be sure to check the code!
	//
	// Notice again that the children of a node are represented using MapToNode
	// objects,
	// and not TrieNodes directly!!! This is done so that each child corresponds
	// to a Character.
	private void insert(TrieNode n, String s) {
		if (s.length() == 0) {
			n.incrementCount();
			return;
		} else {
			char c = s.charAt(0);

			n.addChild(c);
			// TrieNode x =(TrieNode) n.getChildrenMaps();
			insert(n.getChild(c), s.substring(1, s.length()));
		}

	}

	public TrieNode find(String s) {
		return find(root, s);
	}

	// This method should recursively search for a node that
	// matches the prefix String s
	private TrieNode find(TrieNode n, String s) {
		if (s.length() == 0) {
			return n;
		} else {

			TrieNode childNode;
			for (MapToNode<Character> childMap : n.getChildrenMaps()) {
				Character c = (Character) childMap.key;
				childNode = childMap.node;
				if (c.equals(s.charAt(0)))

					return find(childNode, s.substring(1, s.length()));

			}
			return null;

		}
	}

	public void computeTotalWords() {
		this.numWords = totalWords();
	}

	public int totalWords() {
		return totalWords(root);
	}

	private int totalWords(TrieNode n) {
		int count = 0;
		if (n.getCount() != 0) {
			return n.getCount();
		} else {
			TrieNode childNode;

			for (MapToNode<Character> childMap : n.getChildrenMaps()) {

				childNode = childMap.node;

				count += totalWords(childNode);

			}

			return count;
		}
	}

	//
	// // Given a node n, return the String corresponding to the path from
	// // n to the root node. Be careful that Strings are not returned in
	// // reverse order!
	private String pathToRoot(TrieNode n) {
		if (n.equals(root)) {
			return "";
		} else {

			return pathToRoot(n.getParentMap().node) + n.getParentMap().key.toString();

		}

	}

	// This method initializes an empty priority queue and passes it
	// to the recursive method to build a priority queue of words
	// that match the prefix String s.
	public LinkedPriorityQueue<String> completedWords(String s) {
		TrieNode n = find(s); // Find the subtree corresponding to the prefix
		LinkedPriorityQueue<String> words = new LinkedPriorityQueue<String>(); // Initialize
																				// an
																				// empty
																				// priority
																				// queue
		return completedWords(n, words);
	}

	// Given the prefix s, collect all matching vocabulary words and their
	// frequencies.
	// Note that nodes that correspond to the last character in a WORD will have
	// a count
	// greater than 0. Hint: we want words with HIGH FREQUENCIES to be placed at
	// the FRONT
	// of the priority queue - so you will need to make sure you enqueue with
	// priority values
	// that are INVERSELY PROPORTIONAL to the word frequencies.
	private LinkedPriorityQueue<String> completedWords(TrieNode n, LinkedPriorityQueue<String> words) {
		if (n.getCount() != 0) {
			String s = pathToRoot(n);
			words.enqueue(s, (double) 1 / n.getCount());
		}
		for (MapToNode<Character> childMap : n.getChildrenMaps()) {
			TrieNode childNode = childMap.node;
			completedWords(childNode, words);

		}
		return words;
	}
}
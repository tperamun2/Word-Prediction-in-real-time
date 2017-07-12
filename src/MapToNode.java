/**
 * Created by ethan on 2016-11-17.
 * This class represents a Map from a single key of type T to a TrieNode.
 * The purpose of this class is to build a general n-ary tree where the children
 * will be indexed by some key of type T.
 *
 * e.g. A single TrieNode may have a collection of MapToNode objects to represent
 *      links to children nodes that can be indexed by Characters.
 */
class MapToNode<T>{
    T key;
    TrieNode node;

    public MapToNode(T key, TrieNode node) {
        this.key = key;
        this.node = node;
    }

    @Override
    public boolean equals(Object other){
        return this.key.equals(((MapToNode)other).key);
    }
}
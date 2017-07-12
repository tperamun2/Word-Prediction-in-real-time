import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethan on 2016-11-15.
 * This class represents a Node in a Trie.
 * Children are represented indirectly using a MapToNode object.
 * This allows children to be 'indexed' by an object of type T.
 * e.g. In a StringTrie, these indices will be individual Characters.
 * Notice that there is a useful link to the parent node!
 */

public class TrieNode {

    private int count;
    private MapToNode<Character> parentMap;
    private List<MapToNode<Character>> childrenMaps;

    public TrieNode(){
        this.count = 0;
        this.parentMap = null;
        this.childrenMaps = new ArrayList<>();
    }

    public TrieNode(MapToNode<Character> parentMap){
        this.count = 0;
        this.parentMap = parentMap;
        this.childrenMaps = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public MapToNode<Character> getParentMap(){
        return parentMap;
    }

    public void incrementCount(){
        count++;
    }

    public List<MapToNode<Character>> getChildrenMaps() {
        return childrenMaps;
    }

    public void addChild(Character c){
        MapToNode child = new MapToNode(c, new TrieNode(new MapToNode<>(c, this)));
        if(!childrenMaps.contains(child)){
            childrenMaps.add(child);
        }
    }

    public TrieNode getChild(Character c){
        for(MapToNode child : childrenMaps){
            if(child.key.equals(c)){
                return child.node;
            }
        }
        return null;
    }

    public String toString(){
        String r = "";
        for(MapToNode childMap : childrenMaps){
            r += childMap.key + " ";
        }
        return r;
    }
}

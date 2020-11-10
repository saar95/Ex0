package ex0;

import java.util.Collection;
import java.util.HashMap;


public class NodeData implements node_data {
    private int key;
    private String value;
    private int tag;
    private HashMap<Integer, node_data> map;
    private static int keyCount=0;

    public NodeData(){
        this.key= keyCount;
        this.value="";
        this.tag=0;
        this.map= new HashMap<Integer,node_data>();
        keyCount++;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Collection<node_data> getNi() {
        return this.map.values();
    }

    @Override
    public boolean hasNi(int key) {
        if(this.map.containsKey(key))
            return true;
        return false;
    }
    @Override
    public void addNi(node_data t) {
        if(t!=null)
        this.map.put(t.getKey(),t);
    }

    @Override
    public void removeNode(node_data node) {
       // if(this.map.containsKey(node.getKey()))
        this.map.remove(node.getKey());



    }

    @Override
    public String getInfo() {
        return this.value;
    }

    @Override
    public void setInfo(String s) {
    this.value=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
    this.tag=t;
    }


}

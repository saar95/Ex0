package ex0;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class Graph_DS implements graph {

    private HashMap<Integer, node_data> map;
    private int edgeCount;
    private int modeCount;

    public Graph_DS(){
        this.map=new HashMap<Integer,node_data>();
        this.edgeCount=0;
        this.modeCount=0;
    }

    public Graph_DS(HashMap<Integer, node_data> map){
        this.map=map;
        this.edgeCount=0;
        modeCount=0;
    }
    public Graph_DS(HashMap<Integer, node_data> map,int edgeCount,int modeCount){
        this.map=map;
        this.edgeCount=edgeCount;
        this.modeCount=modeCount;
    }

    @Override
    public node_data getNode(int key) {
        if(map.containsKey(key))
            return map.get(key);

        return null;
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if(getNode(node1).hasNi(node2)&&getNode(node2).hasNi(node1))
            return true;
        return false;
    }

    @Override
    public void connect(int node1, int node2) {
        if(node1!=node2 && !this.hasEdge(node1,node2)) {
            if(map.containsKey(node1)&&map.containsKey(node2)) {
                getNode(node1).addNi(getNode(node2));
                getNode(node2).addNi(getNode(node1));
                this.edgeCount++;
                this.modeCount++;
            }
        }
    }

    @Override
    public Collection<node_data> getV() {
        return this.map.values();

    }

    @Override
    public Collection<node_data> getV(int node_id) {
        if(this.map.containsKey(node_id))
      return  getNode(node_id).getNi();
        return null;
    }

    /**
     * Deleting the given node_id from the graph, deleting all his neighbors, deleting node_id ass neighbor from
     * all the nodes in the graph (all his edges)
     * @param key
     * @return node_data
     */
    @Override
    public node_data removeNode(int key) {
        if (!map.containsKey(key))
            return null;
        Iterator<node_data> ni = getNode(key).getNi().iterator();
        while(ni.hasNext()){
            ni.next().removeNode(getNode(key));
            this.edgeCount--;
        }
        getNode(key).getNi().clear();
        map.remove(key);
        this.modeCount++;
        return getNode(key);
        }


    @Override
    public void removeEdge(int node1, int node2) {
        if(getNode(node1)!=null&&getNode(node2)!=null){
        if(hasEdge(node1,node2)) {
            getNode(node1).removeNode(getNode(node2));
            getNode(node2).removeNode(getNode(node1));
            this.edgeCount--;
            this.modeCount++;
        }
        }
    }


    @Override
    public int nodeSize() {
        return map.size();
    }

    @Override
    public int edgeSize() {
        return edgeCount;
    }

    @Override
    public int getMC() {
        return modeCount;
    }

    @Override
    public void addNode(node_data n) {
    map.put(n.getKey(),n);
        this.modeCount++;
    }
}




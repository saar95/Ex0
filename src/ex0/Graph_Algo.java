package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph ga;

    public Graph_Algo() {
        this.ga =new Graph_DS();
    }

    @Override
    public void init(graph g) {
        this.ga = g;
    }

    /**
     * create a deep copy of this graph
     * @return
     */
    @Override
    public graph copy() {
        if(ga!=null) {
            graph a = new Graph_DS();
            Iterator<node_data> it = this.ga.getV().iterator();
            node_data temp = null;
            node_data temp2 = null;
            node_data temp3 = null;
            while (it.hasNext()) {
                temp = it.next();
                a.addNode(temp);
            }
            Iterator<node_data> it2 = this.ga.getV().iterator();
            while (it2.hasNext()) {
                temp2 = it2.next();
                if (temp.getNi() != null) {
                    Iterator<node_data> it3 = temp2.getNi().iterator();
                    while (it3.hasNext()) {
                        temp3 = it3.next();
                        a.connect(temp3.getKey(), temp2.getKey());
                    }
                }
            }
            return a;
        }
        return null;
    }

    /**
     * return true if there is a valid path between every node in the graph
     * else return false
     * @return true if connected else false
     */
    @Override
    public boolean isConnected() {
        Queue<node_data> q = new LinkedList<node_data>();
        boolean ans=true;
        node_data temp = null;
        node_data temp2 = null;
        node_data temp3 = null;
        int nodes = this.ga.nodeSize();
        if(nodes==0)
                return true;
        if (nodes==1)
            return true;
        Iterator<node_data> it = ga.getV().iterator();
        temp=it.next();
        q.add(temp);
        temp.setTag(1);
        while(!q.isEmpty()){
            temp2=q.poll();
            Iterator<node_data> it1 = temp2.getNi().iterator();
            while(it1.hasNext()){
                temp2=it1.next();
                if(temp2.getTag()==0){
                    q.add(temp2);
                    temp2.setTag(1);
                }
            }
        }
        while (it.hasNext()){
            temp3=it.next();
            if(temp3.getTag()==0)
                ans=false;
        }
        resInfo(ga);
    return ans;
    }

    /**
     * return the length of the shortest path in the graph between src and dest
     * if there is no path return -1
     * @param src - start node
     * @param dest - end (target) node
     * @return int-length of the shortest path
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        Queue<node_data> q = new LinkedList<node_data>();
        Iterator<node_data> it = this.ga.getV().iterator();
        if(src==dest)
            return 0;
        BFS(ga, ga.getNode(src), ga.getNode(dest),q);
        if(ga.getNode(dest).getTag()==0)
            return -1;
        int a=ga.getNode(dest).getTag();
        resInfo(ga);
        return a;
    }

    private void BFS(graph g, node_data src, node_data dest, Queue<node_data> q) {
        Iterator<node_data> ni = src.getNi().iterator();
        node_data temp = null;
        if (src.getInfo() != "1") {
            q.add(src);
            src.setInfo("1");
        }
        while (!q.isEmpty()&&temp!=dest) {
            while (ni.hasNext()) {
                temp = ni.next();
                if (temp == dest) {
                    temp.setTag(q.peek().getTag() + 1);
                    temp.setInfo("1");
                }
                if (temp.getInfo() != "1") {
                    q.add(temp);
                    temp.setTag(q.peek().getTag() + 1);
                    temp.setInfo("1");
                }
            }
            if(temp!=dest) {
                q.remove();
                if (q.peek() != null)
                    BFS(g, q.peek(), dest, q);
            }
        }
    }


    /**
     * return the shortest path between src to dest in ordered List
     * @param src - start node
     * @param dest - end (target) node
     * @return List<node_data>
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        List<node_data> l = new ArrayList<node_data>();
        if (src == dest) {
            l.add(ga.getNode(src));
            return l;
        }
        Queue<node_data> q = new LinkedList<node_data>();
        Iterator<node_data> it = this.ga.getV().iterator();
        if(src==dest)
            return null;
        BFS(ga, ga.getNode(src), ga.getNode(dest),q);
        if(ga.getNode(dest).getTag()==0)
            return null;
        BFS(ga.getNode(src), ga.getNode(dest),l);
        Stack<node_data> s = new Stack<node_data>();
        for(int i=0;i<l.size();i=i){
            s.push(l.remove(i));
        }
        while (!s.isEmpty()){
            l.add(s.peek());
            s.pop();
        }
        resInfo(ga);
        return l;
    }
    private int BFS(node_data src,node_data dest,List<node_data> l){
        int result=1;
        Iterator<node_data> it = dest.getNi().iterator();
        if (!l.contains(dest))
            l.add(dest);
        if (dest.getTag()==0)
            return 0;
        node_data temp = dest;
        while(dest.getTag()!=0){
            if (temp.getTag() == dest.getTag() - 1) {
                result = BFS(src, temp, l);
                if (result == 0)
                    return 0;
            }
            else if(it.hasNext())
                temp = it.next();
        }
        return 1;
    }

    /**
     * reset the info and the tag for all the nodes in the graph
     * @param ga
     */
    private void resInfo(graph ga){
       Iterator <node_data> it = ga.getV().iterator();
       node_data temp =null;
       while (it.hasNext()){
           temp=it.next();
           temp.setTag(0);
           temp.setInfo("");
       }
    }
}


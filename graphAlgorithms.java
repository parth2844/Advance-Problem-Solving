import java.util.*;

public class graphAlgorithms {
    static int V;
    static Map<String,Integer> vertices;
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        int[] heuristics= getHeuristics(sc);
        int[][] graph = getGraph(sc);
        //Get start Node
        System.out.println("Enter Start Node");
        String start= sc.next();
        int s=vertices.get(start);
        //Get End Node
        System.out.println("Enter End Node");
        String end= sc.next();
        int e=vertices.get(end);
        //Get search Type
        System.out.println("Enter Search Type");
        String search=sc.next();
        findPath(graph,heuristics,s,e,search);

    }

    private static int[] getHeuristics(Scanner sc) {
        //System.out.println("Enter number of vertices in the Graph");
        V= sc.nextInt();
        vertices= new LinkedHashMap<>();
        int[] heuristic= new int[V];
        for(int i=0;i<V;i++){
            String name= sc.next();
            vertices.put(name,i);
            heuristic[i]=sc.nextInt();
        }
        return heuristic;
    }

    private static int[][] getGraph(Scanner sc) {
        int[][] graph= new int[V][V];
        //System.out.println("Enter number of edges");
        int edges= sc.nextInt();
        for(int i=0;i<edges;i++){
            String n1= sc.next();
            String n2= sc.next();
            int weight= sc.nextInt();
            graph[vertices.get(n1)][vertices.get(n2)]= weight;
            graph[vertices.get(n2)][vertices.get(n1)]= weight;
        }
        return graph;
    }

    private static void findPath(int[][] graph,int[] heuristics, int s, int e, String search) {
        switch (search){
            case "Greedy":
                getGreedyPath(graph,heuristics,s,e);
                break;
            case "Dijkstra":
                getDijkstraPath(graph,s,e);
                break;
            case "A*":
                getAPath(graph,heuristics,s,e);
                break;
            default:
                System.out.println("Invalid search type");
        }
    }

    private static void getGreedyPath(int[][] graph, int[] heuristics, int s, int e) {
        PriorityQueue<Pair> path= new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.value-o2.value;
            }
        });
        boolean[] visited= new boolean[V];
        int[] parent= new int[V];
        int[] dist= new int[V];
        String expansion_order="";
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[s]=0;
        path.add(new Pair(s,heuristics[s]));
        while(!path.isEmpty()){
            Pair current= path.poll();
            visited[current.node]=true;
            if(current.node==e){
                printOutput(expansion_order, parent, dist[e], e);
                break;
            }
            else{
                for(int v=0;v<V;v++){
                    if (!visited[v] && graph[current.node][v] != 0 && dist[current.node] != Integer.MAX_VALUE && dist[current.node] + graph[current.node][v] < dist[v]) {
                        dist[v] = dist[current.node] + graph[current.node][v];
                        parent[v]= current.node;
                        path.add(new Pair(v,heuristics[v]));
                    }
                }
                expansion_order+=""+current.node+" ";
            }
        }

    }

    private static void getDijkstraPath(int[][] graph, int s, int e) {
        PriorityQueue<Pair> path= new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.value-o2.value;
            }
        });
        boolean[] visited= new boolean[V];
        int[] parent= new int[V];
        int[] dist= new int[V];
        String expansion_order="";
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[s]=0;
        path.add(new Pair(s,dist[s]));
        while(!path.isEmpty()){
            Pair current= path.poll();
            visited[current.node]=true;
            if(current.node==e){
                printOutput(expansion_order, parent, dist[e], e);
                break;
            }
            else{
                for(int v=0;v<V;v++){
                    if (!visited[v] && graph[current.node][v] != 0 && dist[current.node] != Integer.MAX_VALUE && dist[current.node] + graph[current.node][v] < dist[v]) {
                        dist[v] = dist[current.node] + graph[current.node][v];
                        parent[v]= current.node;
                        path.add(new Pair(v,dist[v]));
                    }
                }
                expansion_order+=""+current.node+" ";
            }
        }
    }

    private static void getAPath(int[][] graph, int[] heuristics, int s, int e) {
        PriorityQueue<Pair> path= new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.value-o2.value;
            }
        });
        boolean[] visited= new boolean[V];
        int[] parent= new int[V];
        int[] dist= new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[s]=0;
        path.add(new Pair(s,heuristics[s]+dist[s]));
        String expansion_order="";
        while(!path.isEmpty()){
            Pair current= path.poll();
            visited[current.node]=true;
            if(current.node==e){
                printOutput(expansion_order, parent, dist[e], e);
                break;
            }
            else{
                for(int v=0;v<V;v++){
                    if (!visited[v] && graph[current.node][v] != 0 && dist[current.node] != Integer.MAX_VALUE && dist[current.node] + graph[current.node][v] < dist[v]) {
                        dist[v] = dist[current.node] + graph[current.node][v];
                        parent[v]= current.node;
                        path.add(new Pair(v,heuristics[v]+dist[v]));
                    }
                }
                expansion_order+=""+current.node+" ";
            }
        }
    }

    private static void printOutput(String expansion_order, int[] parent, int cost, int e) {
        int p=e;
        String path_found="";
        String[] names= vertices.keySet().toArray(new String[0]);//Converting Node number to Node Name
        expansion_order+=""+(e); //Adding Goal Node

        //Expansion Order
        String exp[] = expansion_order.split(" ");
        System.out.print("Expansion Order: ");
        for(String i:exp){
            int n= Integer.parseInt(i);
            System.out.print(names[n]+" ");
        }
        System.out.println();

        //Actual path
        while(p!=0){
            path_found=names[parent[p]]+" "+path_found;
            p=parent[p];
        }
        path_found+=""+names[e];//Adding Goal Node
        System.out.println("Path: "+path_found);

        System.out.println("Cost: "+cost);
    }


}
class Pair{
    int node,value;
    Pair(int node, int value){
        this.node=node;
        this.value=value;
    }
}
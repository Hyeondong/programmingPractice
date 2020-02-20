class Cospro5_7 {
    public int find(int[] parent, int u) {
        if(u == parent[u])
            return u;

        parent[u] = find(parent, parent[u]);
        return parent[u];
    }

    public boolean merge(int[] parent, int u, int v) {
        u = find(parent, u);
        v = find(parent, v);

        if(u == v)
            return true;

        parent[u] = v;
        return false;
    }

    public int solution(int n, int[][] connections) {
        int answer = 0;

        int[] parent = new int[n+1];
        for(int i = 1; i <= n; i++)
            parent[i]=i;

        for(int i = 0; i < connections.length; i++) {
        	
            if(merge(parent, connections[i][0], connections[i][1])) {
                answer = i + 1;
                break;
            }
           
        }

        return answer;
    }

    public static void main(String[] args) {
    	Cospro5_7 sol = new Cospro5_7();
        int n = 3;
        int[][] connections = {{1, 2}, {1, 3}, {2, 3}};
        int ret = sol.solution(n, connections);
        
        System.out.println("solution  " + ret);
    }
}
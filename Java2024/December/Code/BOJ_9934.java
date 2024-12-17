package Code;

import java.util.*;

import java.io.*;

class BOJ_9934{
    static List<List<Integer>> nodeLayers;
    static int[] nodes;
    static int nodeCount,h;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9934;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int K=Integer.parseInt(br.readLine());

        nodeCount=(int)Math.pow(2,K)-1; // 노드는 2**k-1개

        nodes=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        h=(int)(Math.log(nodeCount+1)/Math.log(2)); // 포화이진트리 높이

        nodeLayers=new ArrayList<>();

        for(int i=0;i<h;i++){
            nodeLayers.add(new ArrayList<>());
        }
        
        solve(0, nodeCount-1, 0);

        for(int i=0;i<h;i++){
            for(int node:nodeLayers.get(i)){
                System.out.print(node+" ");
            }
            System.out.println();
        }
        br.close();
    }
    static void solve(int start, int end, int depth){
        if(start>end){
            return;
        }

        int middle=(start+end)/2;

        nodeLayers.get(depth).add(nodes[middle]);

        solve(start, middle-1, depth+1);
        solve(middle+1, end, depth+1);
    }
}
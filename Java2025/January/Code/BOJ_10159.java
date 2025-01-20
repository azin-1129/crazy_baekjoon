package Code;

import java.util.*;
import java.io.*;

class BOJ_10159{
    static int[] parent;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10159;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());

        parent=new int[N+1];

        for(int i=0;i<N+1;i++){
            parent[i]=i;
        }
        
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int big=Integer.parseInt(st.nextToken());
            int small=Integer.parseInt(st.nextToken());

            union(big, small);
        }

        System.out.println(Arrays.toString(parent));

        // 물건의 개수 N 과 일부 물건 쌍의 비교 결과가 주어졌을 때, 각 물건에 대해서 그 물건과의 비교 결과를 알 수 없는 물건의 개수를 출력
        

        // 1>2>3>4, 5>4, 6>5

        // 1은 6,5와 이어지지 않아서 2개
        // 2는 6,5와 이어지지 않아서 2개
        // 3은 6,5와 이어지지 않아서 2개
        // 4는 모든 것과 이어져 0개
        // 5는 1,2,3과 이어지지 않아 3개. 6,4와 연관
        // 6은 5와 연관, 5는 4와 연관되어 3개

        // 물건 별로 이어지지 못하는 물건의 개수 출력
        br.close();
    }
    public static void union(int a, int b){
        int aParent=find(a);
        int bParent=find(b);

        if(aParent>bParent){
            parent[aParent]=bParent;
        }else{
            parent[bParent]=aParent;
        }
    }

    public static int find(int x){
        if(parent[x]==x){
            return parent[x];
        }else{
            return find(parent[x]);
        }
    }
}
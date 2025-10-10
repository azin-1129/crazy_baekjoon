package Code;

import java.util.*;
import java.io.*;

class BOJ_9375{
    static int N, categoryCount, result;
    static int[] sizes, comb;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9375;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            result=0;
            Map<String, Integer> hm=new HashMap<>();
            int N=Integer.parseInt(br.readLine());
            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine(), " ");
                String item=st.nextToken();
                String category=st.nextToken();

                if(!hm.containsKey(category)){
                    hm.put(category, 1);
                }else{
                    hm.put(category, hm.get(category)+1);
                }
            }

            categoryCount=hm.size();
            sizes=new int[categoryCount];
            int idx=0;
            for(String category:hm.keySet()){
                sizes[idx++]=hm.get(category);
            }

            System.out.println("옷 개수는.. 각각");
            System.out.println(Arrays.toString(sizes));

            for(int i=1;i<=N;i++){
                System.out.println(i+"개의 조합을 추려요.");
                comb=new int[i];
                comb(0, 0, i);
            }

            System.out.println(result);
        }
        br.close();
    }
    static void comb(int start, int depth, int size){
        if(depth==size){
            result+=1;
            System.out.println("조합 완성:"+Arrays.toString(comb));
            return;
        }
        for(int i=start;i<categoryCount;i++){
            comb[depth]=sizes[i];
            comb(i+1, depth+1, size);
        }
    }
}
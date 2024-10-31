import java.util.*;
import java.io.*;

class BOJ_1351{
    static HashMap<Long, Long> hm;
    static long P,Q;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\";
        int bojNum=1351;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        long[] inputs=Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long N=inputs[0];
        P=inputs[1];
        Q=inputs[2];

        hm=new HashMap<>();
        hm.put(0L, 1L);

        System.out.println(recursion(N));

        br.close();
    }
    static long recursion(long subscript){
        if(hm.containsKey(subscript)){
            return hm.get(subscript);
        }

        if(subscript==0L){
            return 1L;
        }

        hm.put(subscript,
        recursion((long)Math.floor(subscript/P))
        +recursion((long)Math.floor(subscript/Q))
        );

        return hm.get(subscript);
    }
}
package Code;
import java.util.*;
import java.io.*;

public class BOJ_2805 {
    static long[] trees;
    static long M, maxH;
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir")+"\\";
        String fileName="input2805.txt";
        BufferedReader br=new BufferedReader(new FileReader(path+fileName));

        String[] inputs=br.readLine().split(" ");
        int N=Integer.parseInt(inputs[0]);
        M=Integer.parseInt(inputs[1]);

        trees=Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Arrays.sort(trees);

        cut(1, trees[trees.length-1]);

        System.out.println(maxH);

        br.close();
    }
    static void cut(long front, long back){
        if(front>back){
            return;
        }

        long middle=(front+back)/2;
        long treeSlice=0;

        for(long treeHeight:trees){
            if(treeHeight>middle){
                treeSlice+=(treeHeight-middle);
            }
        }

        if(treeSlice>=M){
            if(maxH<middle){
                maxH=middle;
                cut(middle+1, back);
            }
        }else {
            cut(front, middle-1);
        }
    }
}

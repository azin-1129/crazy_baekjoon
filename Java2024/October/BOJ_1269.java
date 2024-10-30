import java.util.*;
import java.io.*;

public class BOJ_1269 {
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input1269.txt"));

        Set<Integer> A=new HashSet<>();

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        int lengthA=inputs[0];
        int lengthB=inputs[1];

        int[] numbersA=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] numbersB=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i=0;i<lengthA;i++){
            A.add(numbersA[i]); // A 원소 추가
        }

        // System.out.println(A);

        int duplicateSize=0;
        for(int i=0;i<lengthB;i++){
            if(A.remove(numbersB[i])==true){
                duplicateSize+=1;
            }
        }

        // System.out.println(A);

        int result=(lengthA-duplicateSize)+(lengthB-duplicateSize);
        System.out.println(result);
        
        br.close();
    }
}

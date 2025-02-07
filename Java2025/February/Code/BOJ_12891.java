package Code;

import java.io.*;
import java.util.*;

public class BOJ_12891 {
    static int P;
    static int[] correctCounts, alphabetCounts;
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/Input/input";
        String probNum="12891";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int S=inputs[0];
        P=inputs[1];

        String[] dna=br.readLine().split("");

        correctCounts=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<String, Integer> hm=new HashMap<>();
        hm.put("A", 0);
        hm.put("C", 1);
        hm.put("G", 2);
        hm.put("T", 3);

        // 카운트 초기화
        int[] alphabetCounts=new int[4];
        for(int i=0;i<P;i++){
            alphabetCounts[hm.get(dna[i])]+=1;
        }

        int result=0;
        // 정답 체크
        result+=equalsWithCorrectAnswers(alphabetCounts);

        for(int i=P;i<dna.length;i++){
            alphabetCounts[hm.get(dna[i-P])]-=1;
            alphabetCounts[hm.get(dna[i])]+=1;

            result+=equalsWithCorrectAnswers(alphabetCounts);
        }

        System.out.println(result);
        br.close();
    }    
    static int equalsWithCorrectAnswers(int[] alphabetCounts){
        for(int i=0;i<4;i++){
            if(alphabetCounts[i]<correctCounts[i]){
                return 0;
            }
        }

        return 1;
    }
}

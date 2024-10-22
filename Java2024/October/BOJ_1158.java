package Java2024.October;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class BOJ_1158 {
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir")+"\\Java2024\\October\\";

        BufferedReader br=new BufferedReader(new FileReader(path+"input1158.txt"));

        int N, K;

        String[] inputs=br.readLine().split(" ");
        N=Integer.parseInt(inputs[0]);
        K=Integer.parseInt(inputs[1]);

                // IntStream으로 1~N까지의 정수를 List<Integer>로 초기화
        List<Integer> numbers = IntStream.rangeClosed(1, N)
            .boxed() // 객체 list를 받아야하기 때문에 int를 Integer로 래핑
            .collect(Collectors.toList()); // 스트림 값 수집

        int idx=0;

        System.out.print("<");
        for(int i=N;i>0;i--){
            idx=(idx+(K-1))%i;
            System.out.print(numbers.get(idx));

            numbers.remove(idx);

            if(i!=1){
                System.out.print(", ");
            }else{
                System.out.print(">");
            }
        }

        br.close();
    }
}

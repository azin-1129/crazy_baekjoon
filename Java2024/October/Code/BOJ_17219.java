package Code;
import java.util.*;
import java.io.*;

public class BOJ_17219 {
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input17219.txt"));
        String[] inputs;

        inputs=br.readLine().split(" ");

        int N=Integer.parseInt(inputs[0]); // 저장된 사이트 주소 수
        int M=Integer.parseInt(inputs[1]); // 찾고자 하는 사이트 수

        HashMap<String, String> hm=new HashMap<>(N, 0.8f);

        for(int n=0;n<N;n++){
            inputs=br.readLine().split(" ");

            hm.put(inputs[0], inputs[1]);
        }

        String input;

        for(int m=0;m<M;m++){
            input=br.readLine();

            System.out.println(hm.get(input));
        }

        br.close();
    }
}

package Code;

import java.io.*;
import java.util.*;

public class Softeer_X_marks_the_Spot {
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="X_marks_the_Spot";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));
        StringBuilder sb=new StringBuilder();

        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String[] inputs=br.readLine().split(" ");

            String S=inputs[0];
            String T=inputs[1];

            int Px=S.indexOf("x");
            int PX=S.indexOf("X");
            int P;
            if(Px>=0){
                P=Px;
            }else{
                P=PX;
            }

            sb.append(Character.toUpperCase(T.charAt(P)));
        }

        System.out.println(sb);

        br.close();
    }
    
}

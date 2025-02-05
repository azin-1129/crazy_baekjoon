package Code;

import java.io.*;

public class Softeer_8단_변속기 {
    static final String ascending="12345678";
    static final String descending="87654321";
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="8단_변속기";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt")); 

        String[] input=br.readLine().split(" ");
        String stat=String.join("", input);

        if(stat.equals(ascending)){
            System.out.println("ascending");
        }
        else if(stat.equals(descending)){
            System.out.println("descending");
        }else{
            System.out.println("mixed");
        }

        br.close();
    }
}
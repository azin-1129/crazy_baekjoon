import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 시험감독13458 {
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/2월/";

        BufferedReader br=new BufferedReader(new FileReader("input13458.txt"));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int A=Integer.parseInt(br.readLine());

        int B;
        int C;

        st=new StringTokenizer(br.readLine());
        B=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        

    }
}

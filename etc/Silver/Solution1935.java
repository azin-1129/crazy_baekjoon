import java.io.*;
import java.util.*;
import javax.script.*;

public class Solution1935 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N=Integer.parseInt(br.readLine());
		String[] stklne=br.readLine().split("");
		Stack<String> stack=new Stack<>();
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("JavaScript");
		List<String> chk_lst= Arrays.asList("+","-","/","*");
		
		for(String stk:stklne) {
			if (chk_lst.contains(stk)){
				String y=stack.pop();
				String x=stack.pop();
				stack.add((String)engine.eval(x+stk+y));
			}else {
				stack.add(stk);
			}
		}
		
		System.out.printf("%.2f", stack.pop());

	}

}

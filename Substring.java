import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Substring {
    private static void getinput(List<String> input) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the number of Strings");
        int n=Integer.parseInt(sc.nextLine());
        System.out.println("Enter the Strings");
        for(int i=1;i<=n;i++)
            input.add(sc.nextLine());
    }

    private static void getSubstring(int index, List<String> input) {
        String key=input.get(index);
        List<Integer> sub= new ArrayList<>();
        for(int i=0;i<input.size();i++){
            if(i!=index && (input.get(i).length()>=key.length())){
                if(containsKey(input.get(i),key)){
                    sub.add(i);
                }
            }
        }
        print(key,sub,input);
    }

    private static boolean containsKey(String s, String key) {
        int len=s.length()-key.length();
        for(int i=0;i<=len;i++)
            if(s.charAt(i)==key.charAt(0)){
                int j;
                for(j=1;j<key.length();j++)
                    if(s.charAt(i+j)!=key.charAt(j))
                        break;
                if(j==key.length()) return true;
            }
        return false;
    }

    private static void print(String key, List<Integer> sub, List<String> input) {
        System.out.print(key+":");
        for(int loc:sub)
            System.out.print(input.get(loc)+"   ");
        System.out.println();
    }

    public static void main(String args[]){
        List<String> input= new ArrayList<>();
        getinput(input);
        for(int i=0;i<input.size();i++)
            getSubstring(i,input);
    }
}

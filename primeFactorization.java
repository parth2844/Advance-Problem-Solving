import java.util.*;

public class primeFactorization {
    public static void main(String args[]){
        Map<Integer,Integer> factors;
        List<Integer> input= getInput();
        for(int n:input){
            factors= getFactors(n, new LinkedHashMap<Integer, Integer>());
            if(n>1)
                factors.remove(1);
            printFactors(factors);
        }
    }

    private static void printFactors(Map<Integer, Integer> factors) {
        System.out.println();
        for(int key:factors.keySet()){
            System.out.print("("+key+")"+"^"+factors.get(key)+" ");
        }
    }

    private static List<Integer> getInput() {
        List<Integer> input= new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Input");
        while(true){
            String in= sc.next();
            if(in.equals("end"))
                break;
            input.add(Integer.parseInt(in));
        }
        return input;
    }

    public static Map getFactors(int n, LinkedHashMap<Integer,Integer> factors){
        if(n==0 || n==1){
            factors.put(n,1);
            return factors;
        }
        int i=2;
        while(n%i!=0)
            i++;
        factors.putIfAbsent(i,0);
        factors.replace(i,factors.get(i)+1);
        return getFactors(n/i,factors);
    }
}

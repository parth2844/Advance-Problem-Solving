import java.util.*;
public class empiricalFormula {
    public static void main (String[] args){
        String[] input= getInput();
        for(String in:input){
            Map<String,Integer> map=countElements(in);
            int[] arr= getValues(map.values());
            int gcd= getGCD(arr);
            map= getModifiedMap(map,gcd);
            print(map);
        }
    }

    private static int[] getValues(Collection<Integer> values) {
        int[] arr= new int[values.size()];
        int j=0;
        for(int i:values)
            arr[j++]=i;
        return arr;
    }

    private static int getGCD(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++){
            result = gcd(arr[i], result);
            if(result == 1) {
                return 1;
            }
        }
        return result;
    }

    private static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    private static Map<String, Integer> getModifiedMap(Map<String, Integer> map, int gcd) {
        for(String i:map.keySet()){
            map.replace(i,map.get(i)/gcd);
        }
        return map;
    }

    private static void print(Map<String, Integer> map) {
        System.out.println();
        for(String i:map.keySet()){
            if(map.get(i)==1)
                System.out.print(i);
            else
                System.out.print(i+""+map.get(i));
        }
    }

    private static String[] getInput() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter number of formulas");
        int n= sc.nextInt();
        String[] input= new String[n];
        for(int i=0;i<n;i++){
            input[i]=sc.next();
        }
        return input;
    }

    private static boolean isLowerCase(char c){
        return  c >= 'a' && c <= 'z';
    }

    public static Map countElements(String formula){
        HashMap<String, Integer> elements = new HashMap<String, Integer>();
        Stack<Integer> mults = new Stack<Integer>();

        StringBuilder element = new StringBuilder("");
        StringBuilder digits = new StringBuilder("");

        char currChar;
        int num = 1;
        int mult = 1;

        for (int i = formula.length() - 1; i >= 0; i--){
            currChar = formula.charAt(i);

            if (Character.isDigit(currChar)) {
                digits.append(currChar);
            }
            else if (isLowerCase(currChar)){
                element.append(currChar);
            }
            else{
                if (digits.length() == 0){
                    num = 1;
                }
                else{
                    num = Integer.parseInt(digits.reverse().toString());
                }

                if (currChar == '(') {
                    mult /= mults.pop();
                }
                else if (currChar == ')') {
                    mults.push(num);
                    mult *= num;
                    digits.delete(0, digits.length());
                }
                else {
                    element.append(currChar).reverse();
                    elements.put(element.toString(), elements.getOrDefault(element.toString(), 0) + num * mult);
                    digits.delete(0, digits.length());
                    element.delete(0, element.length());
                }
            }
        }
        return elements;
    }
}

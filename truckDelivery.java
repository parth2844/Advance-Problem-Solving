import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class truckDelivery {
    static int n;
    static int[] time, position,value;
    public static void main(String args[]){
        getInput();
        getRevenue();
    }

    private static void getRevenue() {
        int[] dp = new int[n+1];
        int[] parent = new int[n+1];
        int maxRevenue = 0;
        int maxIndex = 0;
        List<Integer> optimalDeliveries = new ArrayList<>();
        for(int i = 1; i<= n; i++) {
            for(int j = 0; j<i; j++) {
                int timeReq = Math.abs(position[i]-position[j])/100;
                int timePermitted = time[i] - time[j];
                int tempRevenue = timeReq<=timePermitted?dp[j] + value[i]: 0;
                if(dp[i]<= tempRevenue) {
                    dp[i] = tempRevenue;
                    parent[i] = j;
                }
            }
            if(maxRevenue <= dp[i]) {
                maxRevenue = dp[i];
                maxIndex = i;
            }
        }
        while(maxIndex >0) {
            optimalDeliveries.add(maxIndex);
            maxIndex = parent[maxIndex];
        }
        Collections.reverse(optimalDeliveries);
        printOutput(maxRevenue, optimalDeliveries);
    }

    private static void printOutput(int maxRevenue, List<Integer> optimalDeliveries) {
        System.out.print("Optimal Deliveries = ");
        for(int i:optimalDeliveries)
            System.out.print(i+" ");
        System.out.println();
        System.out.print("Total Revenue = ");
        System.out.println(maxRevenue);
    }

    private static void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n:");
        n=sc.nextInt();
        time=new int[n+1];
        position=new int[n+1];
        value=new int[n+1];
        System.out.println("Enter time:");
        for(int i=1;i<=n;i++)
            time[i]=sc.nextInt();
        System.out.println("Enter position:");
        for(int i=1;i<=n;i++)
            position[i]=sc.nextInt();
        System.out.println("Enter value:");
        for(int i=1;i<=n;i++)
            value[i]=sc.nextInt();
    }
}

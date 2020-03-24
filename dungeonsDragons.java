import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class dungeonsDragons {
    static int current_sum;
    static float total_perms=1;
    static int[] rem_dice= new int[5];
    static int[] bounds= new int[2];
    static int[] cons_rew=new int[2];

    public static void main(String args[]){
        Scanner sc =new Scanner(System.in);
        getInput(sc);
        Queue<Integer> dices= getDices();
        int[][] dp=calculatePerms(dices,bounds[1]-current_sum);
        float[] prob = calculateProbabilities(dp[dp.length-1]);
        printSuccess(prob[2]);
        printReward(prob);
    }


    private static void printReward(float[] prob) {
        float reward= prob[0]*cons_rew[0] + prob[2]*cons_rew[1];
        System.out.println("Expected Reward = "+reward);
    }

    private static void printSuccess(float p_success) {
        System.out.println("Probability of Success= "+p_success*100 +"%");
    }

    private static float[] calculateProbabilities(int[] counts) {
        float[] prob= new float[3];
        int fail = getFailure(counts);
        int draw = getDraw(counts);
        int success= (int)total_perms-fail-draw;
        prob[0]=fail/total_perms;
        prob[1]=draw/total_perms;
        prob[2]=success/total_perms;
        return prob;
    }

    private static int getDraw(int[] counts) {
        int draw=0;
        for(int i=bounds[0]-current_sum;i<=bounds[1]-current_sum;i++)
            if(i<0)
                return 0;
            else
                draw+=counts[i];
        return draw;
    }

    private static int getFailure(int[] counts) {
        int failure=0;
        for(int i=1;i<bounds[0]-current_sum;i++)
            failure+=counts[i];
        return failure;
    }


    private static int[][] calculatePerms(Queue<Integer> dices, int val) {
        int[][] dp = new int[dices.size()+1][val+1];
        dp[0][0]=1;
        for(int i=1;i<dp.length;i++){
            int sides=dices.poll();
            total_perms*=sides;
            for(int j=1;j<dp[0].length;j++){
                dp[i][j]=getSum(j,sides,dp[i-1]);
            }
        }
        return dp;
    }

    private static int getSum(int curr_sum, int sides, int[] prev) {
        int total=0;
        for(int i=1;i<=sides;i++){
            if(curr_sum-i>=0)
                total+=prev[curr_sum-i];
        }
        return total;
    }

    private static Queue<Integer> getDices() {
        Queue<Integer> dices= new LinkedList<>();
        int sides=4;
        for(int i=0;i<rem_dice.length;i++){
            for(int j=1;j<=rem_dice[i];j++)
                dices.add(sides);
            sides+=2;
        }
        return dices;
    }

    private static void getInput(Scanner sc) {
        current_sum=sc.nextInt();
        for(int i=0;i<rem_dice.length;i++)
            rem_dice[i]=sc.nextInt();
        for(int i=0;i<bounds.length;i++)
            bounds[i]=sc.nextInt();
        for(int i=0;i<cons_rew.length;i++)
            cons_rew[i]=sc.nextInt();
    }

}

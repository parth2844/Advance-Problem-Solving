import java.util.*;

public class tiling {
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        int w= sc.nextInt();
        int h= sc.nextInt();
        ArrayList<ArrayList<Integer>> board= new ArrayList<>();
        for(int i=0;i<h;i++){
            ArrayList<Integer> row= new ArrayList<>();
            for(int j=0;j<w;j++)
                row.add(-1);
            board.add(row);
        }
        List<List<List<Integer>>> solution= getSolution(0,0,0, board);
        System.out.println("Total number of boards: "+solution.size());
        printSolution(solution);
    }

    private static void printSolution(List<List<List<Integer>>> solution) {
        int max_sol=10,n=1;
        for(List<List<Integer>> ans: solution){
            if(max_sol<=0)
                break;
            System.out.println("Board "+n++);
            printBoard(ans);
            System.out.println();
            max_sol--;
        }
    }

    private static void printBoard(List<List<Integer>> ans) {
        for(int i =0; i< ans.size();i++){
            for(int j =0; j< ans.get(0).size();j++){
                System.out.print(ans.get(i).get(j)+"  ");
            }
            System.out.println();
        }
    }

    public static List<List<List<Integer>>> getSolution(int i, int j, int count, ArrayList<ArrayList<Integer>> board){
        int height = board.size();
        int width = board.get(0).size();
        List<List<List<Integer>>> ans = new ArrayList<>();
        if(i==width-1 && j== height-1){
            if(board.get(j).get(i) != -1){
                ans.add(insertBoard(board));
            }
            return ans;
        }

        if(board.get(j).get(i)!=-1){
            pair nextParams = getPermutation(i, j, width, height);
            return getSolution(nextParams.first, nextParams.second, count, board);
        }

        if(j+1<height && board.get(j+1).get(i) == -1){

            ArrayList<Integer> temp = board.get(j);
            temp.set(i, count);
            board.set(j, temp);

            temp = board.get(j+1);
            temp.set(i, count);
            board.set(j+1, temp);

            count++;
            pair nextParams = getPermutation(i, j, width, height);
            List<List<List<Integer>>> solution = getSolution(nextParams.first, nextParams.second, count, board) ;
            ans.addAll(solution);
            count--;

            temp = board.get(j);
            temp.set(i, -1);
            board.set(j, temp);

            temp = board.get(j+1);
            temp.set(i, -1);
            board.set(j+1, temp);
        }

        if(i+1<width && board.get(j).get(i+1) == -1){
            ArrayList<Integer> temp = board.get(j);
            temp.set(i, count);
            board.set(j, temp);

            ArrayList<Integer> temp1 = board.get(j);
            temp1.set(i+1, count);
            board.set(j, temp1);

            count++;
            pair nextParams = getPermutation(i, j, width, height);
            List<List<List<Integer>>> solution = getSolution(nextParams.first, nextParams.second, count, board);
            ans.addAll(solution);
            count--;

            ArrayList<Integer> temp11 = board.get(j);
            temp11.set(i, -1);
            board.set(j, temp11);

            ArrayList<Integer> temp22 = board.get(j);
            temp22.set(i+1, -1);
            board.set(j, temp22);
        }

        return ans;
    }

    public static pair getPermutation(int i , int j, int width, int height){
        if(i+1<width){
            return new pair(i+1,j);
        }
        return new pair(0,j+1);
    }

    public static List<List<Integer>> insertBoard(ArrayList<ArrayList<Integer>> board){
        List<List<Integer>> copy= new ArrayList<>();
        for(int i=0;i<board.size();i++){
            List<Integer> row= new ArrayList<>();
            for(int j=0;j<board.get(0).size();j++)
                row.add(board.get(i).get(j));
            copy.add(row);
        }
        return copy;
    }

}
class pair{
    int first,second;
    pair(int first, int second){
        this.first=first;
        this.second=second;
    }
}
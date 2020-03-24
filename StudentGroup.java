import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StudentGroup {
    public static void main(String args[]) throws FileNotFoundException {
        File file = new File("edgecase.txt");
        Scanner sc = new Scanner(file);
        Scanner in= new Scanner(System.in);
        System.out.println("Enter number of students in each group");
        int n=in.nextInt();
        Set<String> absent= new HashSet<>();
        System.out.println("Enter number of absent students");
        int ab=in.nextInt();
        if(ab>0){
            System.out.println("Enter absent student id");
            for(int x=0;x<=ab;x++){
                absent.add(in.nextLine());
            }
        }
        int i=1;
        Map<String,String> map= new LinkedHashMap<>();
        while (sc.hasNextLine()){
            String s= sc.nextLine();
            if(i>20){
                if(s.length()==0)
                    break;
                String k= s.substring(0,8);
                String v="";
                int j=9;
                while(s.charAt(j)!=','){
                    v+=s.charAt(j++);
                }
                if(!absent.contains(k))
                    map.put(k,v);
            }
            i++;
        }

        List<String> student= new ArrayList<>(map.keySet());
        boolean[] select= new boolean[student.size()];
        Arrays.fill(select,false);
        Random rand = new Random();
        i= rand.nextInt(student.size());
        int rem = student.size()%n;
        int total_groups= student.size()/n;
        int group=1;
        while(total_groups>0){
            int s=n;
            if(rem>0){
                s++;
                rem--;
            }
            System.out.println("Group "+group++);
            for(int x=0;x<s;x++){
                System.out.println(student.get(i)+"-"+map.get(student.get(i)));
                select[i]=true;
                i=rand.nextInt(student.size());
                while(select[i]==true)
                    i=rand.nextInt(student.size());
            }
            total_groups--;
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
        System.out.println("Agent Management System");
        int counter=0;
        int i = 0 ;
        while (true){

            Scanner in = new Scanner(System.in);
            System.out.println("Agent username daxil edin ");
            String username=in.nextLine();
            System.out.println("Agent parolu daxil edin ");
            String password=in.nextLine();

            Map usersMap = new HashMap();
            Map productMap = new HashMap();
            List<String> productList = new ArrayList<String>() ;
            usersMap.put("Agent1","A");
            String userLine;
            String product;
                BufferedReader bw = new BufferedReader(new FileReader("C:/Agent/Users.txt"));
                while ((userLine= bw.readLine())!=null) {
                    usersMap.put(userLine.substring(0,userLine.indexOf("&")),userLine.substring(userLine.indexOf("&")+1) );
                }
                if (usersMap.containsKey(username) && usersMap.get(username).equals(password)) {
                    System.out.println(username+" sistemə daxil oldu");
                    System.out.println("Icra etmek istediyiniz emeliyyatin nomresini daxil edin ");
                    System.out.println("1- Məhsullara baxış");
                    System.out.println("2- Diger Agentlerin mehsullari");
                    String inputNum= in.nextLine();
                    if (inputNum.equals("1")) {
                        BufferedReader bw2 = new BufferedReader(new FileReader("C:/Agent/"+username+"product.txt"));
                        while ((product= bw2.readLine())!=null) {
                            productList = Arrays.asList(product.split("&"));
                            productMap.put(i,productList);
                            System.out.printf(productMap.get(i).toString());
                            i++;
                        }

                    }

                }  else
                    System.err.println("Istifadəçi adı və ya şifrə yanlışdır");

            counter++;

        }


    }catch (Exception e) {
        e.printStackTrace();
        System.err.println("Xeta bash verdi...");
    }
    }
}

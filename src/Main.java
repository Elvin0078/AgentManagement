import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Agent Management System");
            int counter = 0;
            int i = 0;
            String inputNum = "";
            String agentProductNum ="";
            while (true) {

                Scanner in = new Scanner(System.in);
                System.out.println("Agent username daxil edin ");
                String username = in.nextLine();
                System.out.println("Agent parolu daxil edin ");
                String password = in.nextLine();
                inputNum="";
                Map usersMap = new HashMap();
                Map productMap = new HashMap();
                List<String> productList = new ArrayList<String>();
                usersMap.put("Agent1", "A");
                String userLine;
                String product;
                BufferedReader bw = new BufferedReader(new FileReader("C:/MyAgent/Agent/Users.txt"));
                while ((userLine = bw.readLine()) != null) {
                    usersMap.put(userLine.substring(0, userLine.indexOf("&")), userLine.substring(userLine.indexOf("&") + 1));
                }
                if (usersMap.containsKey(username) && usersMap.get(username).equals(password)) {
                    System.out.println(username + " sistemə daxil oldu");
                    while (!inputNum.equals("0")) {
                        System.out.println("Icra etmek istediyiniz emeliyyatin nomresini daxil edin ");
                        System.out.println("0- Sistemdən çıxış");
                        System.out.println("1- Məhsullara baxış");
                        System.out.println("2- Diger Agentlerin mehsullarina baxış");
                        inputNum = in.nextLine();
                        if (inputNum.equals("1")) {
                            BufferedReader bw2 = new BufferedReader(new FileReader("C:/MyAgent/Agent/" + username + "product.txt"));
                            while ((product = bw2.readLine()) != null) {
                                productList = Arrays.asList(product.split("&"));
                                productMap.put(i, productList);
                                System.out.println(productMap.get(i).toString());
                                i++;
                            }
                            i=0;

                        }else if (inputNum.equals("2")){
                            System.out.println("Mehsullarina baxmaq istədiyiniz Agenti daxil edin ");
                            String orderAgent = in.nextLine();
                            BufferedReader bw3 = new BufferedReader(new FileReader("C:/MyAgent/Agent/" + orderAgent + "product.txt"));
                            productMap = new HashMap();
                            while ((product = bw3.readLine()) != null) {
                                productList = Arrays.asList(product.split("&"));
                                productMap.put(i, productList);
                                System.err.print(i+" "+ "Mehsulun adı "+ productList.get(0)  );
                                System.err.print("  stok sayi " + productList.get(1) );
                                System.err.println(" qiymeti " + productList.get(2));
                                i++;
                            }
                            i=0;
                            System.out.println("Almaq istediyiniz mehsulun nomresini daxil edin ");
                            int order = in.nextInt();
                            System.out.println(productMap.get(order).toString() + " adlı məhsuldan almaq istədiyiniz miqdarı daxil edin ");
                            in.nextLine();
                            String orderNum = in.nextLine();

                            BufferedWriter bw5 = new BufferedWriter(new FileWriter("C:/MyAgent/Agent/" + username + "product.txt",true));

                            String buy= productMap.get(order).toString();
                            String wrtire2File = buy.substring(1,buy.indexOf(","))+"&"+orderNum+"&"+buy.substring(buy.lastIndexOf(" "),buy.length()-1).trim();

                            bw5.newLine();
                            bw5.write(wrtire2File);

                            bw5.close();
                            System.out.println("Agentə alış haqqında sorğu müvəffəqiyyətlə göndərildi !");


                        }

                    }
                } else
                    System.err.println("Istifadəçi adı və ya şifrə yanlışdır");

                counter++;

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Xeta bash verdi...");
        }
    }
}

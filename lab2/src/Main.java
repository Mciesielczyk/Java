import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //System.out.printf("Podaj zakres gorny: ");
        Scanner scanner = new Scanner(System.in);
        int zakres = scanner.nextInt();
        try {
            int M = zakres;
            if (M > 400 || M < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("NIEPOPRAWNY ARGUMENT");
            System.exit(0);
        }
        System.out.printf("<1, %d>",zakres);
        int z;
        int los = (int) (Math.random() * zakres);
        int a = (int) (Math.log(zakres)/Math.log(2));
        int L = Math.abs(a+1);
        int fl=0;
//System.out.printf("Liczba prob %d",L);
        for(int i = 0; i < L; i++){
            int temp = L-i-1;
            System.out.print("[");
            for(int j = 0; j < L-temp; j++){
                System.out.print("*");
            }
            for(int j = 0; j < temp; j++){
                System.out.print(".");
            }
            System.out.println("]");
            System.out.println("PODAJ");

            if(scanner.hasNextInt()){
                z= scanner.nextInt();
                if(z==los){
                    System.out.println("TAK");
                    fl=1;
                    break;
                }
                if(z>los)System.out.println("ZBYT WIELE");
                if(z<los)System.out.println("NIE WYSTARCZY");
                //else System.out.printf("liczba prob %d ",temp);

           }
            else{
                System.out.println("NIE LICZBA");
                scanner.next();
            }

        }
        if(fl==0)System.out.println("NIESTETY");
        else System.out.println("GRATULACJE");
        scanner.close();

    }
}
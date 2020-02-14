import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventory {
    Scanner read = new Scanner(System.in);

    public void menu(){
        int option = 0;
        do{
            System.out.println("EL HATO COBRA\nBienvenido al administrador de inventario.");
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tAdministrar inventario");
            System.out.println("2)\tIngresar Caballo");
            System.out.println("3)\tVentas");
            System.out.println("0)\tSALIR");
            this.readInt();

        }while(option != 0);
    }

    public int readInt(int max, int min){
        int n = min-1;
        while(true){
            try{
                n = Integer.parseInt(read.nextLine());
            }catch (InputMismatchException ex){
                System.out.println("El numero debe ser entero y ");
            }
            if(n<= max && n>= min) break;
            System.out.println("Debe estar entre "+min+" y "+max);
        }
        return n;
    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.menu();
    }
}

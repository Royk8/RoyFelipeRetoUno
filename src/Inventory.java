import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Inventory {
    Scanner read = new Scanner(System.in);
    Caballo horse = new Caballo();

    public void menu(){
        ArrayList<Caballo> horsesList = new ArrayList<Caballo>(); //Arreglo de objetos de la clase Caballo
        int option = 0;
        do{
            System.out.println("EL HATO COBRA\nBienvenido al administrador de inventario.");
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tAdministrar inventario");
            System.out.println("2)\tVentas");
            System.out.println("0)\tSALIR");
            option = this.readInt(2,0);
            switch (option) {
                case 1:
                    menuInventario();
                    break;
                case 2:
                    menuVentas();
                    break;
            }
        }while(option != 0);
        System.out.println("Hasta luego.");
    }

    void menuInventario(){
        int option1 = 0;
        do{
            System.out.println("EL HATO COBRA\nBienvenido al administrador de inventario.");
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tVer Caballos");
            System.out.println("2)\tIngresa nuevo Caballo");
            System.out.println("3)\tModificar informacion de un Caballo");
            System.out.println("4)\tBorrar un Caballo");
            System.out.println("0)\tSALIR");
            option1 = this.readInt(2,0);
            switch (option1) {
                case 1:
                    checkHorses();
                    break;
                case 2:
                    addHorse();
                    break;
                case 3:
                    editHorse();
                    break;
                case 4:
                    deleteHorse();
                    break;
            }
        }while(option1 != 0);

    }

    void menuVentas(){
        int option2 = 0;
        do{
            System.out.println("EL HATO COBRA\nBienvenido al administrador de inventario.");
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tConsultar ventas");
            System.out.println("2)\tRegistrar venta");
            System.out.println("3)\tConsultar caballo mas preciado");
            System.out.println("0)\tSALIR");

            option2 = this.readInt(2,0);
            switch (option2) {
                case 1:
                    checkSells();
                    break;
                case 2:
                    addSell();
                    break;
                case 3:
                    mostLovedHorse();
                    break;
            }
        }while(option2 != 0);

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

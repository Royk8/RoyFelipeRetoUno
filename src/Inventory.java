import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Inventory {
    Scanner read = new Scanner(System.in);
    ArrayList<Caballo> horsesList;
    ArrayList<String> breedList;
    ArrayList<String> stepList;
    ArrayList<String> diseaseList;

    public void menu(){
        horsesList = new ArrayList<Caballo>(); //Arreglo de objetos de la clase Caballo
        breedList = new ArrayList<String>();
        stepList = new ArrayList<String>();
        diseaseList = new ArrayList<String>();
        diseaseList.add("Ninguna");

        int option = 0;
        do{
            System.out.println("EL HATO COBRA\nBienvenido al administrador de inventario.");
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tAdministrar inventario");
            System.out.println("2)\tVentas");
            System.out.println("0)\tSALIR");
            option = this.readInt(0,2);
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
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tVer Caballos");
            System.out.println("2)\tIngresa nuevo Caballo");
            System.out.println("3)\tModificar informacion de un Caballo");
            System.out.println("4)\tBorrar un Caballo");
            System.out.println("0)\tMenu principal");
            option1 = this.readInt(0,4);
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
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tConsultar ventas");
            System.out.println("2)\tRegistrar venta");
            System.out.println("3)\tConsultar caballo mas preciado");
            System.out.println("0)\tMenu principal");

            option2 = this.readInt(0,3);
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



    public void checkHorses(){
        System.out.println("Seleccione el Caballo del que desea consultar su informacion: ");
        for(int i = 0; i < horsesList.size(); i++){
            System.out.println(i+1 + horsesList.get(i).getName());
        }
        int optionIn = readInt(1, horsesList.size())-1;
        System.out.println("La informacion del Caballo seleccionado es: ");
        System.out.println("Nombre: "+horsesList.get(optionIn).getName());
        System.out.println("Raza: "+horsesList.get(optionIn).getBreed());
        System.out.println("Paso: "+horsesList.get(optionIn).getStep());
        System.out.println("Dieta: "+horsesList.get(optionIn).getDiet());
        System.out.println("Enfermedad: "+horsesList.get(optionIn).getDiseases());
        System.out.println("Nombre del padre: "+horsesList.get(optionIn).getFatherName());
        System.out.println("Pais: "+horsesList.get(optionIn).getCountry());
        System.out.println("Edad: "+horsesList.get(optionIn).getAge());
        System.out.println("Numero de premios: "+horsesList.get(optionIn).getAwards());
        System.out.println("Peso: "+horsesList.get(optionIn).getWeight());
        System.out.println("Precio del Caballo: "+horsesList.get(optionIn).getPrice());
        System.out.println("Precio de Pajilla: "+horsesList.get(optionIn).getSpermDoseCost());

    }

    public void addHorse(){
        //Ingreso de nombre.
        System.out.print("Ingrese el nombre del caballo: ");
        String name = read.nextLine();

        //Ingreso de la Raza
        System.out.println("Seleccione la raza de " + name +": ");
        for (int i = 0; i< breedList.size(); i++)
            System.out.println(i+1+ ". "+breedList.get(i));
        System.out.println("0. Ingresar una raza: ");
        int breedOption = readInt(0, breedList.size());
        if(breedOption == 0){
            System.out.print("Ingrese el nombre de la raza: ");
            String breed = read.nextLine();
            breedList.add(breed);
            breedOption = breedList.size();
        }

        //Ingreso de paso
        System.out.println("Seleccione el tipo de paso  de " + name +": ");
        for (int i = 0; i< stepList.size(); i++)
            System.out.println(i+1+ ". "+stepList.get(i));
        System.out.println("0. Ingresar un nuevo tipo de paso: ");
        int stepOption = readInt(0, stepList.size());
        if(stepOption == 0){
            System.out.print("Ingrese el nombre del tipo de paso: ");
            String step = read.nextLine();
            stepList.add(step);
            stepOption = stepList.size();
        }

        //Ingreso de alimentacion
        System.out.print("Ingrese el tipo de alimento preferido de " + name +": ");
        String diet = read.nextLine();

        //Ingreso de enfermedades
        System.out.println("Seleccione la enfermedad " + name +": ");
        for (int i = 0; i< diseaseList.size(); i++)
            System.out.println(i+1+ ". "+diseaseList.get(i));
        System.out.println("0. Ingresar una nueva enfermedad: ");
        int diseaseOption = readInt(0, diseaseList.size());
        if(diseaseOption == 0){
            System.out.print("Ingrese el nombre de la nueva enfermedad: ");
            String disease = read.nextLine();
            diseaseList.add(disease);
            diseaseOption = diseaseList.size();
        }

        //Ingreso del nombre del padre
        System.out.print("Ingrese el nombre del padre de " + name +": ");
        String fatherName = read.nextLine();

        //Ingreso del pais
        System.out.print("Ingrese el pais de origen de " + name +": ");
        String country = read.nextLine();

        //Ingreso Edad
        System.out.print("Ingrese la edad de "+name +": ");
        int age = readInt(0);

        //Ingreso numero de Premios
        System.out.print("Ingrese el numero de premios de "+name +": ");
        int awards = readInt(0);

        //Ingreso el Precio
        System.out.print("Ingrese el precio de "+name +": ");
        double price = readDouble(0);

        //Ingreso el Peso
        System.out.print("Ingrese el peso de "+name +": ");
        float weight = readFloat(0);

        //Creacion de nueva instancia de caballo
        Caballo caballo = new Caballo(name, breedList.get(breedOption-1), stepList.get(stepOption-1), diet,
                diseaseList.get(diseaseOption-1), fatherName, country, age, awards, price, weight);
    }

    public void editHorse(){

    }

    public void deleteHorse(){

    }

    public void checkSells(){

    }

    public void addSell(){

    }

    public void mostLovedHorse(){

    }

    public int readInt(int min){
        int n = min-1;
        while(true){
            try{
                n = Integer.parseInt(read.nextLine());
            }catch (InputMismatchException ex){
                System.out.println("El numero debe ser entero y ");
            }
            if(n>= min) break;
            System.out.println("Debe ser mayor o igual a  "+min);
        }
        return n;
    }

    public int readInt(int min, int max){
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

    public double readDouble(double min){
        double n = min-1;
        while(true){
            try{
                n = Double.parseDouble(read.nextLine());
            }catch (InputMismatchException ex){
                System.out.println("El numero debe ser real y ");
            }
            if(n> min) break;
            System.out.println("Debe ser mayor a  "+min);
        }
        return n;
    }

    public float readFloat(float min){
        float n = min-1;
        while(true){
            try{
                n = Float.parseFloat(read.nextLine());
            }catch (InputMismatchException ex){
                System.out.println("El numero debe ser real y ");
            }
            if(n> min) break;
            System.out.println("Debe ser mayor a  "+min);
        }
        return n;
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        inventory.menu();




    }
}

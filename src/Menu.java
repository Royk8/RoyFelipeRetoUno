import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner read = new Scanner(System.in);
    ArrayList<Caballo> horsesList;
    ArrayList<String> breedList;
    ArrayList<String> stepList;
    ArrayList<String> diseaseList;

    public Menu(){
        horsesList = new ArrayList<Caballo>(); //Arreglo de objetos de la clase Caballo
        breedList = new ArrayList<String>();
        stepList = new ArrayList<String>();
        diseaseList = new ArrayList<String>();
        diseaseList.add("Ninguna");
    }
    public void menu(){
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
        if(horsesList.size() == 0){
            System.out.println("No se encuentra ni un caballo en el registro");
            return;
        }
        System.out.println("Seleccione el Caballo del que desea consultar su informacion: ");
        for(int i = 0; i < horsesList.size(); i++){
            System.out.println((i+1) + ". "+ horsesList.get(i).getName());
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
        int breedIndex = addBreed(name);

        //Ingreso de paso
        int stepIndex = addStep(name);

        //Ingreso de alimentacion
        System.out.print("Ingrese el tipo de alimento preferido de " + name +": ");
        String diet = read.nextLine();

        //Ingreso de enfermedades
        int diseaseIndex = addDisease(name);

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
        Caballo caballo = new Caballo(name, breedList.get(breedIndex), stepList.get(stepIndex), diet,
                diseaseList.get(diseaseIndex), fatherName, country, age, awards, price, weight);
        horsesList.add(caballo);
    }

    public void editHorse(){
        System.out.println("Ingrese el indice del Caballo que desea modificar: ");
        for(int i = 0; i < horsesList.size(); i++){
            System.out.println(i+1 +" "+ horsesList.get(i).getName());
        }
        int optionIn = readInt(1, horsesList.size())-1;
        System.out.println("La informacion del Caballo seleccionado es: ");
        System.out.println("1. Nombre: "+horsesList.get(optionIn).getName());
        System.out.println("2. Raza: "+horsesList.get(optionIn).getBreed());
        System.out.println("3. Paso: "+horsesList.get(optionIn).getStep());
        System.out.println("4. Dieta: "+horsesList.get(optionIn).getDiet());
        System.out.println("5. Enfermedad: "+horsesList.get(optionIn).getDiseases());
        System.out.println("6. Nombre del padre: "+horsesList.get(optionIn).getFatherName());
        System.out.println("7. Pais: "+horsesList.get(optionIn).getCountry());
        System.out.println("8. Edad: "+horsesList.get(optionIn).getAge());
        System.out.println("9. Numero de premios: "+horsesList.get(optionIn).getAwards());
        System.out.println("10. Peso: "+horsesList.get(optionIn).getWeight());
        System.out.println("11. Precio del Caballo: "+horsesList.get(optionIn).getPrice());
        System.out.println("12. Precio de Pajilla: "+horsesList.get(optionIn).getSpermDoseCost());
        System.out.println("0. Modificar toda la informacion: ");
        int editOption = readInt(0,12);
        switch (editOption){
            case 0:
                horsesList.remove(optionIn);
                addHorse();
                break;
            case 1:
                System.out.println("Cual es el nuevo nombre de: "+ horsesList.get(optionIn).getName());
                String name = read.nextLine();
                horsesList.get(optionIn).setName(name);
                break;
            case 2:
                int breedIndex = addBreed(horsesList.get(optionIn).getName());
                horsesList.get(optionIn).setBreed(breedList.get(breedIndex));
                break;
            case 3:
                int stepIndex = addStep(horsesList.get(optionIn).getName());
                horsesList.get(optionIn).setStep(stepList.get(stepIndex));
                break;
            case 4:
                System.out.println("Cual es el nuevo alimento preferido de: "+ horsesList.get(optionIn).getName());
                String diet = read.nextLine();
                horsesList.get(optionIn).setDiet(diet);
                break;
            case 5:
                int diseaseIndex = addDisease(horsesList.get(optionIn).getName());
                horsesList.get(optionIn).setDiseases(diseaseList.get(diseaseIndex));
                break;
            case 6:
                System.out.println("Cual es el nuevo nombre del padre de: "+ horsesList.get(optionIn).getName());
                String fatherName = read.nextLine();
                horsesList.get(optionIn).setFatherName(fatherName);
                break;
            case 7:
                System.out.println("Cual es el nuevo pais de origen de: "+ horsesList.get(optionIn).getName());
                String country = read.nextLine();
                horsesList.get(optionIn).setCountry(country);
                break;
            case 8:
                System.out.println("Cual es la nueva edad de: "+ horsesList.get(optionIn).getName());
                int age = readInt(0);
                horsesList.get(optionIn).setAge(age);
                break;
            case 9:
                System.out.println("Cual es la nueva cantidad de premios de: "+ horsesList.get(optionIn).getName());
                int awards = readInt(0);
                horsesList.get(optionIn).setAwards(awards);
                break;
            case 10:
                System.out.println("Cual es el nuevo peso de: "+ horsesList.get(optionIn).getName());
                float weight = readFloat(0);
                horsesList.get(optionIn).setWeight(weight);
                break;
            case 11:
                System.out.println("Cual es el nuevo precio de: "+ horsesList.get(optionIn).getName());
                double price = readDouble(0);
                horsesList.get(optionIn).setPrice(price);
                break;
            case 12:
                System.out.println("El precio de la pajilla de "+ horsesList.get(optionIn).getName()+" no puede ser" +
                        "modificado.\nEste depende de sus otras caracteristicas, gracias por su comprension. ");
                break;
        }
    }

    public void deleteHorse(){
        if(horsesList.size() == 0){
            System.out.println("No se encuentra ningun caballo en el registro ");
            return;
        }
        System.out.println("Seleccione el indice del Caballo que desea borrar: ");
        for(int i = 0; i < horsesList.size(); i++){
            System.out.println((i+1) + ". "+ horsesList.get(i).getName());
        }
            int delIndex;
            Scanner IndexIn= new Scanner(System.in);
            delIndex = readInt(1,horsesList.size());

            if(delIndex-1 !=-1){
                System.out.println("El Caballo: "+horsesList.get(delIndex-1).getName()+" ha sido eliminado satisfactoriamente");
                horsesList.remove(delIndex-1);
                // AQUI BORRA EL NOMBRE DEL CABALLO DE LA LISTA PERO EL RESTO DE INFORMACION SIGUE GUARDAD, FALTA ELIMINARLA

            }else{
                System.out.println("El Caballo no se encuentra en la lista\n");
            }
    }

    public void checkSells(){

    }

    public void addSell(){

    }

    public void mostLovedHorse(){

    }

    public int addBreed(String name){
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
        return breedOption-1;
    }

    public int addStep(String name){
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
        return stepOption -1;
    }

    public int addDisease(String name){
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
        return diseaseOption-1;
    }

    public int readInt(int min){
        int n = min-1;
        while(true){
            try{
                n = Integer.parseInt(read.nextLine());
            }catch (InputMismatchException | NumberFormatException ex){
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
            }catch (InputMismatchException | NumberFormatException ex){
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
            }catch (InputMismatchException | NumberFormatException ex){
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
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("El numero debe ser real y ");
            }
            if(n> min) break;
            System.out.println("Debe ser mayor a  "+min);
        }
        return n;
    }
}

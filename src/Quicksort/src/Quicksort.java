//incluyendo las clases necesarias para: crear objetos random(java.util.Random), crear un flujo de entrada de datos (java.io.InputStreamReader),
//crear buffer de datos para guardarlos temporalmente en este para asignar los valores despues (java.io.BufferedReader), Obtener excepcion 
// de tipo entrada/salida para utilizar en las sentencias try catch (java.io.IOException), para imprimir arreglos en una sola linea
// y guardarlos en la matriz para de con todas las iteraciones para despues guardarlas en un archivo (java.util.Arrays), para manejar un archivo
//(java.io.File), para escribir en el archivo en una sola linea utilizando el metodo append (java.io.FileWriter), para escribir en el archivo a modo
// de lineas en el archivo de iteraciones.
import java.util.Random;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
//Definiendo la clase fundamental del programa, donde se definirán los objetos lista, los cuales tendrán como atributos tamaño del arreglo, 
//el limite superior para la generación de números aleatorios y el límite inferior.
class Lista 
{
//Limite inferior, límite superior, tamaño, número de comparaciones, número de cambios
    int tam, inferior, superior, comp, cam; 
    static int comp2, cam2;
    //declaracion del arreglo sobre el cual se opererará todo el tiempo
    int[] lista;
    //declaración de la matriz que guardará todas las iteraciones efectuadas en el método de ordenamiento hasta llegar al ordenamiento final
    static int [][] matrix;
    //declaración de objeto tipo random para la producción de números aleatorios
    Random r;
    /*Metodo constructor, el operador this se utilíza con el fin de especificar que las variables operan en toda la clase y por practicidad*/
    Lista(int tam, int inferior, int superior)
    {
        this.tam=tam;
        this.inferior=inferior;
        this.superior=superior;
        lista= new int[this.tam];  
        r= new Random();
        this.comp=0;
        this.cam=0;
    }
    /*Metodo para llenar una lista con elementos aleatorios en el intervalo dado por los valores dados al definir el objeto*/
    void llenarLista()
    {
        for(int i=0;i<this.tam;i++)
        {
            lista[i]= r.nextInt(this.superior-this.inferior +1)+ this.inferior;
        }
    }
    //Método para mezclar aleatoreamente los elementos de la lista justo ántes de ordenarla para de cierta forma cuasi garantizar que el número de
    //iteraciones realizadas por el elgoritmo no crezca sino hasta su caso promedio
    public static int[] RandomizeArray(int[] array)
    {
        Random rgen = new Random();		
	for (int i=0; i<array.length; i++) 
        {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
 
	return array;
    }
    //Método que únicamente opera en la clase (aunque si así se quisiera podría operar en una instancia de la clase)
    //Este método es crucial para el ordenamiento por quick sort
    //Primeramente recibe un arreglo como primer parámetro, el lugar en la lista desde donde se comenzará hasta el último,
    //en una primera particion se tendra desde el lugar cero hasta el ultimo lugar en la lista.
      static int particion(int [] l,int p,int r)
        {
            //se declara al pivote como el valor en el ultimo lugar de la particion enésima
            //se declara una variable auxiliar con el fin de efectuar los cambios de valores sin que estos se pierdan
            int x=l[r],aux;
            //se declara el índice en un lugar antes de p
            int i=p-1;
            //para j que empieza en p hasta r
            for (int j=p; j<r;j++)
            {
                //se cuentan las comparaciones para poder aproximar un valor promedio del numero de iteraciones que el programa realizará
                comp2+=1;
                //se ve cuales valores son iguales o menores al pivote para ir intercambiandolos y asi asegurar que cuando se acabe la partición
                //unicamente los valores menores que el pivote quedén presentes en la partición
                if(l[j]<=x)
                {
                    //se cuentan los cambios
                    cam2+=1;
                    //si el valor es menor o igual que el pivote se mueve el índice, nótese que para la primera iteración en el caso de que el primer
                    //elemento sea menor o igual que el pivote este se intercambiará con él mismo porque el indice se encuentra inicialmente apuntando a un lugar
                    //anterior a p
                    i=i+1;
                    //intercambiando
                    aux=l[i];
                    l[i]=l[j];
                    l[j]=aux;
                    //Se imprimen en pantalla las iteraciones además de que se guardan en una matriz con renglones definidos, pero no columnas
                    System.out.println(Arrays.toString(l));                    
                    matrix[cam2-1]=l;
                }
            }
            //una vez terminada la partición se intercambia el pivote por el valor al que apunte el índice
            aux=l[i+1];
            l[i+1]=l[r];
            l[r]=aux;
            cam2+=1;
            System.out.println(Arrays.toString(l));
            matrix[cam2-1]=l;
            return i+1;
        }
    
    //En este método se obtienen las particiones hasta que p sea igual a r, están presentes las llamadas recursivas hacia la izquierda y hacia la derecha
   // se obtiene el valor de q a partir del método particion, q es justamente la particion, como la partición asegura que siempre los elementos menores
   //al pivote estén antes entonces, eventualmente,cuando ya no se pueda partir más el arreglo se podrá asegurar que esa parte de éste esta ordenada
   // sumando todas las soluciones particulares al problema general se obtiene la solución general al problema que es el ordenamiento del arreglo.
    static void ordenamiento(int [] l, int p, int r)
    {
        comp2+=1;
        if (p<r)
        {
            int q=particion(l,p,r);
            ordenamiento(l,p,q-1);
            ordenamiento(l,q+1,r);
        }
    }
    
    // Este metodo unicamente recibe como argumento un arreglo. 
    public static void Quick(int [] l)
    {
        //se hace lo que se dijo anteriormente, mezclar aleatoreamente el arreglo
	RandomizeArray(l);
        //Se declara un nuevo objeto de la clase File para crear un archivo txt
        File f2 = new File("Iteraciones.txt");
        //Sentencia if para ver si ya existe o no el archivo
        if(!f2.exists())
        {
            //Si no existe el programa intentará crearlo, como esto puede arrojar una excepción se utiliza la sentencia try catch
            try
            {
                f2.createNewFile();
            }
            catch(IOException io)
            {System.out.println("Error: " + io.toString());}   
        }
        //Se declara el tamaño de la matriz que fungirá como recipiente para las iteraciones
        matrix= new int[2300][];
        //se llama al método ordenamiento, que será precisamente el qué ordene la lista
	ordenamiento(l,0,l.length-1);
        try
        {
            //se utiliza un PrintWriter para guardar en el archivo de iteraciones las iteraciones pero en un formato que no sea una sola línea
            PrintWriter pw = new PrintWriter(f2);
            for(int i=0;i<cam2;i++)
            {
                //Se utiliza el método toString de la clase Arrays para convertir lo contenido en cada renglón de la matriz a cadenas de caracteres
                //y asi guardarlos como renglones en un archivo, por ello todo esto en un for, como si se estuviera imprimiendo una matriz
                pw.println(Arrays.toString(matrix[i]));
            }
            pw.close();
        }
        catch(IOException io)
        {
            System.out.println("Error: "+ io.toString());
        }
    }
    
    //Este método únicamente funciona como una herramienta para simplificar el uso del ordenamiento en las instancias de la clase lista, esta
    // simplificación se produce en la forma de que sólo se tenga que utilizar el metodo quickSort sobre un objeto de la clase lista para ordenarlo
    void quickSort()
    {
        this.comp=0;
        this.cam=0;
        comp2=0;
        cam2=0;
        Quick(lista);
        this.comp=comp2;
        this.cam=cam2;
    }
    //Regresa la lista ya no como una instancia sino como un arreglo para poder ser impreso en pantalla
    int [] regresarLista()
    {        
        return lista;
    }
    
    //Obtiene las camparaciones efectuadas, esto con el fin de obtener un buen tamaño para la matriz
    int getComparaciones()
    {
        return this.comp;
    }
    //Obtiene los cambios efectuados
    int getCambios()
    {
        return this.cam;
    }
    //Obtiene la longitud del arreglo
    int getLongitud()
    {
        return this.lista.length;
    }
    
}

//clase publica que contiene al main, aqui se crean todos los objetos y se mandan llamar a los métodos
public class Quicksort 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        //Se obtienen los valores del usuario a partir del flujo de datos del teclado
        //estos valores son los necesarios para inicializar con el método constructor el objeto lista, como lo son 
        //el limite inferior y superior del intervalo de valores aleatorios y la longitud que se quiere que tenga el arreglo
        int tam, in, sup, aux;
        int [] conteo;
        conteo= new int[2];
        String opcion = null;
        InputStreamReader ir=new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(ir);
        System.out.println("Bienvenido al programa \n");
        //Primero se obtiene la opción de lo que se quiere realizar por parte del usuario
        while(true)
        {
            System.out.println("Elegir si se quiere una lista en pantalla (lista) o salir (salir)");
            try{
            opcion=br.readLine();
            }
            catch(IOException ioe)
            {
                System.out.println("Error: " + ioe.toString());
                continue;
            }
            if ("lista".equals(opcion)|"Lista".equals(opcion))
            {
                System.out.println("\nIntroducir limite inferior  mayor que cero y limite superior mayor que cero");
                try
                {
                    in=Integer.parseInt(br.readLine());
                    sup=Integer.parseInt(br.readLine());
                }
                catch(IOException ioe)
                {
                    System.out.println("Error: " + ioe.toString());
                    continue;
                }
                //Archivos con la entrada y la salida
                File f1 = new File("Entrada.txt");
                File f3 = new File("Salida.txt");
                if (!f1.exists() && !f3.exists())
                {
                    //si no existen los archivos intentar crearlos a través de una sentencia try catch
                    try
                    {
                        f1.createNewFile();
                        f3.createNewFile();
                    }
                    catch(IOException io)
                    {}   
                }
                else if(!f1.exists())
                {
                    try
                    {
                        f1.createNewFile();
                    }
                    catch(IOException io)
                    {System.out.println("Error: " + io.toString());}   
                }
                else if(!f3.exists())
                {
                    try
                    {
                        f3.createNewFile();
                    }
                    catch(IOException io)
                    {System.out.println("Error: " + io.toString());}   
                }
                try
                {
                    FileWriter fw = new FileWriter(f1, true);
                }
                catch(IOException io)
                {System.out.println("Error: " + io.toString());}
                //Se crea el objeto lista
                Lista l= new Lista(353, in, sup);
                //Se llena la lista de elementos aleatorios con el metodo llenarLista
                l.llenarLista();
                
                try
                {
                    //Se escribe en el archivo la lista sin
                    FileWriter fw = new FileWriter(f1, true);
                    fw.append(Arrays.toString(l.regresarLista())+"\n Longitud del Arreglo: " + l.getLongitud());
                    fw.close();                
                }
                catch(IOException io)
                {System.out.println("Error: " + io.toString());} 
                //Se imprime la lita antes de ser ordenada, así como su longitud
                System.out.println("\n"+Arrays.toString(l.regresarLista())+"\n");
                System.out.println("Longitud del arreglo: "+ l.getLongitud());
                //Se llama a quicksort para que actúe sobre la lista
                l.quickSort();
                try
                {
                    //Se escribe en el archivo la lista ya ordenada
                    FileWriter fw = new FileWriter(f3, true);
                    fw.append(Arrays.toString(l.regresarLista())+ "\n Longitud del Arreglo: "+ l.getLongitud());
                    fw.close();                
                }
                catch(IOException io)
                {System.out.println("Error: " + io.toString());}                
                System.out.println("\n"+Arrays.toString(l.regresarLista())+"\n");
                //Se llena el areglo unidimensional arreglos con los cambios y las comparaciones totales efectuadas por el programa para ordenar 
                //los elementos del arreglo
                conteo[0]= l.getCambios();
                conteo[1]= l.getComparaciones();
                              
                System.out.println("\n"+Arrays.toString(conteo)); 
                System.out.println("Longitud del arreglo: "+ l.getLongitud());
            }
            else if("salir".equals(opcion)|"Salir".equals(opcion))
            {
               //Si la opción es salir, se rompe la ejecución del while y la ejecución del programa
               break; 
            }
            else
            {
                System.out.println("Introducir opcion valida, intentar de nuevo\n");
            }           
        }
    }   
}

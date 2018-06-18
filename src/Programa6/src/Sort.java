import java.util.Random;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*Universidad Nacional Autonoma de Mexico
Facultad de Ingenieria
23 de noviembre de 2015
Algoritmos y Estructuras de Datos
Programa: 06
Author: @leguiart
*/
class Lista 
{
    int tam, inferior, superior, comp, cam, acum; 
    static int comp2, cam2;
    int[] lista;
    int[][] matrix;
    Random r;
    /*Metodo constructor*/
    Lista(int tam, int inferior, int superior)
    {
        this.tam=tam;
        this.inferior=inferior;
        this.superior=superior;
        lista= new int[this.tam];  
        r= new Random();
        this.comp=0;
        this.cam=0;
        this.acum=0;
    }
    /*Metodo para llenar una lista*/
    void llenarLista()
    {
        for(int i=0;i<this.tam;i++)
        {
            lista[i]= r.nextInt(this.superior-this.inferior +1)+ this.inferior;
        }
    }
    
    public static int[] RandomizeArray(int[] array)
    {
        Random rgen = new Random();  // Random number generator			
	for (int i=0; i<array.length; i++) 
        {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
 
	return array;
    }
    
    void bubbleSort()
    {
        int bandera=0, cont=0;
        int max= lista[0];
        while(bandera!=1)
        {
            bandera=1;
            while(cont<lista.length)
            {
                if(lista[cont]>=max)
                {
                    max=lista[cont];
                }
                else
                {
                    lista[cont-1]=lista[cont];
                    lista[cont]=max;
                    bandera=0;
                    this.cam+=1;
                }
                cont+=1;
            }
            this.comp=cont+this.comp;
            cont=0;
            max=lista[0];
        }
    }
    
    private static int particion(int [] l,int p,int r)
    {
        int x=l[r],aux;
        int i=p-1;
        for (int j=p; j<r;j++)
        {
            comp2+=1;
            if(l[j]<=x)
            {
                cam2+=1;
                i=i+1;
                aux=l[i];
                l[i]=l[j];
                l[j]=aux;
            }
        }
        aux=l[i+1];
        l[i+1]=l[r];
        l[r]=aux;
        cam2+=1;
        return i+1;
    }
    
    private static void ordenamiento(int [] l, int p, int r)
    {
        comp2+=1;
        if (p<r)
        {
            int q=particion(l,p,r);
            ordenamiento(l,p,q-1);
            ordenamiento(l,q+1,r);
        }
    }
     
    private static void Quick(int [] l)
    {	
	RandomizeArray(l);
	ordenamiento(l,0,l.length-1);
    }
    
    void quickSort()
    {
        comp2=0;
        cam2=0;
        Quick(lista);
        this.comp=comp2;
        this.cam=cam2;
    }
    
    private static int minimo(int [] lista, int x, int min)
    {
        comp2+=1;
        if (x==lista.length-1)
        {
            return min;
        }
        comp2+=1;
        if (lista[min]>lista[x+1])
        {
            min=x+1;
        }
        return minimo(lista,x+1,min);
    }
    
		
    private static void Selec(int[] l)
    {
        int aux;
	for(int i=0; i<l.length; i++)
        {
            int m=minimo(l,i,i);
            comp2+=1;
            aux=l[i];
            l[i]=l[m];
            l[m]=aux;
            cam2+=1;
        }
    }     
    
    void Seleccion()
    {
        comp2=0;
        cam2=0;
        Selec(lista);
        this.comp=comp2;
        this.cam=cam2;
    }
    
    int [] regresarLista()
    {        
        return lista;
    }
    
    private static int[][] ordenamientoInsercion(int [][] lista, int d) //numeros es una lista
    {
        int tama = lista.length, a;
        int [] indice; //tamaño de la lista
        for (int i=0;i<tama;i++)
        {
            cam2++;
            indice = lista[i];
            a = i-1;
            while (a >= 0 && lista[a][d] > indice[d])
            {
                comp2++;
                lista[a+1] = lista[a];
                cam2++;
                a = a-1;
            }
            lista[a+1] = indice;
            cam2++;
        }
        return lista;
    }
    
    private static int[][] division(int[]lista, int sup)
    {
        int[] aux;
        int[][] listadelistas;
        int lugares=0;
        float[] array;
        float n;
        while (true)
        {
            sup=sup/10;
            lugares+=1;
            if (sup==0)
                break;
        }
        aux=new int[lugares];
        array = copyFromIntArray(lista);
        listadelistas = new int [lista.length][lugares];
        for (int i=0;i<lista.length;i++)
        {
            for (int j=0;j<lugares;j++)
            {           
                n= (float)((array[i]/(Math.pow(10,j+1))-(int)(lista[i]/(Math.pow(10,j+1))))*10);
                aux[j]=(int)n;
            }
            listadelistas[i]=aux;
            aux=new int[lugares];
        }
        return listadelistas;
    }
    
    public static float[] copyFromIntArray(int[] source) 
   {
        float[] dest = new float[source.length];
        for(int i=0; i<source.length; i++) {
            dest[i] = source[i];
        }
        return dest;
   }

    public static int [] Reverse(int[] lista)
    {
        for(int i = 0; i < lista.length / 2; i++)
        {
        int temp = lista[i];
        lista[i] = lista[lista.length - i - 1];
        lista[lista.length - i - 1] = temp;
        } 
        return lista;
    }

    private static int[][] ordenamientoRadix(int [][] lista, int d)
    {
        for(int i=0;i<d;i++)
            ordenamientoInsercion(lista, i);
        for (int i=0; i<lista.length; i++)
            lista[i]=Reverse(lista[i]);
        return lista;
    }
    
    void Radix()
    {
        comp2=0;
        cam2=0;
        matrix=division(this.lista,this.superior);
        ordenamientoRadix(matrix,matrix[0].length);
        this.cam=cam2;
        this.comp=comp2;
    }
    
    void busquedaLineal(int elemento)
    {
        boolean bandera=false;
        this.acum=0;
        for(int i=0; i<this.tam; i++)
        {
            if(elemento==lista[i])
            {
                bandera=true;
                System.out.print("\nElemento buscado: " + elemento + "; Lugar en la lista: " + i + "\n");
                this.acum+=1;
                if(lista[i]!=lista[i+1])
                    break;
            }
            this.acum+=1;
        }
        if(bandera==false)
        {
        System.out.println("\nElemento no encntrado");
        }
    }
    
    boolean busquedaBinaria(int elemento)
    {
        int izq=0, der=lista.length-1, medio;
        this.acum=0; 
        boolean bandera=false;
        while(izq<=der)
        {
            medio=(der+izq)/2;
            if (elemento==lista[medio])
            {
                 System.out.println("\n Elemento buscado: " + elemento + "; Lugar en la lista: " + medio + "\n");
                 this.acum+=1;
                 bandera=true;
                 return true;
            }
            else if(elemento<lista[medio])
            {
                this.acum+=1;
                der=medio-1;
            }
            else
            {
                this.acum+=1;
                izq=medio+1;
            }
        }
        if (bandera==false)
        {
            System.out.println("\nElemento no encontrado");
            return bandera;
        }
        else
            return bandera;
    }
    
    int getComparacionesdeBusquedas()
    {
        return this.acum;
    }
    
    int getComparaciones()
    {
        return this.comp;
    }
    
    int getCambios()
    {
        return this.cam;
    }
    
    int[][] getMatrix()
    {
        return matrix;
    }
}


public class Sort 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int tam, in, sup, aux;
        int [][] conteo;
        conteo= new int[10][8];
        String opcion = null;
        InputStreamReader ir=new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(ir);
        
        System.out.println("Programa que ordena arreglos a través de bubblesort, quicksort, selección y radix basado en inserción. \n"
                + "Además de graficar las comparaciones e intercambios realizados para listas de tamaño incremantal por cada uno de los "
                + "métodos mencionados y hacer búsqueda lineal y binaria");
        while(true)
        {
            System.out.println("Elegir si se quiere varias listas de tamaños incrementales (varias), una sola (una) o salir (salir)");
            try{
            opcion=br.readLine();
            }
            catch(IOException ioe)
            {
                System.out.println("Error: " + ioe.toString());
                continue;
            }
            if ("varias".equals(opcion)|"Varias".equals(opcion))
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
                for(int j=0;j<8;j+=2)
                {
                    for(int i=0;i<10;i++)
                    {
                        Lista l= new Lista((i+1)*10, in, sup);
                        l.llenarLista();
                        System.out.println(Arrays.toString(l.regresarLista()));
                        if(j==0)
                            l.bubbleSort();
                        else if (j==2)
                            l.quickSort();
                        else if (j==4)
                            l.Seleccion();
                        else
                            l.Radix();
                        if (j==6)
                            System.out.println(Arrays.deepToString(l.getMatrix()));
                        else
                            System.out.println(Arrays.toString(l.regresarLista()));
                        conteo[i][j]= l.getCambios();
                        conteo[i][j+1]= l.getComparaciones();
                    }
                    System.out.println("\n");
                }
                
                System.out.println("\n");
                
                for (int i=0;i<10;i++)
                {
                    System.out.println(Arrays.toString(conteo[i])); 
                }
                // Fuente de Datos
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        DefaultCategoryDataset data2 = new DefaultCategoryDataset();
        DefaultCategoryDataset data3 = new DefaultCategoryDataset();
        DefaultCategoryDataset data4 = new DefaultCategoryDataset();
        DefaultCategoryDataset data5 = new DefaultCategoryDataset();
        DefaultCategoryDataset data6 = new DefaultCategoryDataset();
        DefaultCategoryDataset data7 = new DefaultCategoryDataset();
        DefaultCategoryDataset data8 = new DefaultCategoryDataset();
        for(int i=0;i<10;i++)
        {
            data.addValue(conteo[i][1], "Comparaciones", String.valueOf((i+1)*10));            
            data2.addValue(conteo[i][3], "Comparaciones", String.valueOf((i+1)*10));
            data3.addValue(conteo[i][5], "Comparaciones", String.valueOf((i+1)*10));
            data4.addValue(conteo[i][0], "Intercambios", String.valueOf((i+1)*10));
            data5.addValue(conteo[i][2], "Intercambios", String.valueOf((i+1)*10));
            data6.addValue(conteo[i][4], "Intercambios", String.valueOf((i+1)*10));
            data7.addValue(conteo[i][7], "Comparaciones", String.valueOf((i+1)*10));
            data8.addValue(conteo[i][6], "Intercammbios", String.valueOf((i+1)*10));
        }
        
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart(
         "Comparaciones BubbleSort", "Numero de datos", "Numero de comparaciones",
         data,PlotOrientation.VERTICAL,
         true,
         true,
         false);
        JFreeChart chart2 = ChartFactory.createLineChart(
         "Comparaciones QuickSort", "Numero de datos", "Numero de comparaciones",
         data2,PlotOrientation.VERTICAL,
         true,
         true,
         false);
        JFreeChart chart3 = ChartFactory.createLineChart(
         "Comparaciones Seleccion", "Numero de datos", "Numero de comparaciones",
         data3,PlotOrientation.VERTICAL,
         true,
         true,
         false);
        JFreeChart chart4 = ChartFactory.createLineChart(
         "Intercambios BubbleSort", "Numero de datos", "Numero de Intercambios",
         data4,PlotOrientation.VERTICAL,
         true,
         true,
         false);
        JFreeChart chart5 = ChartFactory.createLineChart(
         "Intercambios QuickSort", "Numero de datos", "Numero de Intercambios",
         data5,PlotOrientation.VERTICAL,
         true,
         true,
         false);
        JFreeChart chart6 = ChartFactory.createLineChart(
         "Intercambios Seleccion", "Numero de datos", "Numero de Intercambios",
         data6,PlotOrientation.VERTICAL,
         true,
         true,
         false);
        JFreeChart chart7 = ChartFactory.createLineChart(
         "Comparaciones Radix", "Numero de datos", "Numero de Comparaciones",
         data7,PlotOrientation.VERTICAL,
         true,
         true,
         false);
        JFreeChart chart8 = ChartFactory.createLineChart(
         "Intercambios Radix", "Numero de datos", "Numero de Intercambios",
         data8,PlotOrientation.VERTICAL,
         true,
         true,
         false);
 
        // Mostrar Grafico
        ChartFrame frame = new ChartFrame("JFreeChart", chart);
        ChartFrame frame2 = new ChartFrame("JFreeChart", chart2);
        ChartFrame frame3 = new ChartFrame("JFreeChart", chart3);
        ChartFrame frame4 = new ChartFrame("JFreeChart", chart4);
        ChartFrame frame5 = new ChartFrame("JFreeChart", chart5);
        ChartFrame frame6 = new ChartFrame("JFreeChart", chart6);
        ChartFrame frame7 = new ChartFrame("JFreeChart", chart7);
        ChartFrame frame8 = new ChartFrame("JFreeChart", chart8);
        frame.pack();
        frame2.pack();
        frame3.pack();
        frame4.pack();
        frame5.pack();
        frame6.pack();
        frame7.pack();
        frame8.pack();
        frame.setVisible(true);
        frame2.setVisible(true);
        frame3.setVisible(true);
        frame4.setVisible(true);
        frame5.setVisible(true);
        frame6.setVisible(true);
        frame7.setVisible(true);
        frame8.setVisible(true);
            }
            else if("una".equals(opcion)|"Una".equals(opcion))
            {
                System.out.println("\nIntroducir numero de elementos");
                System.out.println("Limite inferior  mayor que cero y limite superior mayor que cero");
                try
                {
                    tam=Integer.parseInt(br.readLine());
                    in=Integer.parseInt(br.readLine());
                    sup=Integer.parseInt(br.readLine());
                }
                catch(IOException ioe)
                {
                    System.out.println("Error: " + ioe.toString());
                    continue;
                }
                Lista l= new Lista(tam, in, sup);
                l.llenarLista();
                System.out.println(Arrays.toString(l.regresarLista())); 
                l.quickSort();
                System.out.println("\n");
                System.out.println(Arrays.toString(l.regresarLista()));
                while(true)
                {
                    System.out.println("Introducir numero a buscar para salir oprimir cualquier letra del teclado");
                    try
                    {
                        aux=Integer.parseInt(br.readLine());
                    }
                    catch(IOException ioe)
                    {
                        break;
                    }
                    l.busquedaLineal(aux);
                    System.out.println("La cantidad de comparaciones de la busqueda lineal fue: " + l.getComparacionesdeBusquedas());
                    l.busquedaBinaria(aux);
                    System.out.println("La cantidad de comparaciones de la busqueda binaria fue: " + l.getComparacionesdeBusquedas()+ "\n");
                }
            }
            else if("salir".equals(opcion)|"Salir".equals(opcion))
            {
               break; 
            }
            else
            {
                System.out.println("Introducir opcion valida, intentar de nuevo\n");
            }           
        }
        /*
        Lista l= new Lista(10, 1, 10);
        l.llenarLista();
        System.out.println(Arrays.toString(l.regresarLista())); 
        l.bubbleSort();
        System.out.println("\n");
        System.out.println(Arrays.toString(l.regresarLista())); 
        l.llenarLista();
        System.out.println("\n"+Arrays.toString(l.regresarLista()));
        l.quickSort();
        System.out.println("\n"+Arrays.toString(l.regresarLista()));
        l.llenarLista();
        System.out.println("\n"+Arrays.toString(l.regresarLista()));
        */
    }    
}
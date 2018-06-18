import java.util.Arrays;

public class Radix
{
static int[][] ordenamientoInsercion(int [][] lista, int d) //numeros es una lista
{
    int tama = lista.length, a;
    int [] indice; //tamaño de la lista
    for (int i=0;i<tama;i++)
    {
        indice = lista[i];
        a = i-1;
        while (a >= 0 && lista[a][d] > indice[d])
        {
            lista[a+1] = lista[a];
            a = a-1;
        }
        lista[a+1] = indice;
    }
    return lista;
}	

static int[][] division(int[]lista, int sup)
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

static int[][] ordenamientoRadix(int [][] lista, int d)
{
    for(int i=0;i<d;i++)
        ordenamientoInsercion(lista, i);
    for (int i=0; i<lista.length; i++)
        lista[i]=Reverse(lista[i]);
    return lista;
}

public static void main(String[] args)
{
int[] l= {808, 901, 735, 628, 666, 110, 234, 993, 812, 13};
int[][] matrix;

System.out.println("\n"+Arrays.toString(l));
                       
matrix=division(l,1000);

System.out.println(Arrays.deepToString(matrix));

ordenamientoRadix(matrix,4);

System.out.println(Arrays.deepToString(matrix));

}

}
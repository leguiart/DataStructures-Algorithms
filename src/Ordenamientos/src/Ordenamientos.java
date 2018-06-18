
import java.util.Random;
import java.util.Arrays;

public class Ordenamientos
{
    public static void main(String args[])
    {
        int [] lst;
        lst= generarLista(20, 20, 1);
        System.out.println(Arrays.toString(lst)+ "\n");
        System.out.println(Arrays.toString(lst)+ "\n");
        Quick(lst);
        System.out.println(Arrays.toString(lst)+ "\n");
        lst=RandomizeArray(lst);
        Seleccion(lst);
        System.out.println(Arrays.toString(lst)+ "\n");
    }
    
    public static int[] generarLista(int tam, int superior, int inferior)
    {
        int[] lista;
        lista= new int[tam];
        Random r;
        r=new Random();
        for(int i=0;i<tam;i++)
        {
            lista[i]= r.nextInt(superior-inferior +1)+ inferior;
        }
        return lista;
    }
    
    public static int[] RandomizeArray(int[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}
    
    static int particion(int [] l,int p,int r)
        {
            int x=l[r],aux;
            int i=p-1;
            for (int j=p; j<r;j++)
            {
                if(l[j]<=x)
                {
                    i=i+1;
                    aux=l[i];
                    l[i]=l[j];
                    l[j]=aux;
                }
            }
            aux=l[i+1];
            l[i+1]=l[r];
            l[r]=aux;
            return i+1;
        }
    
    static void ordenamiento(int [] l, int p, int r)
    {
        if (p<r)
        {
            int q=particion(l,p,r);
            ordenamiento(l,p,q-1);
            ordenamiento(l,q+1,r);
        }
    }
     
    public static void Quick(int [] l)
    {		
	l=RandomizeArray(l);
	ordenamiento(l,0,l.length-1);
    }
    
    static int minimo(int [] lista, int x, int min)
        {
            if (x==lista.length-1)
                return min;
            if (lista[min]>lista[x+1])
                min=x+1;
            return minimo(lista,x+1,min);
        }
    
		
    static void Seleccion(int[] l)
    {
        int aux;
	for(int i=0; i<l.length; i++)
        {
            int m=minimo(l,i,i);
            aux=l[i];
            l[i]=l[m];
            l[m]=aux;
        }

    }        
}
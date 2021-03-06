import math
#Definiendo funciones auxiliares

#Init: funcionará como inicializador de el generador de identificaciones para los nodos
#y para las listas que contendran los recorridos (preorden, inorden, postorden).

#Generador: como el nombre lo dice genera identificaciones para cada nodo para que este pueda ser apuntado
#Cada nodo sera una lista compuesta de: [apuntadorizq, dato, apuntadorder, identificador].

#esVacio: comprueba si el arbol esta vacio o no

#izq: recibira un nodo y una lista(arbol) y evalua a quien esta apuntando basandose en los identificadores

#der: hace lo mismo que izq pero para el apuntador derecho

#tieneHijoIzq: evalua si un nodo tiene o no hijo izquierdo

#tieneHijoDer: evalua si un nodo tiene o no hijo derecho

#insertarHijoDer: cambia el valor del apuntador derecho del nodo que sera padre del nuevo nodo por el valor del identificador
#del nodo nuevo para que "apunte" a este.

#insertarHijoIzq: hace lo mismo que insertarHijoDer pero para el apuntador derecho.

#CuentaHijos: como su nombre lo indica cuenta los hijos de un nodo evaluando cuantos apuntadores 
#tiene ocupados por valores diferentes a nulo

#postOrden: es el unico recorrido que necesita una funcion ya que el preorden es simplemente
#el orden en que los valores fueron insertados y el inorden es tan solo los valores ordenados de 
#menor a mayor.

def ini():
    global i
    global listapre
    global listain
    global listapost
    i=0
    listapre=[]
    listain=[]
    listapost=[]
    
def generador():
    global i
    i=i+1
    return i

def esVacio(ABB):
    if len(ABB)==0:
        return True
    return False

def izq(nodo, lista):
    for i in lista:
        if nodo.get_izq()==i.get_id():
            return i
    return False
        
def der(nodo, lista):
    for i in lista:
        if nodo.get_der()==i.get_id():
            return i
    return False

def tieneHijoIzq(nodo):
    if nodo.get_izq()!=None:
        return True
    return False

def tieneHijoDer(nodo):
    if nodo.get_der()!=None:
        return True
    return False

def insertarHijoIzq(x, lista, nodo):
    n=Nodo()
    n.dato(x)
    nodo.mod_pointers(n.get_id(), False)
    lista.append(n)


def insertarHijoDer(x,lista, nodo):
    n=Nodo()
    n.dato(x)
    nodo.mod_pointers(n.get_id())
    lista.append(n)
    
def cuentaHijos(nodo):
    if tieneHijoIzq(nodo) and tieneHijoDer(nodo):
        return 2
    elif tieneHijoIzq(nodo) or tieneHijoDer(nodo):
        return 1
    else:
        return 0

def postOrden(nodo,arbol):
    if esVacio(arbol):
        return None
    postOrden(izq(nodo, arbol), arbol)
    postOrden(der(nodo, arbol), arbol)
    listapost.append(nodo.get_dato())
    
def altura(arbol):
    return round(math.log(len(arbol),2)-1)


#Definiendo tipo de dato Nodo                 
'''
La clase nodo creara objetos de tipo nodo, los cuales tienen como atributos un apuntador izquierdo
y derecho, valor en el nodo e identificador para que otros nodos lo puedan apuntar, inicialmente
el unico valor diferente a nulo del nodo sera el identificador, Todo lo anterior en el metodo init.
(constructor).
El metodo dato obtiene un dato del exterior para el nodo.
El metodo get info simplemente es para obtener el contenido del nodo (apuntadores, dato, identificador).
get_izq regresa el apuntador izquierdo.
get_der regresa el apuntador derecho.
get_id regresa el identificador.
mod_pointers permite modificar un apuntador ya sea el izquierdo o el derecho.

'''
class Nodo:
    
    def __init__(self):
        self.izq=None
        self.der=None
        self.id=generador()
        self.x=None
        
    def dato(self, x):
        self.x=x
        
    def get_info(self):
        self.info=[self.izq, self.x, self.der, self.id]
        return self.info
    
    def get_izq(self):
        return self.izq
    
    def get_der(self):
        return self.der
    
    def get_dato(self):
        return self.x
    
    def get_id(self):
        return self.id
        
    def mod_pointers(self,valor, d=True):
        if d:
            self.der=valor
        else:
            self.izq=valor

#Definiendo clase arbol binario de datos que se compondra de objetos
#de clase nodo
'''
La clase arbol se inicializa simplemente con una lista, lo anterior en el metodo init.
El metodo str es para imprimir el contenido del objeto, que en este caso seran los nodos
y para ello precisamente es el metodo get_info de la clase nodo.
El metodo len es unica y exclusivamente para poder retornar la longitud del arbol que en este caso
es simplemente la longitud de una lista, esto simplemente para comprobar si es vacio o no.
Insertar: utiliza practicamente la misma estructura del algoritmo visto en clase, la unica
excepcion es que utiliza append para concatenar nodos a la lista arbol.
get_raiz: guarda simplemente el primer valor insertado al arbol diciendo que esa es la raiz.
preO: inserta a listapre los valores de los nodos dentro del arbol y ya esta en preorden
inO: inserta a listaino los valores de los nodos dentro del arbol y los ordena en forma ascendente
dando como resultado al inorden
Buscar: utiliza practicamente la misma estructura del algoritmo visto en clase para buscar en 
un arbol solo cambian algunas cosas en cuanto a la utilizacion de metodos y funciones auxiliares
Sacar: De nuevo utiliza la misma estructura que el algoritmo para sacar visto en clase, solo que
para este caso al quitar un elemento del arbol no basta con eliminar sus referencias si no que
se debe eliminar de la lista arbol. Ademas de que para el caso en que el nodo a eliminar tenga
dos hijos el nodo sucesor desaparece, el valor del sucesor toma el valor del predecesor y se cambia
el apuntador izquierdo del nodo padre del sucesor a nulo manualmente



'''
class ABB:
    
    def __init__(self):
        self.arbol=[]
        
    def __str__(self):
        st=''
        for i in self.arbol:
            st=st+str(i.get_info())+' '
        return st
    
    def __len__(self):
        return len(self.arbol)
    

    def insertar(self, nodo , x):
        if esVacio(self.arbol):
            self.arbol.append(nodo)
            self.arbol[0].dato(x)
            self.raiz=self.arbol[0]
        elif x<nodo.get_dato():
            if tieneHijoIzq(nodo):
                self.insertar(izq(nodo, self.arbol),x)
            else:
                insertarHijoIzq(x, self.arbol, nodo)
        elif x>nodo.get_dato():
            if tieneHijoDer(nodo):
                self.insertar(der(nodo,self.arbol),x)
            else:
                insertarHijoDer(x, self.arbol, nodo)
        else:
            return True
        return False
    
    def get_raiz(self):
        return self.raiz
    
    def preO(self):
        for i in self.arbol:
            listapre.append(i.get_dato())
        return listapre
        
    def inO(self):
        for i in self.arbol:
            listain.append(i.get_dato())
        listain.sort()
        return listain
     
    def postO(self):
        postOrden(self.get_raiz(), self.arbol)
        return listapost
    
    def buscar(self, nodo_actual, valor, nodo_padre=None):
        if nodo_actual.get_dato()==None or esVacio(self.arbol):
            return None, None
        elif valor<nodo_actual.get_dato():
            return self.buscar(izq(nodo_actual, self.arbol), valor, nodo_actual)
        elif valor>nodo_actual.get_dato():
            return self.buscar(der(nodo_actual,self.arbol), valor, nodo_actual)
        else:
            return nodo_actual, nodo_padre
    
    def minimo(self, nodo, padre):
        if tieneHijoIzq(nodo):
            return self.minimo(izq(nodo, self.arbol), nodo)
        return nodo, padre
    
    def maximo(self, nodo, padre):
        if tieneHijoDer(nodo):
            return self.maximo(der(nodo, self.arbol), nodo)
        return nodo, padre
        
    def sacar(self, raiz, valor, min=True):
        aux, padre= self.buscar(raiz, valor, self.get_raiz())
        if aux!=None:
            nHijos=cuentaHijos(aux)
            if nHijos==0:
                if izq(padre, self.arbol).get_dato()== aux.get_dato():
                    padre.mod_pointers(None, False)
                else:
                    padre.mod_pointers(None)
                self.arbol.remove(aux)
            elif nHijos==1:
                if aux==padre:
                    if tieneHijoIzq(aux):
                        sucesor, padreS= self.minimo(izq(aux, self.arbol), aux)
                        aux.dato(sucesor.get_dato())
                    else:
                        sucesor, padreS= self.maximo(der(aux, self.arbol), aux)
                        aux.dato(sucesor.get_dato())
                    self.arbol.remove(sucesor)
                    return True
                if tieneHijoIzq(padre):
                    if tieneHijoIzq(aux):
                        padre.mod_pointers(aux.get_izq(),False)
                    else:
                        padre.mod_pointers(aux.get_der())
                else:
                    if tieneHijoIzq(aux):
                        padre.mod_pointers(aux.get_izq(),False)
                    else:
                        padre.mod_pointers(aux.get_der())
                self.arbol.remove(aux)
            else:
                if min:
                    if aux==padre:
                        if cuentaHijos(der(aux, self.arbol))==1 and tieneHijoDer(der(aux,self.arbol)):
                            sucesor, padreS= self.minimo(der(aux, self.arbol), aux)
                            aux.dato(sucesor.get_dato())
                            self.arbol.remove(sucesor)
                            aux.mod_pointers(sucesor.get_der())
                            return True
                        else:
                            aux.dato(der(aux,self.arbol).get_dato())
                            self.arbol.remove(der(aux, self.arbol))
                            return True
                    sucesor, padreS= self.minimo(der(aux, self.arbol), aux)
                    aux.dato(sucesor.get_dato())
                    if izq(padreS, self.arbol)== sucesor:
                        izq(padreS,self.arbol).mod_pointers(sucesor.get_der())
                    else:
                        der(padreS,self.arbol).mod_pointers(sucesor.get_der())
                self.arbol.remove(sucesor)
                padreS.mod_pointers(None, False)
            return True
        return False
    
    
ini()
arbol= ABB()
cont=0

while True:
    opcion=raw_input("Salir con 'S', Introducir valor por valor 'I', Introducir lista 'L', Eliminar un valor 'E': ")
    if opcion=='S' or opcion=='s':
        break
    elif opcion== 'I' or opcion== 'i':
        valor=raw_input("Introducir valor: ")
        valor.strip()
        if cont==0:
            arbol.insertar(Nodo(), int(valor))
        else:
            arbol.insertar(arbol.get_raiz(), int(valor))
    elif opcion == 'L' or opcion== 'l':
        valor= raw_input("Introducir valores en forma de lista: ")
        valor.strip()
        valor=valor.split()
        if cont==0:
            arbol.insertar(Nodo(), int(valor[0]))
            for j in range(len(valor)-1):
                arbol.insertar(arbol.get_raiz(), int(valor[j+1]))
        else:
            for j in valor:
                arbol.insertar(arbol.get_raiz(), int(j))
    elif opcion== 'E' or opcion== 'e':
        if cont==0:
            print "Que vas a borrar? no tengo nada"
            continue
        valor= raw_input("Introducir valor a eliminar: ")
        valor.strip()
        arbol.sacar(arbol.get_raiz(), int(valor))
    cont+=1
    print arbol
            
        
                     

#Pruebas
'''
arbol= ABB()
arbol.insertar(Nodo(),40)
arbol.insertar(arbol.get_raiz(),30)
arbol.insertar(arbol.get_raiz(),25)
arbol.insertar(arbol.get_raiz(),35)
arbol.insertar(arbol.get_raiz(),50)
arbol.insertar(arbol.get_raiz(),45)
arbol.insertar(arbol.get_raiz(),55)
arbol.insertar(arbol.get_raiz(),53)
arbol.insertar(arbol.get_raiz(),60)
print arbol
arbol.sacar(arbol.get_raiz(),50)
print arbol

print arbol.preO()
print arbol.inO()

arbol.sacar(arbol.get_raiz(),40)


print arbol

arbol.sacar(arbol.get_raiz(),45)
print arbol
'''

    
    
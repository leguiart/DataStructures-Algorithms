#Universidad Nacional Autónoma de México
#Facultad de Ingeniería
#20 de agosto de 2015
#Algoritmos y Estructuras de Datos
#Programa: 01
#Author: @leguiart
import math
#Inicializando listas globales e inmutables a lo largo de la ejecucion del programa
POSIBLES_OPCIONES_NUM=['numerica','Numerica', 'n', 'N', 'num', 'NUMERICA', 'NUM']
POSIBLES_OPCIONES_CIS=['cis', 'Cis','CIS', 'c', 'C']
LST=["Introducir parte real: ","Introducir parte imaginaria: ", "Introducir modulo: ", "Introducir angulo: "]
#Creando clase numero cuyo metodo de inicializacion tomara como argumentos la parte real e imaginaria del numero, creando asi el objeto perteneciente a la clase numero, en la cual habra 
#una mayor conveniencia de operar para los casos de la suma y la resta (en multiplicacion no hay una mayor conveniencia pero mediante esto se puede demostrar numericamente que el producto
#de dos numeros complejos en su forma polar es igual al producto de los módulos por cis de la suma de los argumentos.
#Tambien se incluyen dos metodos dentro de la clase para regresar tanto el modulo del numero como el ángulo, esto con el objetivo de poder crear, en el caso de conversion, un objeto nuevo
#perteneciente a la clase Cis que toma como argumentos modulo y angulo. 
class Numero:
    
    def __init__(self, parte_real, parte_imaginaria):
        self.parte_real=parte_real
        self.parte_imaginaria=parte_imaginaria
        
    def __str__(self):
        return  str(self.parte_real)+"+"+str(self.parte_imaginaria)+"i"
    #método que obtiene el módulo
    def mod(self):
        self.m=math.sqrt(math.pow(self.parte_real,2)+math.pow(self.parte_imaginaria,2))
        return self.m
    #método que obtiene el ángulo
    def ang(self):
        self.angle=math.degrees(math.atan2(self.parte_imaginaria,self.parte_real))
        return self.angle
        
    def num_a_cis(self):
        return str(self.m)+"cis"+str(self.angle)
    #método que efectúa la suma de números complejos
    def suma(self, a, b):
        self.parte_real+=a
        self.parte_imaginaria+=b
    #método que efectúa la resta de números complejos
    def resta (self, a, b):
        self.parte_real-=a
        self.parte_imaginaria-=b
    #método que efectúa la multiplicación de números complejos    
    def multiplicacion(self, a, b):
        c=self.parte_real
        self.parte_real=self.parte_real*a - self.parte_imaginaria*b
        self.parte_imaginaria=self.parte_imaginaria*a + c*b
#Clase de objetos para efectuar las operaciones que son mas convenientes realizar en forma polar y donde también se incluyen dos métodos que esta vez obtienen la parte real y la parte
#imaginaria para operar en la forma numérica o para efectuar la conversión a forma numérica.            
class Cis:
    
    def __init__(self, mod, theta):
        self.mod=mod
        self.theta=theta
        while self.theta<0:
            self.theta+=360
        while self.theta>360:
            self.theta-=360
        
    def __str__(self):
        return str(self.mod)+"cis"+str(self.theta)
    #Método para obtener la parte real del número en forma polar
    def parte_r(self):
        self.r=self.mod*math.cos(math.radians(self.theta))
        return self.r
    #Método para obtener la parte imaginaria del número en forma polar
    def parte_i(self):
        self.i=self.mod*math.sin(math.radians(self.theta))
        return self.i
    
    def cis_a_num(self):
        self.r=self.mod*math.cos(math.radians(self.theta))
        self.i=self.mod*math.sin(math.radians(self.theta))
        return str(self.r)+"+"+str(self.i)+"i"
    #Método para obtener la división que toma como argumentos el módulo y el ángulo del divisor.
    def division(self, modulo, angulo):
        self.mod=self.mod/modulo
        self.theta-=angulo
        while self.theta<0:
            self.theta+=360
    #Método para obtener la potencia que toma como argumento el exponente al cual se elevará el número, se define este metodo en Cis porque la potencia de un numero complejo 
    #es matemáticamente y computacionalmente mas simple al realizarse en su forma polar.
    def potencia(self,exponente):
        self.mod=math.pow(self.mod,exponente)
        self.theta*=exponente        
        while self.theta>360:
            self.theta-=360

#Esta función se define tanto para obtener la forma en la cual se operará, como para crear el objeto correspondiente a la forma elegida           
def numero_nuevo(forma):   
    a=0
    num=[]
    if forma in POSIBLES_OPCIONES_NUM:
        for i in range(2):
            a=raw_input(LST[i])
            a=float(a)
            num.append(a)
        return Numero(num[0],num[1])
          
    elif forma in POSIBLES_OPCIONES_CIS:
        for i in range(2,4):
            a=raw_input(LST[i])
            a=float(a)
            num.append(a)
        return Cis(num[0],num[1])

#Se definen variables globales que podrán ser cambiadas a lo largo de la ejecución del programa, por ello que estén en minúsculas        
num=None
b=0
lst=[]
#Mientras forma sea diferente de 's' (salir) se ejecutará lo que se encuentre dentro del mientras
while True: 
    forma=raw_input("Introducir forma: numerica o cis (para salir (s)): ")
    #si forma es diferente de 's' se continua con la ejecución del programa si no se sale
    if forma=="s":
        break
    #se llama a la función numero_nuevo para crear un nuevo objeto correspondiente ya sea a la clase número en el caso de que la forma se numérica o a la clase cis en el caso de que la
    #forma sea cis, en el caso de que no retorne nada querrá decir que no se introdújo ninguna forma válida o ninguna forma existente por lo que se volverá a pedir al usuario que de
    #una opción valida. Se podrán introducir diferentes comandos para una misma forma, por ello se encuentran las listas inmutables de POSIBLES_OPCIONES_NUM y POSIBLES_OPCIONES_CIS, con
    #todos los posibles comandos para seleccionar cualquiera de las dos formas.
    num=numero_nuevo(forma)
    print num
    if num is None:
        print "Opcion no valida"
        continue
    while True:
        #Se entra a otro ciclo while con el objetivo de que se puedan efectuar diversas operaciones con un mismo numero, de una forma homóloga a la calculadora el comando 's' tendrá 
        #la misma función que el botón 'clear' en una calculadora, de manera que se vuelva a pedir otro número con el cual operar, de lo contrario se seguirá modificando el numero original
        #mediante diversas operaciones.
        operacion=raw_input("Introducir lo que se desea hacer: suma(+), resta(-), division(/), multiplicacion(*), potenciacion(^), modulo(r), conversion(c), salir(s): ")
        if operacion=="s":
            break
        #Aquí se determina como se efectúan las operaciones para las dos distintas formas (numérica o polar)   
        if forma in POSIBLES_OPCIONES_NUM:
            #Sí la forma con la que se está operando es numerica se puede notar que para las operaciones definidas en la clase Numero no existen conversiones a la clase Cis
            #como en la suma, resta y multiplicación, en cambio en la potencia y la división se realizan cambios de clase, lo mismo se cumple para la forma cis solo que en ese caso
            #se hacen conversiones a la clase Número para la suma, resta y multiplicación.
            if operacion!="^" and operacion!="r" and operacion!="c":
                #Si se desean efectuar operaciones que requieran otro número de entrada se crea una lista en la cual el usuario introducirá la parte real e imaginaria
                for i in range(2):                
                    b=raw_input(LST[i])
                    b=int(b)
                    lst.append(b)
                if operacion == "+":
                    num.suma(lst[0],lst[1])
                elif operacion == "-":
                    num.resta(lst[0],lst[1])
                elif operacion == "/":
                    #En este caso se crea un nuevo objeto de nombre num2 con los datos de la lista como argumentos para poder operar la división la cuál esta definida en la clase Cis
                    num=Cis(num.mod(), num.ang())
                    num2=Numero(lst[0],lst[1])            
                    num.division(num2.mod(),num2.ang())
                    num=Numero(num.parte_r(), num.parte_i())
                elif operacion == "*":
                    num.multiplicacion(lst[0],lst[1])
                else:
                    print "Elegir una operacion valida"
                    continue
            else:
                if operacion == "^":
                    num=Cis(num.mod(),num.ang())
                    b=raw_input("Introducir exponente:")
                    b=int(b)
                    num.potencia(b)
                    num=Numero(num.parte_r(), num.parte_i())
                elif operacion == "r":
                    print num.mod()
                elif operacion == "c":
                    #Si se elige efectuar una conversión de forma numerica a cis ello provocará que se tenga que trabajar con forma cis, lo contrario también se cumple.
                    num=Cis(num.mod(), num.ang())
                    forma= "cis"
                    
                  
        elif forma in POSIBLES_OPCIONES_CIS:
            
            if operacion !="^" and operacion !="r" and operacion !="c":
                for i in range(2,4):
                        b=raw_input(LST[i])
                        b=int(b)
                        lst.append(b)
                if operacion == "+" or operacion== "-":
                    #En este caso, como en el caso de la división en forma numérica, se creará otro objeto Cis (num2) con los argumentos de la lista dados por el usuario y posteriormente
                    #la lista se cambiará a tener la parte real y parte imaginaria con el uso de los métodos definidos también en la clase Cis, así como tambien se efectuará una conversión
                    #de num de la clase Cis a la clase Numero.
                    num=Numero(num.parte_r(),num.parte_i())
                    num2=Cis(lst[0],lst[1])
                    lst=[num2.parte_r(),num2.parte_i()]
                    if operacion == "+":
                        num.suma(lst[0],lst[1])
                    else:
                        num.resta(lst[0],lst[1])
                    num=Cis(num.mod(),num.ang())
                elif operacion == "/":
                    num.division(lst[0],lst[1])
                elif operacion == "*":
                    #También en este caso se dan las conversiones de una clase a otra, por las razones ya mencionadas.
                    num=Numero(num.parte_r(),num.parte_i())
                    num2=Cis(lst[0],lst[1])                    
                    num.multiplicacion(num2.parte_r(),num2.parte_i())
                    num=Cis(num.mod(),num.ang())
            else:
                if operacion == "^":
                    b=raw_input("Introducir exponente:")
                    b=int(b)
                    num.potencia(b)
                elif operacion == "r":
                    num=Numero(num.parte_r(),num.parte_i())
                    print num.mod()
                    num=Cis(num.mod(),num.ang())
                elif operacion == "c":
                    num=Numero(num.parte_r(), num.parte_i())
                    forma= "numerica"  
        print num          
        lst=[]
        
#Enlace para entrar al programa en el intérprete en línea
#http://www.codeskulptor.org/#user40_pjo576dBJ5_1.py

#Bonus:
#Juego efectuado para clase en línea de programación interactiva:
#http://www.codeskulptor.org/#user40_pjo576dBJ5_2.py

#Si quieres divertirte un rato programando juegos, se pueden crear muchas cosas con codeskulptor.org 

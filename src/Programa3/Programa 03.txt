#Universidad Nacional Autonoma de Mexico
#Facultad de Ingenieria
#07 de septiembre de 2015
#Algoritmos y Estructuras de Datos
#Programa: 03
#Autores: Eguiarte Morett Luis Andres, Vilchis Martinez Misael, Alvarado de Paz Carlos Fernando, Romo Bravo Katia Fernanda

#Inicializando variables globales, en este caso solo habra una lista con todas las letra del abecedario
LETRAS=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']
#Creando clase Frase de objetos
class Frase:
    #Inicializando los parametros con los que trabajaran los metodos de la clase
    def __init__(self,frase,numero):
        self.frase=[]
        frase=frase.lower()
        self.numero=numero
        #Retirando espacios de la oracion de entrada
        for i in frase:
            if i!=' ':
                self.frase.append(i)
    #Metodo para imprimir           
    def __str__(self):
        s=''
        for i in self.frase:
            s=s+i
        return s
    #Metodo para efectuar la encriptacion del objeto
    def cript(self):
        #Creando una lista local para guardar la copia de la frase original pero encriptada
        frase_cript=[]
        #Ciclo for para iterar en cada uno de los caracteres de la frase
        for i in self.frase:
            #Ciclo for para iterar en cada una de las letras del abecedario
            for j in range(len(LETRAS)):
                #Si la letra actual de la frase es igual a alguna de las letras
                if i==LETRAS[j]:
                    #Sentencia if para el caso de que el indice exceda a la cantidad de elementos en la lista
                    if j+self.numero>len(LETRAS)-1:
                        frase_cript.append(LETRAS[j+self.numero-len(LETRAS)])
                    #Si lo anterior no sucede simplemente se le agrega a la lista con la palabra 
                    #encriptada la letra en el lugar actual mas el numero     
                    else:
                        frase_cript.append(LETRAS[j+self.numero])
                        
        self.frase=frase_cript
    #Metodo para desencriptar    
    def decript(self):
        #Como en el metodo anterior se crea una lista vacia
        frase_decript=[]
        #Ciclo for para iterar en los elementos de la frase
        for i in self.frase:
            #Ciclo for para iterar en cada una de las letras del abecedario
            for j in range(len(LETRAS)):
                #Si la letra actual de la frase es igual a alguna de las letras del abecedario
                if i==LETRAS[j]:
                    #Si el numero de encriptacion es negativo, al querer desencriptar
                    #Sucede lo mismo que en el metodo anterior, el indice se
                    #pasa de algunos elementos que contiene la lista por ello la sig
                    #sentencia if.
                    if j-self.numero>len(LETRAS)-1:
                        frase_decript.append(LETRAS[j-self.numero-len(LETRAS)])
  
                    else:
                        frase_decript.append(LETRAS[j-self.numero])
        self.frase=frase_decript
        
"""

Pruebas     
palabra= Frase("LA CRIPTOGRAFIA ES ROMANTICA", -8)

palabra.cript()
print palabra

palabra.decript()
print palabra


"""
frase=raw_input("Introducir frase:\n")
numero=raw_input("Introducir numero:\n")
numero=int(numero)
palabra= Frase(frase,numero)

print "Encriptacion:"
palabra.cript()
print palabra

print "\nDesencriptacion:"
palabra.decript()
print palabra

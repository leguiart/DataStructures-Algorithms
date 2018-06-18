import random

def ordenamientoInsercion(lista, d): #numeros es una lista
    tama = len(lista) #tamaño de la lista
    i=0
    comp=0
    inter=0
    for i in range(tama):
        indice = lista[i]
        inter+=1
        a = i-1
        while (a >= 0 and lista[a][d] > indice[d]):
            comp+=1
            lista[a+1] = lista[a]
            inter+=1
            a = a-1
        lista[a+1] = indice
        inter+=1
    return (lista,comp,inter)	

def lista_aleatorios(n): 
    lista = [0] * n
    for i in range(n):
        lista[i] = random.randrange(1000)
    return lista


def division(lista, sup):
    aux=[]
    lugares=0
    while True:
        sup=sup/10
        lugares+=1
        if sup==0:
            break
    for i in range(len(lista)):
        for j in range(lugares):
            n=(float(lista[i])/(10**(j+1))-lista[i]/(10**(j+1)))*10
            aux.append(int(n))
        lista[i]=aux
        aux=[]
    return lista

def ordenamientoRadix(lista, d):
    for i in range(d):
        ordenamientoInsercion(lista, i)
    for i in range(len(lista)):
        lista[i].reverse()
    return lista


l=lista_aleatorios(10)

print l
                       
division(l,1000)

print l

ordenamientoRadix(l,4)

print l



def st(lst):
    s=''
    for i in range(len(lst)):
        s=s+str(lst[i])
    return s

def bin(dec,n):
    b=list()
    sign=False
    if dec<0:
        sign=True
        dec=dec*-1
    while dec!=0:
        if dec%2==0:
            b.append(0)
        else:
            b.append(1)
        dec=dec/2
    while len(b)<n-1:
        b.append(0)
    if sign:
        b.append(1)
    else:
        b.append(0)
    b.reverse()
    return b

def comp1(dec,n):
    lista=[]
    for i in range(len(bin(dec,n))):
        if i>0:
            if bin(dec,n)[i]==0:
                lista.append(1)
            else:
                lista.append(0)
        else:
            lista.append(bin(dec,n)[i])
    return lista

def comp2(dec,n):
    lista=bin(dec,n)
    lista.reverse()
    a=[]
    switch=False
    cont=0
    for i in lista:
        if cont==len(lista)-1:
            a.append(i)
            break
        if switch:
            if i==0:
                a.append(1)
            elif i==1:
                a.append(0)
        else:
            a.append(i)
        if i==1:
            switch=True
        cont+=1
    a.reverse()
    return a


d=raw_input("Introducir numero decimal: ")
n=raw_input("Introducir numero de bits: ")
print "\nSigno y magnitud:"
print st(bin(int(d),int(n)))
print "\nComplemento a 1:"
print st(comp1(int(d),int(n)))
print "\nComplemento a 2:"
print st(comp2(int(d),int(n)))

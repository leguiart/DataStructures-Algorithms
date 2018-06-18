from math import *
#expresion aritmetica en RPN
def evalua(expresion):
    data = expresion.split()
    dicOperacionBin={'+':'+','-':'-','/':'/', '^':'**', 'res':'%'}
    dicOperacionUno=("raiz":"sqrt", "cos":"cos", "sen":"sin"}
    operandos = list()
    for s in data:
        if s in dicOperacionBin:
            operandos.append(str(evalua(operandos.pop()+dicOperacionBin[s]+operandos.pop())))
        elif s in dicOperacionUno:
            operandos.append(str( evalua(dicOperacionUno[s]+'('+operandos.pop()+')')))
        else:
            operandos.append(s)
    return data, operandos
    
print evalua("2 3 ^")


 
    

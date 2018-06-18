def bin_dec(b):
    dec=0
    b.reverse()
    for i in range(len(b)):
        if b[i]=='1':
            dec+=2**int(i)
    return dec

def bin_float(bin):
    ent=[]
    flo=[]
    if bin[0]=='0':
        bin = bin.lstrip('0')
        signo=0
    else:
        bin= bin.lstrip('1')
        signo=1
    for i in range(len(bin)):
        if i<=7:
            ent.append(bin[i])
        else:
            flo.append(bin[i])
    s=bin_dec(flo)
    while s>1:
        s=s*.1
    return ((-1)**signo)*(2**(bin_dec(ent)-127))*(1+s)


print bin_float("11010010110001010101001010101010")
print bin_float("01011010100101011101001011101010")
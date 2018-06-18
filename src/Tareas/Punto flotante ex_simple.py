def bin_dec(b):
    dec=0
    b.reverse()
    for i in range(len(b)):
        if b[i]=='1':
            dec+=2**i
    return dec

def mantisa(f):
    sum=1
    for i in range(len(f)):
        if f[i]=='1':
            sum+=float(1)/(2**(i+1))
    return sum

def bin_float(bin):
    ent=[]
    flo=[]
    li=[]
    for i in bin:
        li.append(i)
    if li[0]=='0':
        signo=0
    else:
        signo=1
    li.pop(0)
    for i in range(len(li)):
        if i<=7:
            ent.append(li[i])
        else:
            flo.append(li[i])
    return ((-1)**signo)*(2**(bin_dec(ent)-127))*(mantisa(flo))


print bin_float("11010010110001010101001010101010")
print bin_float("01011010100101011101001011101010")

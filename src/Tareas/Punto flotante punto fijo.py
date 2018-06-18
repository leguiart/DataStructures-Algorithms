import math

def bin_a_dec(a, sign):
    a=a.split('.')
    b=a[0]
    c=a[1]
    lst=[]
    sum=0
    for i in b:
        lst.append(i)
    lst.reverse()
    if sign:
        for i in range(len(lst)):
            if lst[i]=='1' and i<len(lst)-1:
                sum=sum+2**i
    else:
        for i in range(len(lst)):
            if lst[i]=='1':
                sum=sum+2**i
            
    for i in range(len(c)):
        if c[i]=='1':
            sum=sum+2**((i+1)*-1)
    lst.reverse()
    if sign:
        if lst[0]=='1':
            return sum*-1
        else:
            return sum
    else:
        return sum

print bin_a_dec('1011010101.100101', False)
print bin_a_dec('1011010101.100101', True)
print bin_a_dec('0100101010.100011', False)
print bin_a_dec('0100101010.100011', True)



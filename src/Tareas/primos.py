import math

def main():
    lst=list()
    count = 3
    
    while count<1000000:
        isprime = True
        if count%2!=0:
            for x in range(2, int(math.sqrt(count) + 1)):
                if count % x == 0: 
                    isprime = False
                    break
            
            if isprime:
                lst.append(count)
        
        count += 1
        
    return lst
        
print main()

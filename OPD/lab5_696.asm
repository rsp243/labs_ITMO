org 0x205
R:      word 0x5af
W:      word 0x00
STOP:   word 0x0d
START:  CLA
S1:     IN 7
        AND #0x40
        BZS S1
        IN 6
        ST W
        ST (R)
        CMP STOP
        BZS HALT
        CLA
S2:     IN 7
        AND #0x40
        BZS S2
        IN 6
        SWAB
        OR (R)
        ST (R)+
        SWAB
        SUB W
        SWAB
        BZS HALT
        JUMP S1        
HALT:   HLT
        

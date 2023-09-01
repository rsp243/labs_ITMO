org 0x11a
R:      word 0x55b
W:      word 0x00
STOP:   word 0x00
START:  CLA
S1:     IN 5
        AND #0x40
        BZS S1
        IN 4
        SWAB
        ST W
        ST (R)
        CMP STOP
        BZS HALT
        CLA
S2:     IN 5
        AND #0x40
        BZS S2
        IN 4
        OR (R)
        SWAB
        ST (R)+
        SWAB
        SUB W
        BZS HALT
        JUMP S1        
HALT:   HLT

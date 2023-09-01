
org 0x10
T:      WORD 0x0
M:      WORD 0x2
N:      WORD 0x5
PP:     WORD 0x666
R:      WORD 0xFFFF
ADDR:   WORD 0x0
        CLA
ARRPP:  LD (PP)+
        ST ADDR
        LD N
        ST T
ARRV:   LD (ADDR)+
        CMP R
        BNS BR1
        ST R
BR1:    LOOP T
        JUMP ARRV
        LOOP M
        JUMP ARRPP
        HLT

org 0x666
        WORD 0x700
        WORD 0x750

org 0x700
        WORD 0x01
        WORD 0x02
        WORD 0x55
        WORD 0x77
        WORD 0x03

org 0x750
        WORD 0x01
        WORD 0x02
        WORD 0x02
        WORD 0x02
        WORD 0x02
        
        

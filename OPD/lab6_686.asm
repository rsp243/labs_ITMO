        ORG 0x0
V0:     WORD $DEFTL, 0x180
V1:     WORD $INTR1, 0x180
V2:     WORD $INTR2, 0x180
V3:     WORD $DEFTL, 0x180
V4:     WORD $DEFTL, 0x180
V5:     WORD $DEFTL, 0x180
V6:     WORD $DEFTL, 0x180
V7:     WORD $DEFTL, 0x180
DEFTL:  IRET
        
        ORG 0x021
X:      WORD 0x020

START:  DI
        OUT 0x1
        LD #0x9
        OUT 0x3
        LD #0xA
        OUT 0x5
        CLA
        OUT 0x7
        OUT 0xB
        OUT 0xE
        OUT 0x12
        OUT 0x16
        OUT 0x1A
        OUT 0x1E

PROG:
DEC_LP:        DI
        LD X        
        DEC
        CALL STX
        EI
        NOP                     ;Точка останова №0
        JUMP DEC_LP
       

INTR1:    NOP                   ;Точка останова №1
        PUSH
        LD X
        ASL
        NEG
        INC                     ;Вычисление F(X) = 2 * X * (-1) + 1
        out 2
        POP
        NOP                     ;Точка останова №2
        IRET
                
INTR2:    NOP                   ;Точка останова №3
        PUSH
        IN 4
        SXTB
        ADD X
        CALL CH_STX
        POP
        NOP                     ;Точка останова №4
        IRET
        
CH_STX:    CMP MINX
        BZS LDMAXX
        CMP MAXX
        BZC LDMAXX
        JUMP STX
LDMAXX: LD MAXX
STX:    ST X
        RET
        
MAXX:   WORD 0x3F
MINX:   WORD -0x3F

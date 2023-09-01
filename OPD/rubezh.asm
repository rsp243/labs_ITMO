        ORG 0x10
ARRV:   WORD 0x687
M:      WORD 0x3                ;Столбцы
N:      WORD 0x3                ;Строки
        
ARRP:   WORD ?
I:      WORD 0x1
J:      WORD 0x1
ADDR:   WORD ?

RESULT: WORD 0x0
        
CONST1: WORD 0x5BB7
CONST2: WORD 18457              ;В десятичной СС

START:  CLA
        LD ARRV
        ST ADDR
NEXT:   LD (ADDR)+
        ST ARG
        CALL CHECK_INDEX
        LD J
        CMP N
        BZS INC_I
        INC
        ST J
        JUMP NEXT
INC_I:  LD #0x1
        ST J
        LD I
        CMP M
        BZS HALT
        INC
        ST I
HALT:   HLT
        


XOR_PART:      WORD ?
XOR:    PUSH
        LD RESULT
        NOT
        AND &0
        ST XOR_PART
        POP
        NOT
        AND RESULT        
        OR XOR_PART
        ST RESULT
        RET
        
        
CHECK_INDEX:
        LD I
DIV:    SUB #0x3
        BZS CHECK_JINDEX
        BNC DIV
        RET

CHECK_JINDEX:
        LD J
        ROR
        BCC CALCULATE
        RET
CALCULATE:
        CALL FUNC
        RET
        

ARG:    WORD ?
FUNC:   LD ARG
        ASL
        BVS ERR
        ADD ARG
        BVS ERR
        RET
ERR:    LD ERR
        RET

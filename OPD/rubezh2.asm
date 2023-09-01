        ORG 0x10
M:      WORD 0x3
N:      WORD 0x5

I:      WORD 0x1                     ;Столбцы
J:      WORD 0x1                     ;Строки
        
ARR_POINTER:    WORD 0x70
ADDR:   WORD ?
ARG:    WORD ?
RESULT: WORD 0x0
COUNTER:        WORD 0x0
        
START:  CLA
NEW_ARRAY_VAL:      LD (ARR_POINTER)+
        ST ADDR
NEW_VALUE:      LD (ADDR)+
        ST ARG
        CALL FUNC
        ADD RESULT
        ST RESULT

INC_J:  LD J
        CMP N
        BZS INC_I
        INC
        ST J
        LD COUNTER
        INC
        ST COUNTER
        JUMP NEW_VALUE

INC_I:  LD I
        
        CMP M
        BZS HALT
        INC
        ST I
        LD #0x0
        ST J
        JUMP NEW_ARRAY_VAL

HALT:   HLT
        
FUNC:   LD ARG
        ASL
        ADD ARG
END__FUNC:       RET

   
        org 0x70
        WORD 0x75
        WORD 0x81
        WORD 0x87

        org 0x75
        WORD 0x1
        WORD 0x1
        WORD 0x1
        WORD 0x1
        WORD 0x1

        org 0x81
        WORD 0x1
        WORD 0x1
        WORD 0x1
        WORD 0x1
        WORD 0x1

        org 0x87
        WORD 0x1
        WORD 0x1
        WORD 0x1
        WORD 0x1
        WORD 0x1

        org 0x10
K:      WORD 0x3                ;length of ARRPPP
M:      WORD 0x3                ;length of ARRPP
N:      WORD 0x3                ;length of ARRV
Q:      WORD 0x0                ;Number for loop to go through ARRV
T:      WORD 0x0                ;Number for loop to go through ARRPP
PPP:    WORD 0x300
PP:     WORD 0x0
ADDR:   WORD 0x0                ;Pointer for current value in ARRV
R:      WORD 0xFFFF
        
        CLA
ARRPPP: LD (PPP)+
        ST PP
        LD M
        ST Q
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
        LOOP Q
        JUMP ARRPP
        LOOP K
        JUMP ARRPPP
        HLT

        org 0x300               ;PPP
        WORD 0x370
        WORD 0x380
        WORD 0x390

        org 0x370               ;PP1
        WORD 0x400
        WORD 0x405
        WORD 0x40A

        org 0x380               ;PP2
        WORD 0x410
        WORD 0x415
        WORD 0x41A

        org 0x390               ;PP3
        WORD 0x420
        WORD 0x425
        WORD 0x42A

        org 0x400               ;V1
        WORD 0x1
        WORD 0x4
        WORD 0x88

        org 0x405               ;V2
        WORD 0x0
        WORD 0x100
        WORD 0x111

        org 0x40A               ;V3
        WORD 0x111
        WORD 0x111
        WORD 0x111

        org 0x410               ;V4
        WORD 0x1
        WORD 0x4
        WORD 0x88

        org 0x415               ;V5
        WORD 0x0
        WORD 0x100
        WORD 0x111

        org 0x41A               ;V6
        WORD 0x111
        WORD 0x111
        WORD 0x112

        org 0x420               ;V7
        WORD 0x1
        WORD 0x4
        WORD 0x88

        org 0x425               ;V8
        WORD 0x0
        WORD 0x100
        WORD 0x111

        org 0x42A               ;V9
        WORD 0x111
        WORD 0x111
        WORD 0x111

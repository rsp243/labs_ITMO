     ORG 0x4C0
T1F:    WORD ?                  ;Test1 Flag
T2F:    WORD ?                  ;Test2 Flag
T3F:    WORD ?                  ;Test3 Flag
ALF:    WORD ?                  ;ALl Flags

        ORG 0x4CD
START:  CALL TEST1
        CALL TEST2
        CALL TEST3

        LD T1F
        AND T2F
        AND T3F
        ST ALF
        HLT

T1V:    WORD 0xFFFF             ;Test1 Value
T1RP:   WORD 0x666              ;Test1 Result cell Pointer
T1CV:   WORD 0x0001             ;Test1 Correct result Value 
TEST1:  LD T1V
        WORD 0x9666
        LD T1CV
        CMP (T1RP)
        BZS FL1T1               ;FLag for Test 1 = 1 (Right Result)
        LD #0
        JUMP STF1
FL1T1:  LD #1
STF1:   ST T1F
        RET
        
T2V:    WORD 0x07FF             ;Test1 Value
T2RP:   WORD 0x132              ;Test1 Result cell Pointer
T2CV:   WORD 0xF801             ;Test1 Correct result Value 
TEST2:  LD T2V
        WORD 0x9132
        LD T2CV
        CMP (T2RP)
        BZS FL1T2               ;FLag for Test 2 = 1 (Right Result)
        LD #0
        JUMP STF2
FL1T2:  LD #1
STF2:   ST T2F
        RET

T3V:    WORD 0xFFF1             ;Test1 Value
T3RP:   WORD 0x0AA              ;Test1 Result cell Pointer
T3CV:   WORD 0x000F             ;Test1 Correct result Value 
TEST3:  LD T3V             
        WORD 0x90AA
        LD T3CV
        CMP (T3RP)
        BZS FL1T3               ;FLag for Test 3 = 1 (Right Result)
        LD #0
        JUMP STF3
FL1T3:  LD #1
STF3:   ST T3F
        RET

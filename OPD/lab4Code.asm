org 0x694

START:  LD &1
BEQ SR1
BNC SR2
SR1:    SUB M 
BNS SR3
JUMP J1
SR3:    ADD M
SR2:    ADD &1
ADD &1
SUB V
JUMP J2
J1:     LD M
J2:     ST &1
RET
M:      word 0xF4A9
V:      word 0x0054

org 0x3AD
CLA
ST R
LD Y
PUSH
CALL $START
POP
SUB R
ST R
LD Z
PUSH
CALL $START
POP
SUB R
ST R
LD X
DEC
PUSH
CALL $START
POP
SUB R
ST R
HLT
Z:      word 0xFEED
Y:      word 0x90D
X:      word 0xD1ED
R:      word 0xF4A9

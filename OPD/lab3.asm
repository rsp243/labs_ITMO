org 0x5B7
X:word 0x0400
Y:word 0xA000 
Z:word 0x4000 
R:word 0x0010  

START: 
CLA
ST R
LD #5
ST Z
LD X
ST Y
ZL:LD (Y)+
ROR
BCS K
ROL
SUB (R)+
K:LOOP Z
JUMP ZL
HLT
org 1024
word 0xED9E
word 0x60CC
word 0xDEAF
word 0xF666
word 0xD10D
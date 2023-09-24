global _start

section .data
teststring: db "abcdef", 10
        
section .text
string_length:
        xor rax, rax            ; rax - total length of string. Set up zero to rax

.loop:
        cmp byte[rdi + rax], 0  ; if byte of [rdi + rax] is equal zero-symbol go to .end
        ; (rdi - address of first letter of string)
        je .end

        inc rax                 ; else set up rax with rax + 1, go to .loop
        jmp .loop

.end:
        ret        

print_string:
        push rax
        call string_length
        mov rdx, rax
        mov rax, 1              ; 'write' syscall number
        mov rsi, rdi            ; string address
        mov rdi, 1              ; stdout descriptor
        ; mov rdx, 7            ; string length in bytes
        syscall
        pop rax
        ret

_start:
        mov rdi, teststring
        call print_string
        
        xor rdi, rdi
        mov rax, 60
        syscall

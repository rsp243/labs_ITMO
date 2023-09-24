global _start

section .data
teststring: db "abcdef", 0
        
section .text
strlen:
        xor rax, rax            ; rax - total length of string. Set up zero to rax

.loop:
        cmp byte[rdi + rax], 0  ; if byte of [rdi + rax] is equal zero-symbol go to .end
        ; (rdi - address of first letter of string)
        je .end

        inc rax                 ; else set up rax with rax + 1, go to .loop
        jmp .loop

.end:
        ret


_start:
        mov rdi, teststring
        call strlen
        mov rdi, rax

        mov rax, 60
        syscall

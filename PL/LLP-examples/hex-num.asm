section .data
new_line_chr: db 10
codes: db '0123456789ABCDEF'
demo1: dq 0x11223347788
demo2: db 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88
        
section .text
global _start

        ; function print_newline print newline into stdout file
print_char:
        push rdi               ; rdi -> stack
        mov rax, 1             ; 'write' syscall number
        mov rdi, 1             ; stdout descriptor
        mov rsi, rsp           ; take pointer of symbol's code to write
        mov rdx, 1             ; 1 - count of bytes to write
        syscall
        pop rdi
        ret

; Принимает указатель на нуль-терминированную строку, возвращает её длину
string_length:
        xor rax, rax ; rax - total length of string. Set up zero to rax
.loop:
        cmp byte[rdi + rax], 0 ; if byte of [rdi + rax] is equal zero-symbol go to .end
        ; (rdi - address of first letter of string)
        je .end                 
        inc rax            ; else set up rax with rax + 1, go to .loop
        jmp .loop
.end:
        ret

; Принимает указатель на нуль-терминированную строку, выводит её в stdout
print_string:
        push rdi
        call string_length
        pop rsi
        mov rdx, rax            ; string length in bytes
        mov rax, 1              ; 'write' syscall number
        mov rdi, 1              ; stdout descriptor
        syscall
        ret
        
; Переводит строку (выводит символ с кодом 0xA)
print_newline:
        mov rdi, 0xA
        call print_char
        ret

print_newline1:
        mov rax, 1              ; write syscall identifier
        mov rdi, 1              ; stdout file descriptor
        mov rsi, new_line_chr   ; take new_line_chr
        mov rdx, 1              ; the amount of bytes to write
        syscall
        ret

print_hex:
        mov rax, rdi            ; take the first arg passed in function
        mov rdi, 1              ; stdout file descriptor
        mov rdx, 1              ; the amount of bytes to write
        mov rcx, 64             ; rcx - counter of rest bits to transfer 

iterate:
        push rax
        sub rcx, 4              ; take the first tetrade in 4 bits from the junior word
        sar rax, cl             ; cl - the smallest part of rcx
        ; sar - shift on cl value
        and rax, 0xf            ; clear all bits except the lowest four
        lea rsi, [codes + rax]  ; upload to rsi appropriate character or hex num
        mov rax, 1
        push rcx
        syscall                 ; write part of hex number
        
        pop rcx
        pop rax                 ; recover rax decimal value
        test rcx, rcx           ; if rcx is 0?
        jnz iterate

        ret                     ; if rcx - counter of rest bits to transfer is 0, ret


print_uint:
        push rdi
        xor rcx, rcx
        
.loop_devide:
        mov rax, rdi
        mov bl, 10
        div bl
        push dx
        inc rcx
        test rdi, rdi
        jnz .loop_devide
        
.assume_string:
        xor rax, rax
        pop r8
        add r8, 48
        mov rax, r8
        
        dec rcx
        test rcx, rcx
        jnz .assume_string

        pop rdi

        mov rdi, rax
        call print_string
        
        ret

        
_start:
        mov rdi, [demo1]
        call print_uint
        call print_newline

        mov rax, 60             ; invoke exit syscall
        xor rdi, rdi
        syscall

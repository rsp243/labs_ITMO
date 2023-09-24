; print_uint.asm
section .data
message1:
        db 'Hello2121', 0
message2:
        db 'Hello', 0
        
section .text
global _start

        
; Принимает код символа и выводит его в stdout
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
        cmp byte [rdi + rax], 0 ; if byte of [rdi + rax] is equal zero-symbol go to .end
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


; Выводит знаковое 8-байтовое число в десятичном формате
print_int:
        cmp rdi, 0
        jns print_uint         ; if number is unsigned, print it!
        push rdi               ; else store rdi to stack and
        mov rdi, 0x2D
        call print_char         ; print '-'
        pop rdi
        neg rdi
                
; Выводит беззнаковое 8-байтовое число в десятичном формате 
; Совет: выделите место в стеке и храните там результаты деления
; Не забудьте перевести цифры в их ASCII коды.        
print_uint:
        dec rsp                 ; allocate memory in stack
        mov byte [rsp], 0
        mov r8, 1               ; count of bytes
        mov rax, rdi
        mov rcx, 10
.loop_devide:
        inc r8                  ; increment count of bytes
        xor rdx, rdx
        div rcx                ; devision
        add rdx, '0'             ; to ASCII
        dec rsp                 
        mov [rsp], dl          ; store to stack
        test rax, rax           ; is the number 0?
        jnz .loop_devide
.print:
        mov rdi, rsp            ; pointer to first letter
        push r8                 ; store to stack r8 - count of bytes
        call print_string
        pop r8
        add rsp, r8             ; restore rsp
        ret

strings_equal:
        push rdi
        push rsi        
        call string_length
        mov rdx, rax
        mov rdi, rsi
        call string_length
        cmp rax, rdx
        jnz .return_zero
        pop rsi
        pop rdi
        xor rcx, rcx
.loop_on_string:
        dec rax
        mov r9b, [rdi + rcx]
        mov r10b, [rsi + rcx]    
        cmp r9, r10
        jnz .return_zero
        inc rcx
        test rax, rax
        jnz .loop_on_string
        mov r8, 1
        jmp .return_result
.return_zero:
        mov r8, 0
        jmp .return_result
.return_result:
        mov rax, r8
        ret

string_equals:
        xor rcx, rcx
.loop_on_string:
        mov r9b, [rdi + rcx]
        mov r10b, [rsi + rcx]    
        cmp r9b, r10b
        jnz .return_zero
        cmp r9b, 0
        jz .return_one
        inc rcx
        jmp .loop_on_string
.return_one:
        mov rax, 1
        jmp .return_result
.return_zero:
        mov rax, 0
.return_result:
        ret 
               
_start:
        mov rdi, message1
        mov rsi, message2
        call string_equals

        mov rdi, rax
        add rdi, '0'
        call print_char
        
        mov rax, 60
        xor rdi, rdi
        syscall

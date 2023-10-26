%define WRITE_SYSCODE 1
%define STDOUT 1       
%define EXIT_SYSCODE 60        
%define STDERR 2
        
global _start

section .text

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
        mov rsi, STDOUT         ; stdout descriptor

; Принимает указатель на нуль-терминированную строку, выводит её в переданный ей descriptor (2 param)
print_string_with_cust_descr:
        push rsi
        push rdi
        call string_length
        pop rsi
        pop rdi
        mov rdx, rax            ; string length in bytes
        mov rax, WRITE_SYSCODE  ; 'write' syscall number
        syscall
        ret

; Читает один символ из stdin и возвращает его. Возвращает 0 если достигнут конец потока
read_char:
        sub rsp, 8
        xor rax, rax            ; 'read' syscall number
        xor rdi, rdi            ; stdin decriptor
        mov rsi, rsp            ; take pointer of symbol code to read
        mov rdx, 1              ; 1 - count of bytes to read
        syscall
        test rax, rax
        jle .stream_end
        mov al, [rsp]
.stream_end:
        add rsp, 8
        ret

; Принимает: адрес начала буфера, размер буфера
; Читает в буфер слово из stdin, пропуская пробельные символы в начале, .
; Пробельные символы это пробел 0x20, табуляция 0x9 и перевод строки 0xA.
; Останавливается и возвращает 0 если слово слишком большое для буфера
; При успехе возвращает адрес буфера в rax, длину слова в rdx.
; При неудаче возвращает 0 в rax
; Эта функция должна дописывать к слову нуль-терминатор
read_word:
        push rbx
        xor rax, rax
        xor rdx, rdx
        xor rbx, rbx
        push rdx
        push rsi
        push rdi
.readloop:
        call read_char
        test rax, rax           ; if we had taken the end of stream
        jz .return_success
        jl .return_fail
        cmp rax, ` `
        jz .skip_char
        cmp rax, `\t`
        jz .skip_char
        cmp rax, `\n`
        jz .skip_char        
.check_length:
        cmp rbx, [rsp + 8]
        jge .return_fail        
.store_char:
        pop rdi
        mov byte [rdi + rbx], al
        push rdi
        inc rbx
        jmp .readloop
.skip_char:
        test rbx, rbx
        jnz .return_success
        jmp .readloop
.return_success:
        pop rdi
        mov byte [rdi + rbx], 0
        mov rax, rdi
        mov rdx, rbx
        jmp .popping_n_return
.return_fail:
        pop rdi
        mov rdx, rbx
        xor rax, rax
.popping_n_return:
        pop rsi
        pop rdx
.return:
        pop rbp
        ret


string_copy:
        push rsi
        push rdx
        call string_length
        pop rdx
        pop rsi
        cmp rax, rdx
        ja .return_zero
        mov r8, rax
        xor rax, rax
.loop:
        mov r9b, [rdi + rax]
        mov [rsi + rax], r9b
        inc rax
        cmp rax, r8
        jz .return
        jmp .loop
.return_zero:
        xor rax, rax
.return:
        ret
        
_start:
        sub rsp, 256            ; memory allocating
        mov rdi, rsp
        mov rsi, 255
        call read_word
        mov rdi, rax
        call print_string
        
        mov rax, 60
        xor rdi, rdi
        syscall

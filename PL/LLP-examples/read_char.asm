global _start

section .text

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
        
; Читает один символ из stdin и возвращает его. Возвращает 0 если достигнут конец потока
read_char:
        dec rsp
        xor rax, rax            ; 'read' syscall number
        xor rdi, rdi            ; stdin decriptor
        mov rsi, rsp            ; take pointer of symbol code to read
        mov rdx, 1              ; 1 - count of bytes to read
        syscall
        mov rax, [rsp]
        inc rsp
        ret

read_word:
        xor rcx, rcx
        xor rax, rax
.readloop:
        push rdi
        push rsi
        push rcx
        call read_char
        pop rcx
        pop rsi
        pop rdx
        test rax, rax           ; if we had taken the end of stream
        jz .return_success
        cmp rax, 0x20
        jz  .skip_char
        cmp rax, 0x9
        jz .skip_char
        cmp rax, 0xA
        jz .skip_char        
        cmp rcx, rsi
        jge .return_fail
        
.store_char:
        mov byte [rdi + rcx], al
        inc rcx
        jmp .readloop
        
.skip_char:
        test rcx, rcx
        jz .readloop
        
.return_success:
        mov byte [rdi + rcx], 0
        mov rax, rdi
        mov rdx, rcx
        jmp .return
.return_fail:
        xor rax, rax
.return:
        ret
 
parse_uint:
        xor rax, rax
        xor rdx, rdx
        xor r8, r8
        mov r9, 10
.loop:
        mov r8b, byte [rdi + rdx]
        cmp r8d, '0'
        jb .return
        cmp r8b, '9'
        ja .return
        sub r8b, '0'
        mul r9
        add rax, r8
        inc rdx
        jmp .loop
.return:
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
        mov rdi, 0x3333
        mov rsi, 5
        call read_word
        
        mov rax, 60
        xor rdi, rdi
        syscall

#include <stddef.h>
#include <stdio.h>
#include <inttypes.h>
#include <malloc.h>


// Functions for more comfortable reading from stdin
int64_t read_int64();
size_t read_size();

// Fill the array with int64_t, while the size is not over 
void array_int_fill( int64_t* array, size_t size ) {
    for (size_t i = 0; i < size; i++) {
        array[i] = read_int64();
    }
}

// Read *size and allocate memory to array with got *size 
// (consider that we need int64_t type in array and need to malloc(*size * 64)) 
int64_t* array_int_read( size_t* size ) {
    *size = read_size();
    int64_t* array = malloc(*size * 64);
    if (array) {
        array_int_fill(array, *size);
    }
    return array;
}
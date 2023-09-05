#include <stdio.h>
#include <stddef.h>
#include <inttypes.h>
#include <malloc.h>


// Functions for more comfortable reading from stdin
int64_t read_int64() {
    int64_t array_int;
    scanf("%" SCNd64, &array_int);
    return array_int;
}

size_t read_size() {
    size_t size_of_array;
    scanf("%zu", &size_of_array);
    return size_of_array;
}

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
    int64_t* array = malloc(*size * sizeof(int64_t));
    if (array) {
        array_int_fill(array, *size);
    }
    return array;
}

int64_t* array_int_min( int64_t* array, size_t size) {
    int64_t* pointer_to_min = 0;
    int64_t min = INT64_MAX;
    for (size_t i = 0; i < size; i++) {
        if (min > array[i]) {
            min = array[i];
            pointer_to_min = array + i;
        }
    }
    return pointer_to_min;
}

// Выводит None если x == NULL, иначе число, на которое указывает x.
void intptr_print( int64_t* x ) {
    if (x == NULL) {
        printf("None");
    } else {
        printf("%" PRId64, *x);
    }
}

void perform() {
    size_t size = 0;
    int64_t* array = array_int_read(&size);
    int64_t* pointer_to_min = array_int_min(array, size);
    intptr_print(pointer_to_min);
    free(array);
}

int64_t** marray_read( size_t* rows, size_t** sizes ) {
    return;
}

void marray_print(int64_t** marray, size_t* sizes, size_t rows) {
    for( size_t i = 0; i < rows; i++ ) {
        array_int_print( marray[i], sizes[i] );
        print_newline();
    }
}

int main(int argc, char const *argv[])
{
    size_t rows = read_size();
    size_t* sizes_array = malloc(rows * sizeof(size_t));
    marray_read(rows, &sizes_array);
    return 0;
}

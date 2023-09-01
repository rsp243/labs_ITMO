#include <stddef.h>
#include <stdio.h>
#include <inttypes.h>


// Functions for more comfortable reading from stdin
int64_t read_int64() {
    int64_t array_int;
    scanf("%" SCNd64, array_int);
    return array_int;
}

size_t read_size() {
    size_t size_of_array;
    scanf("%zu", size_of_array);
    return size_of_array;
}

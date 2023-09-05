#include <stddef.h>
#include <stdio.h>
#include <inttypes.h>


size_t count_zeroes( void const* data, const size_t sz ) {
    size_t result = 0;
    for (size_t i = 0; i < sz; i++) {
        if (*((int8_t*) (data + i)) == 0) {
            result++;
        }
    }
    return result;
}

int main(int argc, char const *argv[])
{
    int data[5] = {0, 1, 2, 3, 4};
    printf("%lu", count_zeroes(data, 20));
    return 0;
}

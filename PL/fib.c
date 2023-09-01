void array_fib(int* array, int* limit) {
    int size = limit - array;
    if (size) {
        if (size > 0) {
            *array = 1;
            if (size > 1) {
                array[1] = 1;
                for (int i = 2; i < size; i++) {
                    array[i] = array[i - 1] + array[i - 2];
                }
            }
        } 
    }
}


int main(int argc, char const *argv[])
{
    int array[5] = {0};
    array_fib(array, array + 5);
    for (int i = 0; i < 5; i++) {
        printf("%d ", array[i]);
    }
    return 0;
}

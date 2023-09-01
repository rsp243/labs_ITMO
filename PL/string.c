int is_whitespace(char c) { return c == ' ' || c == '\t' || c == '\n'; }

int string_count(char* str) {
    int count = 0; 
    while (*(str + count) != 0) {
        count++;
    }
    return count;
}

int string_words(char* str)  {
    int curr = 0; 
    int count = 0;
    int is_prev_whitespace = 1;
    while (*(str + curr) != 0) {
        if (is_prev_whitespace && !is_whitespace(*(str + curr))) {
            count++;
            is_prev_whitespace = 0;
        } 
        if (is_whitespace(*(str + curr))) {
            is_prev_whitespace = 1;
        }
        curr++;
    }
    return count;
}

int main(int argc, char const *argv[])
{
    char string[] = {'H', 'e', ' ', 'l', 'l', ' ', 'o', 0};
    printf("%d ", string_count(string));
    printf("%d", string_words(string));
    return 0;
}

#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>

#define MAX_LEN 30

int main(int argc, char** args) {
    int fd;
    char str[MAX_LEN] = "";
    if (argc != 1)
        strcat(str, args[1]);
    fd = open("/proc/my_netstat_module", O_WRONLY);
    if (fd < 0) {
        perror("Failed to open /proc/my_netstat_module");
        return 1;
    }

    if (write(fd, str, strlen(str)) < 0) {
        perror("Failed to write to /proc/my_netstat_module");
        close(fd);
        return 1;
    }

    close(fd);
    printf("%s\n", "Done!");
    return 0;
}
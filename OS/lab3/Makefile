MODULE_NAME := my_netstat_module
USER_NAME := my_netstat

KERNEL_DIR := /lib/modules/$(shell uname -r)/build

obj-m += $(MODULE_NAME).o
$(MODULE_NAME)-objs := src/$(MODULE_NAME).o

PWD := $(CURDIR)

all: module
	rm -rf $(shell find . -type f \( -name "*.cmd" -o -name "*.o" -o -name "Module.symvers" -o -name "modules.order" -o -name "*.mod*" \))

module: user
	make -C $(KERNEL_DIR) M=$(PWD) modules

user:
	gcc -o $(USER_NAME) src/$(USER_NAME).c

clean:
	make -C $(KERNEL_DIR) M=$(PWD) clean

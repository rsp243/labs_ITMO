#include <linux/init.h>
#include <linux/seq_file.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/proc_fs.h>
#include <linux/uaccess.h> 
#include <linux/string.h>
#include <net/tcp.h>
#include <net/udp.h>
#include <net/sock.h>
#include <linux/un.h>

#include <linux/list.h>
#include <net/af_unix.h>

#define MAX_LEN 32
static char filter_buffer[MAX_LEN];

char* tcp_state_to_string(int state) {
    switch (state) {
        case TCP_ESTABLISHED: return "ESTABLISHED";
        case TCP_SYN_SENT: return "SYN_SENT";
        case TCP_SYN_RECV: return "SYN_RECV";
        case TCP_FIN_WAIT1: return "FIN_WAIT1";
        case TCP_FIN_WAIT2: return "FIN_WAIT2";
        case TCP_TIME_WAIT: return "TIME_WAIT";
        case TCP_CLOSE: return "CLOSE";
        case TCP_CLOSE_WAIT: return "CLOSE_WAIT";
        case TCP_LAST_ACK: return "LAST_ACK";
        case TCP_LISTEN: return "LISTEN";
        case TCP_CLOSING: return "CLOSING";
        case TCP_NEW_SYN_RECV: return "NEW_SYN_RECV";
        default: return "UNKNOWN";
    }
}

static void tcp_show(struct seq_file* m, char* state, int filter_on) {
	struct sock *sk;
    struct hlist_nulls_node *node;
    unsigned int bucket;

    for (bucket = 0; bucket <= tcp_hashinfo.ehash_mask; bucket++) {
        sk_nulls_for_each(sk, node, &tcp_hashinfo.ehash[bucket].chain) {
            if (!filter_on || !strcmp(tcp_state_to_string(sk->sk_state), state)) {
                struct inet_sock *inet = inet_sk(sk);
                unsigned int recv_q = sk_rmem_alloc_get(sk);
                unsigned int send_q = sk_wmem_alloc_get(sk);

                seq_printf(m, "%-5s %-6u %-6u %-pI4:%-10u %-pI4:%-10u %-15s\n", 
                    "tcp", recv_q, send_q,
                    &inet->inet_saddr, ntohs(inet->inet_sport),
                    &inet->inet_daddr, ntohs(inet->inet_dport), 
                    tcp_state_to_string(sk->sk_state));
            }
        }
    }
}


static void udp_show(struct seq_file *m) {
    struct udp_table *udptable = &udp_table; // Access the global udp_table
    struct hlist_nulls_node *node;
    struct sock *sk;
    int bucket;

    // Iterate through all buckets in the UDP hash table
    for (bucket = 0; bucket <= udptable->mask; bucket++) {
        sk_for_each(sk, &udp_table.hash[bucket].head) {
            struct inet_sock *inet = inet_sk(sk);
            unsigned int recv_q = sk_rmem_alloc_get(sk);
            unsigned int send_q = sk_wmem_alloc_get(sk);

            seq_printf(m, "%-5s %-6u %-6u %-pI4:%-12u %-pI4:%-6u %-6s\n",
                    "udp", recv_q, send_q, &inet->inet_saddr, 
                    ntohs(inet->inet_sport), &inet->inet_daddr, 
                    ntohs(inet->inet_dport), "");
        }

    }
}


static int get_netstat_info(struct seq_file* m, void* v) {
    seq_printf(m, "Active Internet connections (w/o servers)\n");
    seq_printf(m, "%-5s %-6s %-6s %-22s %-22s %-15s\n", 
            "Proto", "Recv-Q", "Send-Q",
            "Local Address", 
            "Foreign Address", "State");
    if (!strcmp(filter_buffer, "udp") || !strcmp(filter_buffer, ""))
        udp_show(m);
    if (strcmp(filter_buffer, "udp"))
        tcp_show(m, filter_buffer, strcmp(filter_buffer, "tcp") && strcmp(filter_buffer, ""));
    return 0;
}

static int proc_open(struct inode* inode, struct file* file) {
    return single_open(file, get_netstat_info, NULL);
}

static ssize_t write_flags_to_buffer(struct file *file, const char __user *buffer, size_t count, loff_t *pos) {
    if (count > MAX_LEN - 1) // Убедимся, что буфер не переполняется
        return -EINVAL;

    if (copy_from_user(filter_buffer, buffer, count)) // Копируем данные из пользовательского пространства
        return -EFAULT;

    filter_buffer[count] = '\0'; // Добавляем завершающий ноль
    pr_info("%s\n", filter_buffer);
    return count;
}

static const char* procfs_name = "my_netstat_module";
static struct proc_dir_entry *our_proc_file;

static const struct proc_ops proc_file_fops = { 
    .proc_open = proc_open,
    .proc_read = seq_read,
    .proc_lseek = seq_lseek,
    .proc_release = single_release,
    .proc_write = write_flags_to_buffer
}; 

static int __init procfs_init(void) { 
    our_proc_file = proc_create(procfs_name, 0644, NULL, &proc_file_fops); 
    if (NULL == our_proc_file) { 
        proc_remove(our_proc_file); 
        pr_alert("Error:Could not initialize /proc/%s\n", procfs_name); 
        return -ENOMEM; 
    } 
 
    pr_info("/proc/%s created\n", procfs_name); 
    return 0; 
} 

static void __exit procfs_exit(void) { 
    proc_remove(our_proc_file); 
    pr_info("/proc/%s removed\n", procfs_name); 
} 
 
module_init(procfs_init); 
module_exit(procfs_exit); 

MODULE_LICENSE("GPL");
MODULE_AUTHOR("rsp243");
MODULE_DESCRIPTION("Prints information about connections in system (netstat command)");
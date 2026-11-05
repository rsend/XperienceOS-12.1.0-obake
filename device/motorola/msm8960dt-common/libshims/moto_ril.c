/*
 * Copyright (C) 2016 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <stddef.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/syscall.h>
#include <sys/un.h>
#include <unistd.h>

/*
 * SU6-7.7 QCRIL calls accept() for rild_ims with an uninitialized addrlen.
 * Current kernels reject that call before accepting the queued client.  The
 * peer address is unused, so omit it only for this legacy listening socket.
 */
int accept(int socket, struct sockaddr *address, socklen_t *address_length)
{
    static const char ims_socket[] = "/dev/socket/qmux_radio/rild_ims";
    struct sockaddr_un local;
    socklen_t local_length = sizeof(local);

    memset(&local, 0, sizeof(local));
    if (getsockname(socket, (struct sockaddr *)&local, &local_length) == 0 &&
            local.sun_family == AF_UNIX &&
            local_length > (socklen_t)offsetof(struct sockaddr_un, sun_path) &&
            !strcmp(local.sun_path, ims_socket)) {
        return syscall(__NR_accept, socket, NULL, NULL);
    }

    return syscall(__NR_accept, socket, address, address_length);
}

void RIL_register_socket(void *Init, int socketType, int argc, char **argv)
{
}

/* status_t Parcel::writeString16 */
extern int _ZN7android6Parcel13writeString16EPKDsj(void *addr, unsigned int size);

int _ZN7android6Parcel13writeString16EPKtj(void *addr, unsigned int size)
{
    return _ZN7android6Parcel13writeString16EPKDsj(addr, size);
}

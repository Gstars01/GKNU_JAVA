# 시스템 프로그래밍 
## 1. 디렉터리
### 1.1 접근권한 0755는 읽고 쓰기 제거의 권한을 모두 주는 권한이다 
```c
#include<sys/stat.h>
#include<stdlib.h>
#include<stdio.h>

int main(){
  if(mkdir("han",0755)== -1){  // 0755권한 부여
    perror("han");
    exit(1);
  }
}
```
### 1.2 디렉터리 삭제하기
```c
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>

int main(){
  if(rmdir("han")== -1){
    perror("han");
    exit(1);
  }
}
```
### 1.3 디렉터리 위치 검색하기 
```c
  GNU nano 7.2                         mkdir.c *                                
#define _GNU_SOURCE 
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>

int main(){
        char *cwd;
        cwd=get_current_dir_name();
        printf("cwd=%s\n",cwd);
        free(cwd);
        return 0;
}
```
### 1.4 디렉토리 이름 변경하기 
```c
  GNU nano 7.2                         rndir.c                                  
#include<sys/stat.h>
#include<stdlib.h>
#include<stio.h>

int main(){
        if(rename("han","bit")==-1){
                perror("rename");
                exit(1);
        }
}
```

### 1.5 디렉토리 위치 변경하기
```c
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
int main(){
        char *cwd;
        cwd = getcwd(NULL,NUFSIZ);
        printf("1.Current dir.: %s\n",cwd);

        chdir("han");

        cwd = getcwd(NULL,NUFSIZ);
        printf("2.Current dir.: %s\n",cwd);

        free(cwd);
}

```
### 1. Q1 han 100개 만들기.
```c
  GNU nano 7.2                       mkdir100.c                                 
#include<sys/stat.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
int main(){
        char result[100];
        for(int i=0;i<101;i++){
                sprintf(result,"%s%d","han",i);
                if(mkdir(result,0755)== -1){
                        perror("han");
                        exit(1);
                }
        }
}
```
### 1.Q1 추가 han 100개 제거하기 
```c
  GNU nano 7.2                       redir100.c                                 
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
int main(){
        char result[100];
        for(int i=0;i<101;i++){
                sprintf(result,"%s%d","han",i);
                if(rmdir(result)== -1){
                        perror("han");
                        exit(1);
                }
        }
}
```
### 파일 생성하기 
```c
include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>

int main(){
        int fd;
        mode_t mode;

        mode = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH; 

        fd = open("test.txt",O_CREAT,mode);
        if(fd == -1){
                perror("Creat");
                exit(1);
        }
        close(fd);
}
```





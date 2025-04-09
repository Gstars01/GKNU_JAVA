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



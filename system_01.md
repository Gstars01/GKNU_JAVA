# 시스템 프로그래밍 
## 1. 디렉터리
### 1.1 접근권한 0755는 읽고 쓰기 제거의 권한을 모두 주는 권한이다 
### ex)
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

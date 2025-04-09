# 시스템 프로그래밍 
## 1. 디렉터리 생성하기 
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

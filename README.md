# ELKStack
도커 컴포즈로 엘라스틱 서치 와 키바나 구성(로그 스태쉬는 추후에)

1. 도커 네트워크 생성
docker network create --driver bridge --subnet 172.25.0.0/16 es-stack

2. elasticsearch.yml 볼륨마운트
최신 엘라스틱 8.12.1 버전을 사용하며 공통 설정 사항은 elasticsearch.yml에 선언

3. docker-compose.yml 실행
클러스터 1대(실행 컴퓨터) 노드 3개로 진행


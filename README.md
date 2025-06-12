## Description
원하는 책을 입력하고 그 책에 대한 목표일을 관리하도록 서비스를 제공할 수 있는 앱
<br/>
Java / 서버 배포 / S3 / postgresql / 스웨거 / JWT 학습용 앱

<br />

An app that allows you to enter the book you want and provide a service to manage your target dates for that book
Java / server deployment / S3 / postgresql / Swagger / JWT learning app

## Database Schema

프로젝트의 데이터베이스 구조를 쉽게 파악할 수 있도록 DDL(Data Definition Language) 스크립트를 정리해두었습니다.
<br />
테이블 구조와 관계를 확인하시려면 `sql` 폴더를 참고해 주세요.

<br />

We've organized the Data Definition Language (DDL) scripts so that you can easily understand the database structure of your project.
Please refer to the `sql` folder to see the table structure and relationships.

## Tech Stack

- postgresql
- Java
- Spring Boot
- Swagger
- Supabase (S3 + postgresql)
- Render (server deployment)

## Running the app

env 파일이 필요합니다
<br />
You need an env file

env sample
```bash
DB_USERNAME=postgres.sqledvtnoofllskvgbzb
DB_PASSWORD=dbtest19283747a
DB_URL=jdbc:postgresql://aws-0-ap-northeast-2.pooler.supabase.com:5432/postgres


MAIL_HOST=
MAIL_NAME=
MAIL_PASSWORD=
JWT_SECRET_KEY=

S3_END_POINT=
S3_BUCKET_NAME=
S3_REGION=
S3_ACCESS_KEY=
S3_SECRET_KEY=
S3_PUBLIC_URL=
```

## Running the app sample

현재 서버가 내려가 있는 상태입니다.
<br />
필요하면 말씀주세요.

<br />
The server is currently down.
<br />
Let us know if you need it.

<br />
<br />
baseurl: https://book-2ggh.onrender.com
<br />
swagger url: https://book-2ggh.onrender.com/swagger-ui/index.html

![image](https://github.com/user-attachments/assets/fbc61369-5f7f-4039-93e5-b5e2bb6ac9e4)



## Author

- [dorri-riddo](https://github.com/dorri-riddo/public-service)

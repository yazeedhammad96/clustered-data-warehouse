# Clustered Data Warehouse

## Summary

Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to import deals details from files into DB. The requested performance is to be able to import the file containing 100,000 records in less than 5 seconds. 

#### Formats for valid deals
The CSV format is `DEAL_ID,FROM_CURRENCY,TO_CURRENCY,TIMESTAMP,AMOUNT`, see the example below:

```
1X2A6,AED,JOD,2019/10/12 10:50,59.00
123,JOD,SAR,2019/11/22 11:30,1.5
CODE,USD,AED,2019/03/09 00:59,500.00
```

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x


## Technology Used

Java 8 , Spring boot, Spring data, Maven, Hibernate, Thymeleaf, HTML5, BOOTSTRAB, CSS, JUNIT, MySQL.


## Steps to Setup

**1. Clone the project**

```bash
git clone https://github.com/yazeedhammad96/clustered-data-warehouse.git
```

**2. Create Mysql database**
```bash
create database bloomberg_deals
```

**3. Change mysql username and password as per your installation**

+ open `src/(main/test)/resources/application.properties` 

+ change `db.username` and `db.password` as per your mysql installation

**4. Run the project using maven**

```bash
mvn spring-boot:run
```
## Explore project web views

**1. http://localhost:6067/upload**

You can select if the CSV file that you willing to import is including CSV header, also you can specify the separator that your CSV file has, then you can select a CSV file to import, the process will take less that 5 seconds on files with 100,000 records and it's tested on (os:ubuntu 19.04, ram:16GB, cpu: Intel Core i7, ssd:240gb).

**2. http://localhost:6067/search**

Here you can search about the files that you have already import, and see the summary of its.

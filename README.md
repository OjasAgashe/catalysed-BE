# Pathshala

## Build backend-code
From project root run
```
mvn clean install
```
## Run backend-code
```
mvn spring-boot:run
```
OR

run from the jar directly after build

```
cd <project-root>/target
java -jar <jar snapshot>
```

## Deploy the jar to Google App Engine

first do a build, which will create a jar in `target` folder
Copy the jar from here to `appengine/`. Also verify ig there is app.yaml file present in this location
This file represents the configuration of the app engine container.

Now open a terminal and navigate to `appengine/`
run `gcloud app deploy`


## Deployment in dev machine
**********************************************************
###GCP Proxy from project root dir (proxy script present in project root)

`./cloud_sql_proxy -instances=level-abode-312509:asia-south1:catalysed=tcp:3306`


******************************************************

## Deployment help commands

List projects : `gcloud projects list`

App engine java deployment options :  https://cloud.google.com/appengine/docs/standard/java11/testing-and-deploying-your-app#other_deployment_options

gcloud app deploy -> from the folder where app.yml and jar is present

Tail logs to terminal :  `gcloud app logs tail -s default`
 

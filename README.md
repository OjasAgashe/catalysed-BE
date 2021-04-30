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
 

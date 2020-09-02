# Example project for Azure Functions with Spring Cloud Functions

## Config
Create a `local.settings.json` and define the following properties:
* `"FUNCTIONS_WORKER_RUNTIME": "java"`
* `"AzureCosmosDBConnection": "<CONNECTION STRING OF YOUR DB>"`
* `"AzureWebJobsStorage": "<YOUR WEB JOBS STORAGE KEY>"`
* `"EventHubConnection": "<CONNECTION STRING OF YOUR EVENT HUB>"`


* Create the needed DB (`ToDoList`) and Collections (`Items`, `Tasks`) in your CosmosDB.
* Create a `TodoEvents` event hub

## Build
`mvn clean package`

## Run
`mvn azure-functions:run`


## Deploy
`mvn azure-functions:deploy`

You won't have rights to deploy it to my azure-portal account, therefore you need to change the deployment settings in the `pom.xml`
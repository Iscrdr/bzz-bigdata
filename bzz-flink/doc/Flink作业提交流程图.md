#### flink作业提交流程图

- 本地模式


```puml

Client -> Dispatcher : 1.提交应用程序
Dispatcher -> Jobmanager : 2.启动Jobmanager提交应用程序;
Jobmanager -> ResourceManager : 3.向ResourceManager申请slots
ResourceManager -> TaskManager: 4.启动TaskManager
TaskManager -> ResourceManager: 5.向ResourceManager注册slots
ResourceManager -> TaskManager: 6.发出提供slots的指令
TaskManager -> Jobmanager: 7.提供slots
Jobmanager -> TaskManager: 8.提交要在slots中执行的任务
TaskManager -> TaskManager: 9.交换任务


```

- Yarn模式


```puml

App -> Dispatcher : 1.提交应用程序
Dispatcher -> Jobmanager : 2.启动Jobmanager提交应用程序;
Jobmanager -> ResourceManager : 3.向ResourceManager申请slots
ResourceManager -> TaskManager: 4.启动TaskManager
TaskManager -> ResourceManager: 5.向ResourceManager注册slots
ResourceManager -> TaskManager: 6.发出提供slots的指令
TaskManager -> Jobmanager: 7.提供slots
Jobmanager -> TaskManager: 8.提交要在slots中执行的任务
TaskManager -> TaskManager: 9.交换任务


```

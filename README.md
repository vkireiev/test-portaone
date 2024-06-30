## Тестове завдання в рамках програми стажування "Become a Developer 2024"

**Умова задачі**   
У нас є файл, з  великим набором цілих чисел.  
Необхідно знайти наступні чотири/шість значень:
- Максимальне число в файлі 
- Мінімальне число в файлі 
- Медіану 
- Середнє арифметичне значення
- *Найбільшу послідовність чисел (які ідуть один за одним), яка збільшується (опціонально)*
- *Найбільшу послідовність чисел (які ідуть один за одним), яка зменшується (опціонально)*


**Рішення**  
Запуск додатку виконується з командного рядку (за замовчуванням розглядається вхідний файл з назвою `input.txt`, який знаходиться у папці, з якої виконується запуск додатку).  
`% java -jar target/portaone-0.0.1-SNAPSHOT.jar`

Назву і розміщення вхідного файлу можна вказати в якості аргументу під час запуску додатку  
`% java -jar target/portaone-0.0.1-SNAPSHOT.jar src/test/resources/input1.txt`

Результат виконання друкується в стандартний вихідний потік.

**Приклад запуску №1**   
Файл `input.txt` розміщується в тій самій папці, з якої виконується запуск додатку
```
19
8
14
1
```
`% java -jar target/portaone-0.0.1-SNAPSHOT.jar`

<ins>Результат виконання</ins>
```
19
1
11.0000
10.5000
8, 14
19, 8
```

**Приклад запуску №2**   
Файл `input2.txt` розміщується не в тій самій папці, з якої виконується запуск додатку
```
-1
0
1
```
`% java -jar target/portaone-0.0.1-SNAPSHOT.jar src/test/resources/input2.txt`

<ins>Результат виконання</ins>
```
1
-1
0.0000
0.0000
-1, 0, 1
-1
```

## **Компілювання додатку

`% mvn clean test package`

```
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< ua.vkireiev:portaone >------------------------
[INFO] Building portaone 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ portaone ---
[INFO] Deleting /Users/dev/workspace/eclipse-workspace/test-tasks/portaone/target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ portaone ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO] 
[INFO] --- compiler:3.8.1:compile (default-compile) @ portaone ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /Users/dev/workspace/eclipse-workspace/test-tasks/portaone/target/classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ portaone ---
[INFO] Copying 6 resources from src/test/resources to target/test-classes
[INFO] 
[INFO] --- compiler:3.8.1:testCompile (default-testCompile) @ portaone ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /Users/dev/workspace/eclipse-workspace/test-tasks/portaone/target/test-classes
[INFO] 
[INFO] --- surefire:3.0.0-M7:test (default-test) @ portaone ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ua.vkireiev.portaone.util.LongNumberUtilTest
[INFO] Tests run: 160, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.349 s - in ua.vkireiev.portaone.util.LongNumberUtilTest
[INFO] Running ua.vkireiev.portaone.util.FileServiceTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.029 s - in ua.vkireiev.portaone.util.FileServiceTest
[INFO] Running ua.vkireiev.portaone.util.InputDataConverterTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.015 s - in ua.vkireiev.portaone.util.InputDataConverterTest
[INFO] Running ua.vkireiev.portaone.MainAppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s - in ua.vkireiev.portaone.MainAppTest
[INFO] Running ua.vkireiev.portaone.service.StatisticalServiceTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.016 s - in ua.vkireiev.portaone.service.StatisticalServiceTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 182, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ portaone ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO] 
[INFO] --- compiler:3.8.1:compile (default-compile) @ portaone ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /Users/dev/workspace/eclipse-workspace/test-tasks/portaone/target/classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ portaone ---
[INFO] Copying 6 resources from src/test/resources to target/test-classes
[INFO] 
[INFO] --- compiler:3.8.1:testCompile (default-testCompile) @ portaone ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /Users/dev/workspace/eclipse-workspace/test-tasks/portaone/target/test-classes
[INFO] 
[INFO] --- surefire:3.0.0-M7:test (default-test) @ portaone ---
[INFO] Skipping execution of surefire because it has already been run for this configuration
[INFO] 
[INFO] --- jar:3.1.0:jar (default-jar) @ portaone ---
[INFO] Building jar: /Users/dev/workspace/eclipse-workspace/test-tasks/portaone/target/portaone-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.823 s
[INFO] Finished at: 2024-06-30T22:03:50+03:00
[INFO] ------------------------------------------------------------------------
```
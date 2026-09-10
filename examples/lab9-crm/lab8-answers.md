\# Lab 8 Answers



\## Layer Notes



\## Failure Experiments



\### 1. Missing pom.xml

\- Action: Renamed `pom.xml`

\- Result: Maven failed because it could not find a POM

\- Meaning: Maven needs `pom.xml` in the project root

\- Fix: Restored `pom.xml`



\### 2. Repeat Build

\- Action: Ran `mvn clean compile` twice

\- Result: Both builds succeeded

\- Meaning: The build is repeatable



\### 3. Invalid Dependency Direction

\- Action: Added `CustomerController` import inside `CustomerRepository`

\- Result: Project still compiled

\- Meaning: Java allows it, but it violates the architecture rule

\- Fix: Removed the bad import



\## Reflection Questions





\### 1. Which design decision most affected correctness of the skeleton?

Keeping the dependency direction correct had the biggest impact because each layer has a clear responsibility and lower layers do not depend back on higher layers.



\### 2. What evidence proves the layered structure is real, not only aspirational?

The project contains separate controller, service, repository, entity, dto, config, and exception packages, and Maven compiles them successfully.



\### 3. Which failure was hardest to diagnose: pathing, packages, or POM?



Pathing was the hardest to diagnose because the code and files could exist, but Maven or Java would still fail if I was running commands from the wrong folder or if files were placed in the wrong directory structure. The POM failure was easier because Maven clearly said that `pom.xml` was missing.


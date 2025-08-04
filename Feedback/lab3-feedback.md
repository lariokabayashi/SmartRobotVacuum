### Lab 3 feedback
In general, good progress by the three of you!
- separated app and firmware accordingly
- domain, sequence and class diagrams acceptable for both, the app and the firmwware (*)
- maybe too high abstraction level for the app --> curious to see your lab 4 work, i.e. UC demonstrators functionality and pattern-usage for its design.

(*) In firmware domain model, inheritance concept was not correcty applied: In a nut-shell Inheritance: "is a", Aggregation: "has a"!

#### App
- here some hints to successfully provide a demonstrator
- UC11 notification to the user typically is given by special decoration of the map displaying the cleaning area, cleaning progress and robot status: There is the UC-cluster UC DisplayCleaningProgress which includes UC DisplayMap and UC RobotStatus (lacation, battery, errorstate etc) and also your App UC11 that could be called UC DisplayAndControlMessDetected
- Since this UC cluster can interoperate with Brians firmware demontration in labs 5 and 6, I would suggest you concentrate on this UC cluster in your further work today -> lab 3 finalization and lab 4 prep.
- There is no central AppController in thie UC cluster, remind your experience from Java GUI programming! System works asynchronously with threads and gets notified by the components, in case of UC11 by the boundary concept (firmware-side).
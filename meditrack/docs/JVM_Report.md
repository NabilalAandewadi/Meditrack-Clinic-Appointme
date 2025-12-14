# JVM Report - MediTrack Application

## 1. Class Loader Subsystem

The Class Loader is responsible for loading class files into the JVM.

- **Bootstrap Class Loader**: Loads core Java classes (rt.jar, e.g., java.lang.Object). Written in native code.
- **Extension Class Loader**: Loads classes from extension directories (jre/lib/ext).
- **Application/System Class Loader**: Loads classes from the classpath (our application classes under com.airtribe.meditrack).

Key features demonstrated:
- Delegation model: Child loaders delegate to parent before trying to load.
- Ensures security and avoids duplicate loading.

In MediTrack, the Application Class Loader loads our entities, services, controllers, etc.

## 2. Runtime Data Areas

The JVM memory is divided into several areas:

- **Method Area**: Stores class metadata, constant pool, static variables, method bytecode. Shared across threads.
    - In MediTrack: Stores static fields like Constants.TAX_RATE, static blocks output.

- **Heap**: Stores all objects and arrays. Shared across threads. Garbage collected.
    - In MediTrack: All entities (Doctor, Patient, Appointment, Bill) instances live here.

- **Stack**: Per-thread. Stores method frames (local variables, operand stack, parameters).
    - Each controller/service method call creates a frame.

- **PC Register**: Per-thread. Holds address of currently executing JVM instruction.

- **Native Method Stack**: For native method calls.

## 3. Execution Engine

Responsible for executing bytecode:

- **Interpreter**: Executes bytecode line by line. Fast startup, slower execution.
- **JIT (Just-In-Time) Compiler**: Compiles hot methods to native machine code.
    - C1 (client) compiler: Quick warmup.
    - C2 (server) compiler: Aggressive optimizations.
- **Garbage Collector**: Manages heap memory (G1 GC default in Java 17).

In MediTrack, long-running API calls (e.g., stream operations on large patient lists) benefit from JIT optimization.

## 4. JIT Compiler vs Interpreter

- Interpreter: Executes bytecode directly → quick start, but repetitive execution is slow.
- JIT: Monitors "hot" methods → compiles to native code → significantly faster repeated execution.

MediTrack benefits from JIT during:
- Repeated searches using streams/lambdas.
- Frequent CRUD operations in services.

## 5. "Write Once, Run Anywhere"

Java source → compiled to platform-independent bytecode (.class files) → JVM interprets/compiles to native code specific to the OS/hardware.

This principle allows MediTrack to run unchanged on:
- Windows, Linux, macOS
- x86, ARM, etc.
- As long as a compatible JDK (17+) is installed.

Demonstrated by running the same Spring Boot JAR on any supported platform.
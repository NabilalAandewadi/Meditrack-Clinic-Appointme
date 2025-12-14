# Design Decision Report - MediTrack Spring Boot Implementation

## 1. Choice of Spring Boot over Console Application

Original specification was a console-based Java application. Decision to migrate to Spring Boot:

**Reasons**:
- Better demonstrates modern enterprise Java practices.
- Built-in support for REST APIs, dependency injection, security, testing.
- Easier integration testing (vs manual TestRunner).
- Scalable foundation (can later add frontend, database, microservices).
- Aligns with industry standards for Java backend development.

**Trade-offs**:
- Slightly higher complexity for beginners.
- Requires understanding of annotations, IoC, etc.
- Mitigated by clear package structure and separation of concerns.

## 2. Persistence: JPA/Hibernate with H2 vs In-Memory Collections + CSV

**Decision**: Use Spring Data JPA with in-memory H2 database (with CSV export bonus).

**Reasons**:
- Real-world applications use databases.
- Demonstrates ORM, entities, repositories.
- Automatic ID handling, relationships (@ManyToOne).
- Easy integration testing with embedded DB.
- Retained CSV export as bonus feature using try-with-resources.

**Alternative considered**: Pure in-memory (ArrayList/HashMap) + CSV serialization.
- Rejected: Less educational value, manual CRUD boilerplate.

## 3. Security & Authorization

**Decision**: Spring Security with Basic Auth and role-based access (ADMIN/USER).

**Reasons**:
- Requirement mentioned "authorization".
- ADMIN can create/modify, USER can view/search.
- Simple in-memory users for demo.
- Protects endpoints (e.g., POST only for ADMIN).

**Future improvement**: JWT + proper user registration.

## 4. Exception Handling

**Decision**: Custom exceptions + @ControllerAdvice for global handling.

**Benefits**:
- Clean controllers (no try-catch clutter).
- Consistent error responses (HTTP status codes).
- Demonstrates exception chaining (some constructors accept cause).

## 5. OOP Principles Implementation

All core and advanced OOP concepts retained:

- **Encapsulation**: Private fields + getters/setters + Validator utility.
- **Inheritance**: Person → Doctor/Patient.
- **Polymorphism**: Method overloading in PatientController (search by name/age), overriding getType().
- **Abstraction**: MedicalEntity abstract class, Searchable/Payable interfaces with default methods.
- **Deep vs Shallow Copy**: Proper Cloneable implementation in Doctor, Patient, Appointment with public clone() methods.
- **Immutability**: BillSummary as final class with no setters.
- **Enums**: Specialization, AppointmentStatus.
- **Static initialization**: Constants class with static block.

## 6. Design Patterns Used

- **Singleton**: IdGenerator (eager initialization with AtomicInteger for thread-safety).
- **Factory**: Simple factory method in AppointmentService.generateBill() (extensible to different bill types).
- **Bonus**: CSV export using try-with-resources.

Observer and Strategy not fully implemented to keep scope manageable.

## 7. Java 8+ Features

Extensive use of:
- Streams & lambdas in services (filtering, counting appointments per doctor).
- Method references (where applicable).
- Default methods in interfaces.

## 8. Testing Strategy

**Decision**: Integration tests with @SpringBootTest + TestRestTemplate.

**Reasons**:
- Tests full stack (security, controllers, services, repositories).
- More valuable than unit tests with mocks.
- Demonstrates authorization (unauthorized access test).
- Covers happy path (create doctor → create patient → create appointment).

## Summary

This Spring Boot implementation preserves and enhances all learning objectives from the original specification while aligning with professional Java development practices. It provides a solid, testable, secure, and maintainable foundation that exceeds the basic console application requirements.